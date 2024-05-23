package flappybirds;



import FlappyBirds_Frame.QueueList;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class ChimneyLoop {

    private QueueList<Chimney> Chimneys;
    private BufferedImage ChimneyImageBottom;
    private BufferedImage ChimneyImageTop;

    private int TopY = -350, BottomY = 200;

    public static int SIZE = 6;
    public ChimneyLoop() {
        Chimneys = new QueueList<Chimney>();
        Chimney chimney;

        // Lấy một hình ảnh Flappy Birds bằng ImageIO
        try {
            ChimneyImageTop = ImageIO.read(new File("Assets/chimneytop.png"));
            ChimneyImageBottom = ImageIO.read(new File("Assets/chimneybuttom.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Tạo 3 cặp ống khói
        for (int i = 0; i < SIZE / 2; i++) {
            int Y = randomY();
            // Ống khói thứ nhất
            chimney = new Chimney(830 + i * 300, BottomY + Y, 74, 400);
            Chimneys.push(chimney);

            // Ống khói thứ hai
            chimney = new Chimney(830 + i * 300, TopY + Y, 74, 400);
            Chimneys.push(chimney);
        }
    }
    public int randomY () {
        Random random = new Random();
        int Y = random.nextInt(10);
        return Y * 35;
    }
    public Chimney getChimney(int i) {
        return Chimneys.get(i);
    }
    public void update() {
        for (int i = 0; i < 6; i++) {
//            Chimneys.get(i).setPosX(Chimneys.get(i).getPosX() - 2);
            // Update tọa dộ của ônống khói, tạo sự di chuyển
            Chimneys.get(i).update();

        }
        // Nếu tọa độ của ống khói < -74 thì xóa nó ra khỏi queue list và set tọa độ
        // nó ở phía sau tạo độ của ôống khói cuối cùng
        if(Chimneys.get(0).getPosX() < -74 )
        {   int Y = randomY();
            Chimney chimney;
            chimney = Chimneys.pop();
            chimney.setPosX(Chimneys.get(4).getPosX() + 300);
            chimney.setPosY(BottomY + Y);
            Chimneys.push(chimney);
            chimney.setPassed(false);


            chimney = Chimneys.pop();
            chimney.setPosX(Chimneys.get(4).getPosX() );
            chimney.setPosY(TopY + Y);
            Chimneys.push(chimney);
            chimney.setPassed(false);

        }
    }

    public void paint (Graphics2D g2) {
        for (int i = 0; i < 6; i++) {
            if (i % 2 == 0) // Nếu là ống khói thứ nhất thì vẽ nó quay lên
                g2.drawImage(ChimneyImageBottom, (int) Chimneys.get(i).getPosX(), (int) Chimneys.get(i).getPosY(), null);
            else // Ngược lại vẽ ống khói thứ hai quay xuống
                g2.drawImage(ChimneyImageTop, (int) Chimneys.get(i).getPosX(), (int) Chimneys.get(i).getPosY(), null);

        }


    }

}
