/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruitninja;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Rectangle2D;
import javafx.geometry.Side;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

/**
 *
 * @author seif
 */
public class SuperFruit2 extends Fruit{
    private Fruit.fruit superFruit ;
    
    private BufferedImage bi =new BufferedImage(100,200,BufferedImage.TYPE_INT_RGB);
    private BufferedImage bi1 =new BufferedImage(100,200,BufferedImage.TYPE_INT_RGB);
    private BufferedImage bi2 =new BufferedImage(100,200,BufferedImage.TYPE_INT_RGB);
    public SuperFruit2() {
        superFruit =Fruit.fruit.SUPERFRUIT;
        File input=new File("s2.png");
        try {
            bi= ImageIO.read(input);
        }
        catch(IOException ex) {
            Logger.getLogger(Side.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        this.rec = new Rectangle2D(posX,posY,bi.getWidth(),bi.getHeight());
        File input1=new File("s21.png");
        try {
            bi1= ImageIO.read(input1);
        }
        catch(IOException ex) {
            Logger.getLogger(Side.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        File input2=new File("s22.png");
        try {
            bi2= ImageIO.read(input2);
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
    
    public javafx.scene.image.Image getImage1(){
        Image image = SwingFXUtils.toFXImage(this.bi1, null);
        return  image;
    }
    
    public javafx.scene.image.Image getImage2(){
        Image image = SwingFXUtils.toFXImage(this.bi2, null);
        return  image;
    }
    
    
    @Override
    public Enum getObjectType() {
        return superFruit;
    }

    
    
    
}
