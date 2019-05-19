package fruitninja;

import Momento.CareTaker;
import Momento.Originator;
import command.Command;
import command.Invoker;
import command.LoadCommand;
import command.SaveCommand;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.TextAlignment;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class Factory {
    
    private Invoker control = new Invoker();
    private final String uriString ,sliString ,bombString,redString;
    private final MediaPlayer player;
    private  MediaPlayer sliceSound;
    private final GraphicsContext gc;
    private final Scene scene;
    private final Menu menu;
    private final Warnning warnning;
    private int state;
    private final EasyLevel el;
    private final MediumLevel ml;
    private final HardLevel hl;
    private boolean first;
    private Timer timer;
    private int seconds;
    private final Complete complete; 
    private Background bg;
    TimerTask task = new TimerTask() {

        @Override
        public void run() {
            seconds++;
        }
    };
    
    public Factory(GraphicsContext gc, Scene scene) throws ParserConfigurationException, SAXException, IOException  {
        this.state = 0;
        this.uriString = new File("roc.mp3").toURI().toString();
        this.sliString = new File("sword.mp3").toURI().toString();
        this.redString = new File("explode.mp3").toURI().toString();
        this.bombString = new File("explode2.mp3").toURI().toString();
        this.player = new MediaPlayer(new Media(uriString));
        this.gc = gc;
        this.scene = scene;
        this.menu = new Menu(this, gc, scene);
        this.complete = new Complete();
        this.warnning = new Warnning();
        el = new EasyLevel(this, scene);
        ml = new MediumLevel(this, scene);
        hl = new HardLevel(this, scene);
        bg=Background.getInstance();
    }
    
    public void saveScore(CareTaker careTaker , String level , int score){
        Command saveC = new SaveCommand(careTaker, score, level);
        control.setCommand(saveC);
        control.action();
    }
    
    public void loadScore(Originator originator , String level , Level l){
        Command loadC = new LoadCommand(originator, level, l);
        control.setCommand(loadC);
        control.action();
    }
    
    public void shadow(double x ,double y , Level level){
        gc.setFill(Color.BLUEVIOLET);
        gc.fillOval(x, y, 10, 10);
        level.setXY(-15,-15);
    }
    
    public void time(){
        timer = new Timer();
        timer.schedule(task, 1000, 1000);
    }
    
    public void showTime(){
        gc.setFill(Color.BLACK);
        gc.fillText("Time : " + seconds, 70, 45);
    }
    
    public void media() {
        player.setCycleCount(state);
        player.play();
    }
    
    public void redSound(){
        this.sliceSound = new MediaPlayer(new Media(redString));
        sliceSound.play();
    }
    
    public void bombSound(){
        this.sliceSound = new MediaPlayer(new Media(bombString));
        sliceSound.play();
    }
    
    public void swordSound(){
        this.sliceSound = new MediaPlayer(new Media(sliString));
        sliceSound.play();
    }
    
    public void gameState() {
        media();
        time();
        new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (state == 0) {
                    menu.setRecs();
                    drawMenu();
                } else if (state == 1) {
                    menu.removeRecs();
                    if(!first){
                        try {
                            el.initGame();
                            first=true;
                        } catch (ParserConfigurationException ex) {
                            Logger.getLogger(Factory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(Factory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                        } catch (SAXException ex) {
                            Logger.getLogger(Factory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                        }
                    }
                    gotoLevel(el);
                } else if (state == 2) {
                    menu.removeRecs();
                    if(!first){
                        try {
                            ml.initGame();
                        } catch (ParserConfigurationException ex) {
                            Logger.getLogger(Factory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                        } catch (SAXException ex) {
                            Logger.getLogger(Factory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(Factory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                        }
                        first=true;
                    }
                    gotoLevel(ml);
                } else if (state == 3) {
                    menu.removeRecs();
                    if(!first){
                        try {
                            hl.initGame();
                        } catch (ParserConfigurationException ex) {
                            Logger.getLogger(Factory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                        } catch (SAXException ex) {
                            Logger.getLogger(Factory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(Factory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                        }
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
        gc.setFill(Color.BLACK);
        gc.fillText("Score : " + score, 70, 15);
    }
    public void showHighScore(int score) {
        gc.setFill(Color.BLACK);
        gc.fillText("High score : " + score, 300, 15);
    }
    
    public void drawW(Level level){
        gc.drawImage(complete.getWImage(), 300, 125);
        gc.drawImage(complete.getRImage(), 610, 315);
        gc.drawImage(complete.getBImage(), 325, 315);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.setFill(Color.RED);
        gc.fillText("You Lost!", 500, 180);
        gc.fillText("Score :" + level.getScore(), 505, 220);
        gc.fillText("Time :" + level.getTime(), 505, 260);
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
        bg.drawBackground(gc);
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getSeconds() {
        return seconds;
    }


}
