package flappybirds;

import FlappyBirds_Frame.Objects;

import java.awt.*;

public class Chimney extends Objects {
    private Rectangle checkImpact;

    private boolean Passed = false;

    public boolean isPassed() {
        return Passed;
    }

    public void setPassed(boolean passed) {
        Passed = passed;
    }

    public Rectangle getCheckImpact() {
        return checkImpact;
    }

    public void setCheckImpact(Rectangle checkImpact) {
        this.checkImpact = checkImpact;
    }

    public Chimney (int x, int y, int width, int height) {
        super(x, y, width, height);
        checkImpact = new Rectangle(x, y, width, height);

    }

    public void update() {
        setPosX(getPosX()-2);
        this.checkImpact.setLocation((int) this.getPosX(), (int) this.getPosY());

    }



}
