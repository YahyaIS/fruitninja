/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruitninja;

import Momento.CareTaker;
import Momento.Momento;
import Momento.Originator;
import java.io.IOException;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author seif
 */
public class MediumLevel implements Level {

    private final Factory factory;
    private final Originator originator;
    private final Momento momento;
    private final CareTaker careTaker;
    private final GameActions actions;
    private boolean[] flag;
    private boolean[] s;
    private boolean[] f;
    private GameObject go;
    private GameObject go1;
    private GameObject go2;
    private GameObject go3;
    private final Scene scene;
    private int score, lives, time;
    private final Complete complete;
    private int highscore;
    String level = "Medium";
    private double x, y;

    public MediumLevel(Factory factory, Scene scene) throws ParserConfigurationException, SAXException, IOException {
        originator = new Originator();
        momento = new Momento();
        careTaker = new CareTaker();
        this.lives = 3;
        this.score = 0;
        this.flag = new boolean[4];
        this.s = new boolean[4];
        this.f = new boolean[4];
        this.actions = new TheActions();
        this.factory = factory;
        this.scene = scene;
        this.complete = new Complete();
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
            factory.showTime();
            time = factory.getSeconds();
            objectMotion(go, 0);
            objectMotion(go1, 1);
            objectMotion(go2, 2);
            objectMotion(go3, 3);
            go = checkEnd(go, 0);
            go1 = checkEnd(go1, 1);
            go2 = checkEnd(go2, 2);
            go3 = checkEnd(go3, 3);
        }
        scene.setOnMouseDragged(
                (EventHandler<MouseEvent>) e -> {
                    x = e.getX();
                    y = e.getY();
                    if (go.getRec().contains(e.getX(), e.getY())) {
                        s[0] = true;
                    } else if (go1.getRec().contains(e.getX(), e.getY())) {
                        s[1] = true;
                    } else if (go2.getRec().contains(e.getX(), e.getY())) {
                        s[2] = true;
                    } else if (go3.getRec().contains(e.getX(), e.getY())) {
                        s[3] = true;
                    }
                });
        factory.shadow(x, y, this);
        scene.setOnMouseClicked(
                (EventHandler<MouseEvent>) e -> {

                    if (complete.getRec1().contains(e.getX(), e.getY())) {
                        factory.setState(0);
                    } else if (complete.getRec2().contains(e.getX(), e.getY())) {
                        factory.setState(2);
                        try {
                            initGame();
                        } catch (ParserConfigurationException ex) {
                            Logger.getLogger(MediumLevel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                        } catch (SAXException ex) {
                            Logger.getLogger(MediumLevel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(MediumLevel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
            if (go.getObjectType() != Bombs.bomb.DEADLY && go.getObjectType() != Bombs.bomb.NORM) {
                if (f[i] == false) {
                    factory.swordSound();
                    score = factory.setScore((Fruit) go, score);
                    f[i] = true;
                }
                actions.updateHalf((Fruit) go);
                factory.drawHalf((Fruit) go);
            } else if (go.getObjectType() == Bombs.bomb.NORM) {
                factory.bombSound();
                if (f[i] == false) {
                    lives--;
                    go.setPosY(563);
                    f[i] = true;

                }
                actions.updateObjectPlace(go);
            } else if (go.getObjectType() == Bombs.bomb.DEADLY) {
                factory.redSound();
                lives = 0;
            }
        }
    }

    public GameObject checkEnd(GameObject go, int i) {
        if (go.getPosY() > 562) {
            if (s[i] == false && (go.getObjectType() == Fruit.fruit.APPLE || go.getObjectType() == Fruit.fruit.BANANA
                    || go.getObjectType() == Fruit.fruit.MELON)) {
                lives--;
            }
            flag[i] = false;
            go = actions.createGameObject();
            s[i] = false;
            f[i] = false;
        }
        return go;
    }

    public void initGame() throws ParserConfigurationException, SAXException, IOException {
        factory.loadScore(originator, level, this);
        this.go3 = actions.createGameObject();
        this.go2 = actions.createGameObject();
        this.go1 = actions.createGameObject();
        this.go = actions.createGameObject();
        factory.setSeconds(0);
        score = 0;
        lives = 3;
        complete.removeRecs();
        for (int i = 0; i < 4; i++) {
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

    public GameObject getGo3() {
        return go3;
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
