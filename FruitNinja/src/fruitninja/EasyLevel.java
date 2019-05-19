package fruitninja;

import Momento.CareTaker;
import Momento.Momento;
import Momento.Originator;
import fruitninja.Bombs.bomb;
import fruitninja.Fruit.fruit;
import java.io.IOException;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class EasyLevel implements Level {

    private final String level = "easy";
    private final Factory factory;
    private final GameActions actions;
    private boolean[] flag;
    private boolean[] s;
    private boolean[] f;
    private GameObject go;
    private GameObject go1;
    private GameObject go2;
    private final Scene scene;
    private final Complete complete;
    private int score, lives, time;
    private final Originator originator;
    private final Momento momento;
    private final CareTaker careTaker;
    private int highscore;
    private double x, y;

    public EasyLevel(Factory factory, Scene scene) throws ParserConfigurationException, SAXException, IOException {
        originator = new Originator();
        momento = new Momento();
        careTaker = new CareTaker();
        this.lives = 3;
        this.score = 0;
        this.flag = new boolean[3];
        this.s = new boolean[3];
        this.f = new boolean[3];
        this.actions = new TheActions();
        this.factory = factory;
        this.complete = new Complete();
        this.scene = scene;
        this.factory.loadScore(this.originator, this.level, this);
    }

    @Override
    public void manageLevel() {

        factory.clearCanvas();
        factory.drawBackGround();
        factory.showScore(score);
        factory.showHighScore(highscore);
        factory.drawGx(780, 25);
        factory.drawGx(860, 25);
        factory.drawGx(940, 25);

        if (lives == 2) {
            factory.drawRx(940, 25);
        } else if (lives == 1) {
            factory.drawRx(940, 25);
            factory.drawRx(860, 25);
        } else if (lives == 0) {
            momento.setHighscore(score);
            factory.drawRx(940, 25);
            factory.drawRx(860, 25);
            factory.drawRx(780, 25);
            complete.setRec1();
            complete.setRec2();
            factory.drawW(this);
            factory.saveScore(careTaker, level, score);
        }
        if (lives > 0) {
            try {
                factory.showTime();
                time = factory.getSeconds();
                objectMotion(go, 0);
                objectMotion(go1, 1);
                objectMotion(go2, 2);
                go = checkEnd(go, 0);
                go1 = checkEnd(go1, 1);
                go2 = checkEnd(go2, 2);
            } catch (ParserConfigurationException | IOException | SAXException ex) {
                Logger.getLogger(EasyLevel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }
        scene.setOnMouseDragged(
                (EventHandler<MouseEvent>) e -> {
                    x = e.getX();
                    y = e.getY();
                    if (go.getRec().contains(e.getX(), e.getY())) {
                        s[0] = true;
                    }
                    if (go1.getRec().contains(e.getX(), e.getY())) {
                        s[1] = true;
                    }
                    if (go2.getRec().contains(e.getX(), e.getY())) {
                        s[2] = true;
                    }
                });
        factory.shadow(x, y, this);
        scene.setOnMouseClicked(
                (EventHandler<MouseEvent>) e -> {
                    if (complete.getRec1().contains(e.getX(), e.getY())) {
                        factory.setState(0);
                    } else if (complete.getRec2().contains(e.getX(), e.getY())) {
                        factory.setState(1);
                        try {
                            initGame();
                        } catch (ParserConfigurationException | IOException | SAXException ex) {
                            Logger.getLogger(EasyLevel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                        }
                    }
                });

    }

    public void objectMotion(GameObject go, int i) {
        if (flag[i] == true || s[i] == true) {
            go.setDeltaY(go.getDownY());
        } else if (go.getPosY() <= 30) {
            flag[i] = true;
        }
        if (s[i] == false) {
            actions.updateObjectPlace(go);
            factory.drawObject(go);
        } else if (s[i] == true) {
            if (go.getObjectType() != bomb.DEADLY && go.getObjectType() != bomb.NORM) {
                if (f[i] == false) {
                    factory.swordSound();
                    score = factory.setScore((Fruit) go, score);
                    f[i] = true;
                }
                actions.updateHalf((Fruit) go);
                factory.drawHalf((Fruit) go);
            } else if (go.getObjectType() == bomb.NORM) {
                if (f[i] == false) {
                    factory.bombSound();
                    lives--;
                    go.setPosY(563);
                    f[i] = true;

                }
                actions.updateObjectPlace(go);
            } else if (go.getObjectType() == bomb.DEADLY) {
                factory.redSound();
                lives = 0;
            }
        }
    }

    public GameObject checkEnd(GameObject go, int i) throws ParserConfigurationException, IOException, SAXException {
        if (go.getPosY() > 562) {
            if (s[i] == false && (go.getObjectType() == fruit.APPLE || go.getObjectType() == fruit.BANANA
                    || go.getObjectType() == fruit.MELON)) {
                lives--;
            }
            flag[i] = false;
            go = actions.createGameObject();
            s[i] = false;
            f[i] = false;

        }
        return go;
    }

    public void initGame() throws ParserConfigurationException, IOException, SAXException {
        factory.loadScore(originator, level, this);
        this.go2 = actions.createGameObject();
        this.go1 = actions.createGameObject();
        this.go = actions.createGameObject();
        factory.setSeconds(0);
        score = 0;
        lives = 3;
        complete.removeRecs();
        for (int i = 0; i < 3; i++) {
            this.flag[i] = false;
            this.s[i] = false;
            this.f[i] = false;
        }
    }

    @Override
    public int getScore() {
        return score;
    }

    public GameObject getGo() {
        return go;
    }

    public GameObject getGo1() {
        return go1;
    }

    public GameObject getGo2() {
        return go2;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    @Override
    public int getTime() {
        return time;
    }

    @Override
    public void setHighScore(int highscore) {
        this.highscore = highscore;
    }

    @Override
    public void setXY(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
