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
public class Warnning {
    private BufferedImage br =new BufferedImage(100,200,BufferedImage.TYPE_INT_RGB);
    private BufferedImage bg =new BufferedImage(100,200,BufferedImage.TYPE_INT_RGB);
    public Warnning(){
        File input1=new File("gx.png");
        try {
            bg= ImageIO.read(input1);
        }
        catch(IOException ex) {
            Logger.getLogger(Side.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        File input2=new File("rx.png");
        try {
            br= ImageIO.read(input2);
        }
        catch(IOException ex) {
            Logger.getLogger(Side.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
    public javafx.scene.image.Image getGImage(){
        Image image = SwingFXUtils.toFXImage(this.bg, null);
        return  image;
    }
    
    public javafx.scene.image.Image getRImage(){
        Image image = SwingFXUtils.toFXImage(this.br, null);
        return  image;
    }
    
    
}
