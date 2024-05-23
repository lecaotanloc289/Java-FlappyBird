package flappybirds;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Background {
    private final int x;
    private final int y; // Tọa độ của background
    private final BufferedImage background;
    public Background () {
        try {
            background = ImageIO.read(new File("Assets/background.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        x = -50; y = -50;
    }

    public void Paint (Graphics2D g2) {
        g2.drawImage(background, x, y, null);
    }
}
