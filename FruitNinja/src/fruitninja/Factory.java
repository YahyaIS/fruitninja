package fruitninja;

import java.io.File;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Factory {
    String uriString;
    MediaPlayer player;
    private GraphicsContext gc;
    private Scene scene;
    Menu menu;
    Warnning warnning;
    public Factory(GraphicsContext gc,Scene scene) {
        this.uriString = new File("roc.mp3").toURI().toString();
        this.player = new MediaPlayer( new Media(uriString));
        this.gc = gc;
        this.scene = scene;
        this.menu = new Menu(this, gc,scene);
        this.warnning = new Warnning();
    }
    public void media(){
    player.play();
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
        gc.drawImage(object.getImage1(), object.getHalfX1(), object.getPosY());
        gc.drawImage(object.getImage2(), object.getHalfX2(), object.getPosY());
    }
    
    public void drawRx(int x, int y){
        gc.drawImage(warnning.getRImage(), x, y);
    }
    
    public void drawGx(int x, int y){
        gc.drawImage(warnning.getGImage(),x, y);
    }
    
    
    
    public int setScore(Fruit go, int score){
        score+=go.getPoints();
        return score;
    }
    
    public void showScore(int score){
        gc.fillText("Score : "+ score, 70, 15);
    }
    
    public void options() {
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.setFill(Color.BLACK);
        gc.fillRect(110, 150, 180, 50);
        gc.fillRect(94, 250, 212, 50);
        gc.fillRect(110, 350, 180, 50);
        gc.setFill(Color.BURLYWOOD);
        gc.setFont(Font.font(35));
        gc.fillText("Easy Level", 200, 170);
        gc.fillText("Medium Level", 200, 270);
        gc.fillText("Hard Level", 200, 370);
        
    }

    public void drawMenu() {
        menu.Draw();
    }

    public void drawBackGround() {
        Background bg = new Background(gc);
    }
}
