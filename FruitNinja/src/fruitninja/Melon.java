
package fruitninja;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Rectangle2D;
import javafx.geometry.Side;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

public class Melon extends Fruit{
    private fruit melon ;
    private Rectangle2D rec;
    private BufferedImage bi =new BufferedImage(100,200,BufferedImage.TYPE_INT_RGB);
    public Melon() {
        melon =fruit.MELON;
        File input=new File("melon.png");
        try {
            bi= ImageIO.read(input);
        }
        catch(IOException ex) {
            Logger.getLogger(Side.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        this.rec = new Rectangle2D(posX,posY,bi.getWidth(),bi.getHeight());
    }

    @Override
    public javafx.scene.image.Image getImage(){
        Image image = SwingFXUtils.toFXImage(this.bi, null);
        return  image;
    }
    @Override
    public Enum getObjectType() {
        return melon;
    }
    
}
