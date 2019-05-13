package fruitninja;

import java.io.File;
import javafx.animation.AnimationTimer;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.TextAlignment;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class Factory {

    private final String uriString;
    private final MediaPlayer player;
    private final GraphicsContext gc;
    private final Scene scene;
    private final Menu menu;
    private final Warnning warnning;
    private int state;
    private final EasyLevel el;
    private final MediumLevel ml;
    private final HardLevel hl;
    private boolean first;
    private int seconds=0;
    private Complete complete; 
    public Factory(GraphicsContext gc, Scene scene) {
        this.state = 0;
        this.uriString = new File("roc.mp3").toURI().toString();
        this.player = new MediaPlayer(new Media(uriString));
        this.gc = gc;
        this.scene = scene;
        this.menu = new Menu(this, gc, scene);
        this.complete = new Complete();
        this.warnning = new Warnning();
        el = new EasyLevel(this, scene);
        ml = new MediumLevel(this, scene);
        hl = new HardLevel(this, scene);
    }

    public void media() {
        player.play();
    }
    
    
//    public void doTime() {
//  Timeline time= new Timeline();
//  
//  
// KeyFrame frame= new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>(){
//     
//   @Override
//   public void handle(ActionEvent event) {
//     gc.setTextAlign(TextAlignment.CENTER);
//     gc.setTextBaseline(VPos.CENTER);
//     gc.setFill(Color.BLACK);
//     gc.setFont(Font.font(35));
//       System.out.println(seconds);
//     gc.fillText(""+seconds, 500, 170);
//    seconds++;
//    
//   }
//  });
//  
//  time.setCycleCount(Timeline.INDEFINITE);
//  time.getKeyFrames().add(frame);
//  if(time!=null){
//   time.stop();
//  }
//  time.play();
//  
// }
     

    public void gameState() {
        media();
        new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (state == 0) {
                    menu.setRecs();
                    drawMenu();
                } else if (state == 1) {
                    menu.removeRecs();
                    if(!first){
                        el.initGame();
                        first=true;
                    }
                    gotoLevel(el);
                } else if (state == 2) {
                    menu.removeRecs();
                    if(!first){
                        ml.initGame();
                        first=true;
                    }
                    gotoLevel(ml);
                } else if (state == 3) {
                    menu.removeRecs();
                    if(!first){
                        hl.initGame();
                        first=true;
                    }
                    gotoLevel(hl);
                }
            }
        }.start();
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public void gotoLevel(Level level) {
        level.manageLevel();
    }

    public void clearCanvas() {
        gc.clearRect(0, 0, 1000, 562);
    }

    public void drawObject(GameObject object) {
        gc.drawImage(object.getImage(), object.getPosX(), object.getPosY());
    }

    public void drawHalf(Fruit object) {
        gc.drawImage(object.getImage1(), object.getHalfX1(), object.getPosY());
        gc.drawImage(object.getImage2(), object.getHalfX2(), object.getPosY());
    }

    public void drawRx(int x, int y) {
        gc.drawImage(warnning.getRImage(), x, y);
    }

    public void drawGx(int x, int y) {
        gc.drawImage(warnning.getGImage(), x, y);
    }

    public int setScore(Fruit go, int score) {
        score += go.getPoints();
        return score;
    }

    public void showScore(int score) {
        gc.fillText("Score : " + score, 70, 15);
    }
    
    public void drawW(){
        gc.drawImage(complete.getWImage(), 300, 125);
        gc.drawImage(complete.getRImage(), 610, 315);
        gc.drawImage(complete.getBImage(), 325, 315);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.setFill(Color.RED);
        gc.fillText("You Lost!", 500, 180);
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

    public void setState(int state) {
        this.state = state;
    }


}
