package fruitninja;

import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Factory {

    private GraphicsContext gc;
    private Scene scene;

    public Factory(GraphicsContext gc,Scene scene) {
        this.gc = gc;
        this.scene = scene;
    }
    
    public void gotoLevel(Level level) {
        level.manageLevel();
    }
    
    public void clearCanvas(){
        gc.clearRect(0, 0, 1000, 562);
    }
    
    public void drawObject(GameObject object){
        gc.drawImage(object.getImage(), object.getPosX(), object.getPosY());
    }
    public void drawHalf(Fruit object){
        gc.drawImage(object.getImage1(), object.getPosX(), object.getPosY());
        gc.drawImage(object.getImage2(), object.getPosX(), object.getPosY());
    }
    
    public void options() {
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.setFill(Color.BLACK);
        gc.fillRect(410, 150, 180, 50);
        gc.fillRect(394, 250, 212, 50);
        gc.fillRect(410, 350, 180, 50);
        gc.setFill(Color.BURLYWOOD);
        gc.setFont(Font.font(35));
        gc.fillText("Easy Level", 500, 170);
        gc.fillText("Medium Level", 500, 270);
        gc.fillText("Hard Level", 500, 370);
        
    }

    public void drawMenu() {
        Menu menu = new Menu(this, gc,scene);
        menu.Draw();
    }

    public void drawBackGround() {
        Background bg = new Background(gc);
    }
}
