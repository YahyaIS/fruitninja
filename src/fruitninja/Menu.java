package fruitninja;

import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public class Menu {

    private final Factory factory;
    private final GraphicsContext gc;
    private final Scene scene;
    private Rectangle2D rec1;
    private Rectangle2D rec2;
    private Rectangle2D rec3;
    private Rectangle2D rec4;

    public Menu(Factory factory, GraphicsContext gc, Scene scene) {
        this.factory = factory;
        this.gc = gc;
        this.scene = scene;

        this.rec1 = new Rectangle2D(110, 150, 180, 50);
        this.rec2 = new Rectangle2D(94, 250, 212, 50);
        this.rec3 = new Rectangle2D(110, 350, 180, 50);
        this.rec4 = new Rectangle2D(94, 450, 212, 50);
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
                    } else if (rec4.contains(e.getX(), e.getY())) {
                        factory.setState(4);
                        factory.setFirst(false);
                    }
                });

    }

    public void setRecs() {
        this.rec1 = new Rectangle2D(110, 150, 180, 50);
        this.rec2 = new Rectangle2D(94, 250, 212, 50);
        this.rec3 = new Rectangle2D(110, 350, 180, 50);
        this.rec4 = new Rectangle2D(94, 450, 212, 50);
    }

    public void removeRecs() {
        this.rec1 = new Rectangle2D(1000, 150, 180, 50);
        this.rec2 = new Rectangle2D(1000, 250, 212, 50);
        this.rec3 = new Rectangle2D(1000, 350, 180, 50);
        this.rec4 = new Rectangle2D(1000, 250, 212, 50);
    }
}
