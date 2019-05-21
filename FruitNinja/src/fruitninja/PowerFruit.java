
package fruitninja;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Rectangle2D;
import javafx.geometry.Side;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

public class PowerFruit extends Fruit{
    private Fruit.fruit powerFruit ;
    public PowerFruit() {
        points=0;
        powerFruit =Fruit.fruit.POWERFRUIT;
        File input=new File("dfruit.png");
        try {
            bi= ImageIO.read(input);
        }
        catch(IOException ex) {
            Logger.getLogger(Side.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        this.rec = new Rectangle2D(posX,posY,bi.getWidth(),bi.getHeight());
        File input1=new File("d1.png");
        try {
            bi1= ImageIO.read(input1);
        }
        catch(IOException ex) {
            Logger.getLogger(Side.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        File input2=new File("d2.png");
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
    public javafx.scene.image.Image getImage1(){
        Image image = SwingFXUtils.toFXImage(this.bi1, null);
        return  image;
    }
    
    @Override
    public javafx.scene.image.Image getImage2(){
        Image image = SwingFXUtils.toFXImage(this.bi2, null);
        return  image;
    }
    
    
    @Override
    public Enum getObjectType() {
        return powerFruit;
    }

    
    
}
