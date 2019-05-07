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

public class RedBomb extends Bombs {

    private bomb redbomb;
    private Rectangle2D rec;
    private BufferedImage bi = new BufferedImage(100, 200, BufferedImage.TYPE_INT_RGB);

    public RedBomb() {
        redbomb = bomb.DEADLY;
        File input = new File("redbomb.png");
        try {
            bi = ImageIO.read(input);
        } catch (IOException ex) {
            Logger.getLogger(Side.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        this.rec = new Rectangle2D(posX, posY, bi.getWidth(), bi.getHeight());
        Random rx= new Random();
        deltaX = 2+rx.nextFloat()*4;
        Random ry= new Random();
        deltaY = -(8+ry.nextFloat()*8);
        downY=-deltaY;
    }

    @Override
    public javafx.scene.image.Image getImage() {
        Image image = SwingFXUtils.toFXImage(this.bi, null);
        return image;
    }

    @Override
    public Enum getObjectType() {
        return redbomb;
    }


    

}
