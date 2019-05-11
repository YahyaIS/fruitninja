package fruitninja;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public class Menu {

    private Factory factory;
    private GraphicsContext gc;
    private Scene scene;
    private Rectangle2D rec1;
    private Rectangle2D rec2;
    private Rectangle2D rec3;
   
    public Menu(Factory factory, GraphicsContext gc, Scene scene) {
        this.factory = factory;
        this.gc = gc;
        this.scene = scene;
       
         
        this.rec1 = new Rectangle2D(110, 150, 180, 50);
        this.rec2 = new Rectangle2D(94, 250, 212, 50);
        this.rec3 = new Rectangle2D(110, 350, 180, 50);
    }

    public void Draw() {
        factory.clearCanvas();
        factory.drawBackGround();
        factory.media();
        
                factory.options();
                scene.setOnMouseClicked(
                        (EventHandler<MouseEvent>) e -> {
                            if (rec1.contains(e.getX(), e.getY())) {
                                factory.setState(1);
                                factory.setFirst(false);
                            } else if (rec2.contains(e.getX(), e.getY())) {
                                factory.setState(2);
                                factory.setFirst(false);
                            } else if (rec3.contains(e.getX(), e.getY())) {
                                factory.setState(3); 
                                factory.setFirst(false);
                            }
                        });

            
    }
}
