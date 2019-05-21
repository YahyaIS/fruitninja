
package fruitninja;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


public class Login {
    private final Factory factory;
    private final userauthentication userauth;
    private final GraphicsContext gc;
    private final Scene scene;
    private Rectangle2D user;
    private Rectangle2D pw;
    
    private Rectangle2D login;
    private Label v;
    
    public Login(Factory factory,GraphicsContext gc,Scene scene){
        this.factory = factory;
        this.userauth = new userauthentication();
        this.gc =gc;
        this.scene=scene;
        this.login = new Rectangle2D(110, 550, 180, 50);
        this.user=new Rectangle2D(110, 150, 180, 50);
       
        this.pw = new Rectangle2D(110, 350, 180, 50);
        
        this.v = new Label();
        v.setTranslateX(110);
        v.setTranslateY(650);
    }
    TextField userN = new TextField();
    TextField userP = new TextField();
    
    boolean validation;
    
    public void Draw(){
        factory.clearCanvas();
        factory.drawBackGround();
        factory.media();
            
            userN.setTranslateX(110);
            userN.setTranslateY(250);
            userP.setTranslateX(110);
            userP.setTranslateY(450);
            factory.loginOptions();
            scene.setOnMouseClicked((EventHandler<MouseEvent>)e->{
            try {
                if(login.contains(e.getX(),e.getY())){
                userauth.validate(userN.getText(), userP.getText());
                if(validation){
                    factory.setState(0);
                    factory.setFirst(false);
                }
                else {
                    v.setText("INVALID USERNAME OR PASSWORD");
                }
                }
            } catch (IOException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
            });
    }
}
