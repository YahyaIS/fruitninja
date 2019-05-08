package fruitninja;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Menu {

    private Factory factory;
    private GraphicsContext gc;
    private Scene scene;
    private Rectangle2D rec1;
    private Rectangle2D rec2;
    private Rectangle2D rec3;
    private EasyLevel el;

    public Menu(Factory factory, GraphicsContext gc , Scene scene) {
        this.factory = factory;
        this.gc = gc;
        this.scene=scene;
        el = new EasyLevel(factory,scene);
        this.rec1 = new Rectangle2D(410, 150, 180, 50);
        this.rec2 = new Rectangle2D(394, 250, 212, 50);
        this.rec3 = new Rectangle2D(410, 350, 180, 50);
    }
    
    public void Draw() {
                factory.drawBackGround();
                factory.options();
        new AnimationTimer() {
            @Override
            public void handle(long l) {
                scene.setOnMouseClicked(
                (EventHandler<MouseEvent>) e -> {
                    if (rec1.contains(e.getX(), e.getY())) {
                        factory.gotoLevel(el);
                    } else if (rec2.contains(e.getX(), e.getY())) {
                        
                    } else if (rec3.contains(e.getX(), e.getY())) {
                        
                    }
                });
            }
        }.start();
    }
}
