package flappybirds;

import FlappyBirds_Frame.Objects;
import FlappyBirds_Frame.SoundPlayer;

import java.awt.*;
import java.io.File;

public class Bird extends Objects {

    // Tao một biến tốc độ
    private float v = 0;

    // Tạo một biến flag kiểm tra xem Bird có đang bay hay không
    private boolean isFlying;

    private boolean isLive = true;


    public Bird (int x, int y, int width, int height) {
        super(x, y, width, height);
            // Trong đó x và y là tọa độ của con chim và width - height là độ
            // rộng của con chim để xác định va chạm với vùng khác

        checkImpact = new Rectangle(x, y, width, height);
        swingSound = new SoundPlayer(new File("Assets/wing.wav"));
        impactSound = new SoundPlayer(new File("Assets/hit.wav"));
        pointSound = new SoundPlayer(new File("Assets/point.wav"));
        dieSound = new SoundPlayer(new File("Assets/die.wav"));



    }
    public boolean getLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public Rectangle getCheckImpact() {
        return checkImpact;
    }

    public SoundPlayer swingSound, impactSound, pointSound, dieSound;

    public void setCheckImpact(Rectangle checkImpact) {
        this.checkImpact = checkImpact;
    }

    // biến kiểm tra va chạm giữa Bird và Chimney
    // bằng cách kiểm tra va chạm giữa các hình chữ nhật với nhau
    private Rectangle checkImpact;
    public void update (long deltaTime) { // Update để tăng tốc độ lên bằng trọng lực
        v+= FlappyBirds.GRAVITY;
        // Vận tốc v có gia tốc là Gravity, nên tốc độ nó sẽ thay đổi theo thời gian
        // Và khi tốc độ thay đổi thì mình cập nhật lại vị trí rơi của obj
        this.setPosY(this.getPosY() + v);
        this.checkImpact.setLocation((int) this.getPosX(), (int) this.getPosY());
        isFlying = v < 0; //Bird đang bay lên

    }

    public void fly () {
        v = (float) - 3.5; // Lúc này v sẽ tăng lên
        // Nhưng trong quá trình update nó sẽ lại tăng giá trị v này nên => giảm theo y rơi xuống lại
        swingSound.play();
    }

    public void setV (float v ) {
        this.v = v;
    }


    public boolean getIsFlying () {
        return isFlying;
    }
}
