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

public class ArcadeMode implements Level {

    private final Originator originator;
    private final Momento momento;
    private final CareTaker careTaker;
    private int highscore;
    private final Factory factory;
    private final GameActions actions;
    private boolean[] flag;
    private boolean[] s;
    private boolean[] f;
    private GameObject go;
    private GameObject go1;
    private GameObject go2;
    private GameObject go3;
    private GameObject go4;
    private GameObject go5;
    private final Scene scene;
    private final Complete complete;
    private int score, time;
    String level = "Arcade";
    private double x, y;
    private boolean p = false;

    public ArcadeMode(Factory factory, Scene scene) throws ParserConfigurationException, SAXException, IOException {
        originator = new Originator();
        momento = new Momento();
        careTaker = new CareTaker();
        this.time = 60;
        this.score = 0;
        this.flag = new boolean[6];
        this.s = new boolean[6];
        this.f = new boolean[6];
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
        factory.drawP();
        factory.showHighScore(highscore);
        if (p) {
            factory.drawPause();
            factory.showPause();
            complete.setRec1();
            complete.setRec2();
        }
        if (time == 0) {
              momento.setHighscore(score);
            complete.setRec1();
            complete.setRec2();
            factory.drawW(this, score, highscore);
                   factory.saveScore(careTaker, level, score);
        }
        if (time > 0 && !p) {
            factory.showTime();
            time = factory.getSeconds();
            objectMotion(go, 0);
            objectMotion(go1, 1);
            objectMotion(go2, 2);
            objectMotion(go3, 3);
            objectMotion(go4, 4);
            objectMotion(go5, 5);
            go = checkEnd(go, 0);
            go1 = checkEnd(go1, 1);
            go2 = checkEnd(go2, 2);
            go3 = checkEnd(go3, 3);
            go4 = checkEnd(go4, 4);
            go5 = checkEnd(go5, 5);
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
                    } else if (go4.getRec().contains(e.getX(), e.getY())) {
                        s[4] = true;
                    } else if (go5.getRec().contains(e.getX(), e.getY())) {
                        s[5] = true;
                    }
                });
        factory.shadow(x, y, this);
        scene.setOnMouseClicked(
                (EventHandler<MouseEvent>) e -> {
                    if (complete.getRec1().contains(e.getX(), e.getY())) {
                        p = false;
                        factory.setState(0);
                    } else if (complete.getRec2().contains(e.getX(), e.getY())) {
                        p = false;
                        factory.setState(4);
                        initGame();
                    } else if (complete.getRec3().contains(e.getX(), e.getY())) {
                        p = true;
                    } else if (complete.getRec4().contains(e.getX(), e.getY())) {
                        complete.removeRecs();
                        factory.setSeconds(time);
                        p = false;

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
            if (go.getObjectType() != Bombs.bomb.DEADLY && go.getObjectType() != Bombs.bomb.NORM && go.getObjectType() != Fruit.fruit.POWERFRUIT) {
                if (f[i] == false) {
                    factory.swordSound();
                    score = factory.setScore((Fruit) go, score);
                    f[i] = true;
                }
                actions.updateHalf((Fruit) go);
                factory.drawHalf((Fruit) go);
            }
            if (go.getObjectType() == Bombs.bomb.NORM) {
                if (f[i] == false) {
                    factory.bombSound();
                    go.setPosY(563);
                    f[i] = true;
                }
                actions.updateObjectPlace(go);
            }
            if (go.getObjectType() == Bombs.bomb.DEADLY) {
                factory.redSound();
            }
            if (go.getObjectType() == Fruit.fruit.POWERFRUIT) {
                if (f[i] == false) {
                    f[i] = true;
                    if (this.go.getObjectType() != Bombs.bomb.DEADLY && this.go.getObjectType() != Bombs.bomb.NORM) {
                        s[0] = true;
                    }
                    if (this.go1.getObjectType() != Bombs.bomb.DEADLY && this.go1.getObjectType() != Bombs.bomb.NORM) {
                        s[1] = true;
                    }
                    if (this.go2.getObjectType() != Bombs.bomb.DEADLY && this.go2.getObjectType() != Bombs.bomb.NORM) {
                        s[2] = true;
                    }
                    if (this.go3.getObjectType() != Bombs.bomb.DEADLY && this.go3.getObjectType() != Bombs.bomb.NORM) {
                        s[3] = true;
                    }
                    if (this.go4.getObjectType() != Bombs.bomb.DEADLY && this.go4.getObjectType() != Bombs.bomb.NORM) {
                        s[4] = true;
                    }
                    if (this.go5.getObjectType() != Bombs.bomb.DEADLY && this.go5.getObjectType() != Bombs.bomb.NORM) {
                        s[5] = true;
                    }
                }
                actions.updateHalf((Fruit) go);
                factory.drawHalf((Fruit) go);
            }
        }
    }

    public GameObject checkEnd(GameObject go, int i) {
        if (go.getPosY() > 562) {
            if (s[i] == false && (go.getObjectType() == Fruit.fruit.APPLE || go.getObjectType() == Fruit.fruit.BANANA
                    || go.getObjectType() == Fruit.fruit.MELON)) {
            }
            flag[i] = false;
            go = actions.createGameObject(factory.getState());
            s[i] = false;
            f[i] = false;
        }
        return go;
    }

    public void initGame() {
        factory.loadScore(originator, level, this);
        this.go5 = actions.createGameObject(factory.getState());
        this.go4 = actions.createGameObject(factory.getState());
        this.go3 = actions.createGameObject(factory.getState());
        this.go2 = actions.createGameObject(factory.getState());
        this.go1 = actions.createGameObject(factory.getState());
        this.go = actions.createGameObject(factory.getState());
        factory.setSeconds(60);
        score = 0;
        time = 60;
        p = false;
        complete.removeRecs();
        for (int i = 0; i < 6; i++) {
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
