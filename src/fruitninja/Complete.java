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
    private BufferedImage bp=new BufferedImage(100,200,BufferedImage.TYPE_INT_RGB);
    private BufferedImage bs=new BufferedImage(100,200,BufferedImage.TYPE_INT_RGB);
    private Rectangle2D rec1;
    private Rectangle2D rec2;
    private Rectangle2D rec3;
    private Rectangle2D rec4;
    public Complete() {
     
        File input3=new File("p.png");
        try {
            bp= ImageIO.read(input3);
        }
        catch(IOException ex) {
            Logger.getLogger(Side.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        this.rec3 = new Rectangle2D(30,70,br.getWidth(),br.getHeight());
        File input4=new File("s.png");
        try {
            bs= ImageIO.read(input4);
        }
        catch(IOException ex) {
            Logger.getLogger(Side.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        this.rec4 = new Rectangle2D(460,300,br.getWidth(),br.getHeight());
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
   public javafx.scene.image.Image getPImage(){
        Image image = SwingFXUtils.toFXImage(this.bp, null);
        return  image;
    }
   public javafx.scene.image.Image getSImage(){
        Image image = SwingFXUtils.toFXImage(this.bs, null);
        return  image;
    }

    public void setRec1() {
        rec1 = new Rectangle2D(325,315,bb.getWidth(),bb.getHeight());
    }

    public void setRec2() {
        rec2 = new Rectangle2D(610,315,br.getWidth(),br.getHeight());
    }

    public void setRec3(Rectangle2D rec3) {
        rec3 = new Rectangle2D(30,70,br.getWidth(),br.getHeight());
    }
    

    public void removeRecs(){
        rec1 = new Rectangle2D(1000,315,bb.getWidth(),bb.getHeight());
        rec2 = new Rectangle2D(1000,315,br.getWidth(),br.getHeight());
    }
    
    public Rectangle2D getRec1() {
        return rec1;
    }

    public Rectangle2D getRec2() {
        return rec2;
    }
    public Rectangle2D getRec3() {
        return rec3;
    }
    public Rectangle2D getRec4() {
        return rec4;
    }
   
   
    
}
