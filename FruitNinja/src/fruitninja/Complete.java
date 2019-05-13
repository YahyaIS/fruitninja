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
public class Complete {
    
     private BufferedImage bw =new BufferedImage(100,200,BufferedImage.TYPE_INT_RGB);
    private BufferedImage br=new BufferedImage(100,200,BufferedImage.TYPE_INT_RGB);
    private BufferedImage bb=new BufferedImage(100,200,BufferedImage.TYPE_INT_RGB);
    private Rectangle2D rec1;
    private Rectangle2D rec2;
    
    public Complete() {
     
        File input=new File("win.jpg");
        try {
            bw= ImageIO.read(input);
        }
        catch(IOException ex) {
            Logger.getLogger(Side.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        File input1=new File("backButton.png");
        try {
            bb= ImageIO.read(input1);
        }
        catch(IOException ex) {
            Logger.getLogger(Side.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        this.rec1 = new Rectangle2D(325,315,bb.getWidth(),bb.getHeight());
        File input2=new File("restart.png");
        try {
            br= ImageIO.read(input2);
        }
        catch(IOException ex) {
            Logger.getLogger(Side.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        this.rec2 = new Rectangle2D(610,315,br.getWidth(),br.getHeight());
        
    }
    
    
    public javafx.scene.image.Image getWImage(){
        Image image = SwingFXUtils.toFXImage(this.bw, null);
        return  image;
    }
    
   public javafx.scene.image.Image getRImage(){
        Image image = SwingFXUtils.toFXImage(this.br, null);
        return  image;
    }
   
   public javafx.scene.image.Image getBImage(){
        Image image = SwingFXUtils.toFXImage(this.bb, null);
        return  image;
    }

    public Rectangle2D getRec1() {
        return rec1;
    }

    public Rectangle2D getRec2() {
        return rec2;
    }
   
   
    
}
