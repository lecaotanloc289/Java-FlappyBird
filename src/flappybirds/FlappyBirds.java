package flappybirds;

import FlappyBirds_Frame.AFrameOnImage;
import FlappyBirds_Frame.Animation;
import FlappyBirds_Frame.GameScreen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FlappyBirds extends GameScreen {
    // Tạo một biến lấy hình ảnh
    private final BufferedImage Birds;
    private final Animation bird_animation;
    // Tạo một biến thể hiện trọng lực rơi
    // Gia tốc hướng tâm của trái đất, tạo cho con chim nó rơi xuống dưới mặt đất
    public static float GRAVITY = 0.2f;
    private final Bird bird;
    private final Ground ground;
    private final Background background;
    private ChimneyLoop chimneyLoop;
    private final int BEGIN_SCREEN = 0;
    private final int GAMEPLAY_SCREEN = 1;
    private final int GAMEOVER_SCREEN = 2;
    private int CurrentScreen = BEGIN_SCREEN;
    private int POINT = 0;
    // Viết phương thức khởi tao
    public FlappyBirds () {
        // Thiết lập kích thước cửa sổ màn hình
        super(800, 600);

        // Lấy một hình ảnh Flappy Birds bằng ImageIO
        try {
            Birds = ImageIO.read( new File("Assets/bird_sprite.png"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        bird_animation = new Animation(100); // 100 mili giây

        // Bức ảnh bird_sprite gồm 3 ảnh của con chim chuyển động, cao 56 rộng 180,
        // Lấy hình ảnh con chim đầu tiên
        AFrameOnImage AFOI;
            // x = 0, y = 0, rộng 60, cao 60
        AFOI = new AFrameOnImage(0, 0, 60, 56);
        bird_animation.AddFrame(AFOI);

        // Lấy hình ảnh con chim thứ hai
        AFOI = new AFrameOnImage(60, 0, 60, 56);
        bird_animation.AddFrame(AFOI);

        // Lấy hình ảnh con chim thứ ba
        AFOI = new AFrameOnImage(120, 0, 60, 56);
        bird_animation.AddFrame(AFOI);

        // Lấy hình ảnh con chim thứ 2: làm cho chuyển động mịn hơn
        AFOI = new AFrameOnImage(60, 0, 60, 56);
        bird_animation.AddFrame(AFOI);
        // Gọi hàm Begin game để mở cửa sổ mành hình game: Khỏi chạy và khỏi tạo Thread
        // Tất cả các đối tượng phải được khởi tạo trước khi game khởi chạy

        bird = new Bird(350, 250, 50, 50);
        ground = new Ground();
        background = new Background();
//        chimney = new Chimney(700, 400, 70, 400);

        chimneyLoop = new ChimneyLoop();



        BeginGame(); // KHi begin game thì các phương thức override được tự động gọi liên tục.
    }

    public static void main(String[] args) {
        new FlappyBirds();
    }

    public void resetGame () {
        bird.setPos(350, 250);
        bird.setV(0);
        bird.setLive(true);
        POINT = 0;
        // Khởi tạo lại màn hình game khi Game Over
        chimneyLoop = new ChimneyLoop();
    }
    @Override
    public void GAME_UPDATE(long deltaTime) {
        if(CurrentScreen == BEGIN_SCREEN) {
            resetGame();

        } else if (CurrentScreen == GAMEPLAY_SCREEN) {
            // Khi game được bắt đầu chơi mới thực hiện update
            if(bird.getLive()) { // Nếu Bird ở trạng thái true
                bird_animation.Update_Me(deltaTime);
                bird.update(deltaTime);
                ground.Update();
                chimneyLoop.update();
            }

            for (int i = 0; i < ChimneyLoop.SIZE; i++) {
                // Kiểm tra hai rectangle có giao nhau hay không, trả về boolen
                if(bird.getCheckImpact().intersects(chimneyLoop.getChimney(i).getCheckImpact()))
                {
                    bird.setLive(false);
                    bird.impactSound.play();
                }



            }

            for (int i = 0; i < ChimneyLoop.SIZE; i+=2) {
                // Tính điểm bằng cách kiểm tra
                if(bird.getPosX() > chimneyLoop.getChimney(i).getPosX() + 74  && !chimneyLoop.getChimney(i).isPassed())
                {
                    POINT++;
                    bird.pointSound.play();
                    chimneyLoop.getChimney(i).setPassed(true);
                    // Khi đã qua 6 ống khói, không còn cái nào flase nữa,
                    // nên ta phải update lại trạng thái của ống khói khi nó về sau
                }
            }


            // Kiểm tra khi nào bị va chạm và kết thúc game đối với mặt đất
            if (bird.getPosY() + bird.getH() > ground.getY())
            {
                CurrentScreen = GAMEOVER_SCREEN;
                bird.dieSound.play();
            }


        }
    }
    @Override
    public void GAME_PAINT(Graphics2D g2) {
        background.Paint(g2);
        chimneyLoop.paint(g2);
        ground.Paint(g2);
        g2.setColor(Color.red);
        g2.drawString("Point: "+POINT, 20, 20);

        if(bird.getIsFlying()) // trả về true => đang bay lên
            bird_animation.PaintAnims((int) bird.getPosX(), (int) bird.getPosY(), Birds, g2, 0, -1 );
        else
            bird_animation.PaintAnims((int) bird.getPosX(), (int) bird.getPosY(), Birds, g2, 0, 0 );
        if(CurrentScreen == BEGIN_SCREEN) {
            g2.setColor(Color.black);
            g2.drawString("Press SPACE to play game", 310, 240);
        }
        if (CurrentScreen == GAMEOVER_SCREEN || !bird.getLive() ) {
            g2.setColor(Color.black);
            g2.drawString("Press SPACE to play game again!", 310, 240);
            CurrentScreen = GAMEOVER_SCREEN;
        }
    }

    @Override
    public void KEY_ACTION(KeyEvent e, int Event) {
        if(CurrentScreen == BEGIN_SCREEN) {
            CurrentScreen = GAMEPLAY_SCREEN;
        } else if (CurrentScreen == GAMEPLAY_SCREEN) {
             if(bird.getLive()) bird.fly(); //if (Event == KEY_PRESSED) bird.fly();

        } else if (CurrentScreen == GAMEOVER_SCREEN){
            CurrentScreen = BEGIN_SCREEN;


        }
    }
}
