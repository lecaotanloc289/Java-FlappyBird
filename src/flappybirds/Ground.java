package flappybirds;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Ground {
    private BufferedImage groundImage;

    // Tọa độ của mặt đất
    // x1 y1 cho mặt đất số 1, x2 y2 cho mặt đất số 2.
    private int x1, y1, x2, y2;
    // Phương thức khởi tạo
    public Ground () {
        // Đọc hình ảnh mặt đất
        try {
            groundImage = ImageIO.read(new File("Assets/ground.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Khởi tạo vị trí của mắt đất đầu tiên
        x1 = 0; y1 = 550;

        // Khởi tạo vị trí của mặt đất số 2 phía sau mặt đất đầu tiên
        x2 = x1 + 830; y2 = 550; // 830 là chiều dài của ảnh.
    }
    // Vẽ mặt đất
    public void Paint(Graphics2D g2) {
        // Vẽ mặt đất thứ nhất
        g2.drawImage(groundImage, x1, y1, null);

        // Vẽ mặt đất thứ hai
        g2.drawImage(groundImage, x2, y2, null);

    }

    public int getY() {
        return y1;
    }

    public void Update () {
        // Update tọa độ của x1 và x2 để tạo tính di chuyển cho mặt đất
        x1-=2;        x2 -= 2;

        // Nếu x1 bắt đầu di chuyển thì cộng thêm tọa độ cho x2 để bức ảnh 2 hiện ra sau bức ảnh đầu tiên
        if(x1 < 0) x2 = x1 + 830;

        // Ngược lại
        if (x2 < 0) x1 = x2 + 830;
    }
}
