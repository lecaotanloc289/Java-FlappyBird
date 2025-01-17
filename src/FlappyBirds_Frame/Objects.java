package FlappyBirds_Frame;

public class Objects {
    
    private float posX, posY;
    private float w, h;
    
    public Objects(float x, float y, float w, float h){
        this.posX = x;
        this.posY = y;
        this.w = w;
        this.h = h;
    }

    public void setPos(float x, float y){
        posX = x;
        posY = y;
    }
    public void setPosX(float x){
        posX = x;
    }
    public void setPosY(float y){
        posY = y;
    }
    public float getPosX(){
        return posX;
    }
    public float getPosY(){
        return posY;
    }
    public float getW(){
        return w;
    }
    public float getH(){
        return h;
    }
    public void increasePosX(float m){
        posX+=m;
    }
    public void increasePosY(float m){
        posY+=m;
    }
}
