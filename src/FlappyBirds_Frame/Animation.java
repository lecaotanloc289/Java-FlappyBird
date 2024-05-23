/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlappyBirds_Frame;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Animation {
    
    private long beginTime = 0;
    
    private long mesure = 20;
    
    private AFrameOnImage[] frames;
    private int NumOfFrame = 0; // Số frames
    private int CurrentFrame = 0; // Frames hiện tại

    // mesure: tham số chỉ mỗi bức ảnh tồn tại trong bao nhiêu lâu.
    public Animation(long mesure){
        this.mesure = mesure;
    }
    
    public void Update_Me(long deltaTime){
        // Khi số frame > 0 thì mới được thực hiện hàm
        if(NumOfFrame>0){
            // nếu hiệu thời gian lớn hơn thời gian chờ đợi thì tăng frame lên
            if(deltaTime - beginTime > mesure){
                CurrentFrame++;
                // Nếu số frames lớn hơn = số frames mình có thì nó quay lại ban đầu để thực hiện
                if(CurrentFrame>=NumOfFrame) 
                    CurrentFrame = 0;
                beginTime = deltaTime;
            }
        }
    }

    // Khi add frame thì add vào đối tuượng gồm mảng các frames
    public void AddFrame(AFrameOnImage sprite){
        AFrameOnImage[] bufferSprites = frames;
        frames = new AFrameOnImage[NumOfFrame+1];
        for(int i = 0;i<NumOfFrame;i++) frames[i] = bufferSprites[i];
        frames[NumOfFrame] = sprite;
        NumOfFrame++;
    }

    // Vẽ đối tượng ra mà hình có animation
    public void PaintAnims(int x, int y, BufferedImage image, Graphics2D g2, int anchor, float rotation){
        frames[CurrentFrame].Paint(x, y, image, g2, anchor, rotation);
    }
}
