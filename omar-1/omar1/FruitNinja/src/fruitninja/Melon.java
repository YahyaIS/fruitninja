
package fruitninja;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Rectangle2D;
import javafx.geometry.Side;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

public class Melon extends Fruit{
    private fruit melon ;
    public Melon() {
        points=1;
        melon =fruit.MELON;
        File input=new File("melon.png");
        try {
            bi= ImageIO.read(input);
        }
        catch(IOException ex) {
            Logger.getLogger(Side.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        this.rec = new Rectangle2D(posX,posY,bi.getWidth(),bi.getHeight());
        File input1=new File("w1.png");
        try {
            bi1= ImageIO.read(input1);
        }
        catch(IOException ex) {
            Logger.getLogger(Side.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        File input2=new File("w2.png");
        try {
            bi2= ImageIO.read(input2);
        }
        catch(IOException ex) {
            Logger.getLogger(Side.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
