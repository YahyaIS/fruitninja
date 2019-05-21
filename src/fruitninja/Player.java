
package fruitninja;

import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;


public class Player {
    private final Factory factory;
    private final GraphicsContext gc;
    private final Scene scene;
    private Rectangle2D login;
    private Rectangle2D signup;
    public Player(Factory factory,GraphicsContext gc,Scene scene){
        this.factory = factory;
        this.gc = gc;
        this.scene = scene;
        this.login = new Rectangle2D(110, 150, 180, 50);
        this.signup = new Rectangle2D(94, 250, 212, 50);
    }
    public void Draw(){
        factory.clearCanvas();
        factory.drawBackGround();
        factory.media();
        
            factory.playerOptions();
        scene.setOnMouseClicked((EventHandler<MouseEvent>)e ->{
            if(login.contains(e.getX(), e.getY())){
                factory.setState(5);
                factory.setFirst(false);
            }
            else if(signup.contains(e.getX(),e.getY())){
                factory.setState(6);
                factory.setFirst(false);
            }
        });
    }
    public void setRecs(){
        this.login = new Rectangle2D(110, 150, 180, 50);
        this.signup = new Rectangle2D(94, 250, 212, 50);
    }
    public void removeRecs(){
        this.login = new Rectangle2D(1000, 150, 180, 50);
        this.signup = new Rectangle2D(1000, 250, 212, 50);
    }

   
}
