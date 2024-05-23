package FlappyBirds_Frame;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class AFrameOnImage {
    
    private boolean enablePaintRect = false;
    
    private int []DimsBounds = new int[4];
    
    public AFrameOnImage(int xOnImage, int yOnImage, int w, int h){
        DimsBounds[0] = xOnImage;
        DimsBounds[1] = yOnImage;
        DimsBounds[2] = w;
        DimsBounds[3] = h;
    }
    // Sử dụng hàm để vẽ bức ảnh
    public void Paint(int x, int y, BufferedImage image, Graphics2D g2, int anchor, float rotation){
        
        // Sử dụng mảng DimsBounds để lấy tọa độ bức ảnh trên hình.
        BufferedImage imageDraw = image.getSubimage(DimsBounds[0], DimsBounds[1], DimsBounds[2], DimsBounds[3]);

        // Sử dụng đối tượng để xoay ảnh theo yêu cầu thông qua tham số rotation
        // SỬ DỤNG ĐỂ XOAY CON CHIM KHI NÓ NGỐC CÁI ĐẦU LÊN
        AffineTransform tx = new AffineTransform();
        tx.rotate(rotation, imageDraw.getWidth() / 2, imageDraw.getHeight() / 2);

        AffineTransformOp op = new AffineTransformOp(tx,
            AffineTransformOp.TYPE_BILINEAR);
        imageDraw = op.filter(imageDraw, null);
        
        
        g2.drawImage(imageDraw, x, y, null);
        
        if(enablePaintRect) PaintBound(g2);
    }
    private void PaintBound(Graphics2D g){
        
    }
}
