/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruitninja;

import java.util.ArrayList;
import java.util.List;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author seif
 */
public class HardLevel implements Level {

    private final Factory factory;
    private final GameActions actions;
    private List<GameObject> objects = new ArrayList<>();
    private boolean[] flag;
    private boolean[] s;
    private boolean[] f;
    private GameObject go;
    private GameObject go1;
    private GameObject go2;
    private GameObject go3;
    private GameObject go4;
    private final Scene scene;
    private final Complete complete;
    private int score, lives;

    public HardLevel(Factory factory, Scene scene) {
        this.lives = 3;
        this.score = 0;
        this.flag = new boolean[5];
        this.s = new boolean[5];
        this.f = new boolean[5];
        this.actions = new TheActions();
        this.factory = factory;
        this.scene = scene;
        this.complete = new Complete();
    }

    @Override
    public void manageLevel() {
        factory.clearCanvas();
        factory.drawBackGround();
        factory.showScore(score);
        factory.drawGx(780, 25);
        factory.drawGx(860, 25);
        factory.drawGx(940, 25);

        if (lives == 2) {
            factory.drawRx(940, 25);

        } else if (lives == 1) {
            factory.drawRx(940, 25);
            factory.drawRx(860, 25);
        } else if (lives == 0) {
            factory.drawRx(940, 25);
            factory.drawRx(860, 25);
            factory.drawRx(780, 25);
            complete.setRec1();
            complete.setRec2();
            factory.drawW(this);
        }
        if (lives > 0) {
            objectMotion(go, 0);
            objectMotion(go1, 1);
            objectMotion(go2, 2);
            objectMotion(go3, 3);
            objectMotion(go4, 4);
            go = checkEnd(go, 0);
            go1 = checkEnd(go1, 1);
            go2 = checkEnd(go2, 2);
            go3 = checkEnd(go3, 3);
            go4 = checkEnd(go4, 4);
        }
        scene.setOnMouseDragged(
                (EventHandler<MouseEvent>) e -> {
                    if (go.getRec().contains(e.getX(), e.getY())) {
                        objects.remove(go);
                        s[0] = true;
                    } else if (go1.getRec().contains(e.getX(), e.getY())) {
                        objects.remove(go1);
                        s[1] = true;
                    } else if (go2.getRec().contains(e.getX(), e.getY())) {
                        objects.remove(go2);
                        s[2] = true;
                    } else if (go3.getRec().contains(e.getX(), e.getY())) {
                        objects.remove(go3);
                        s[3] = true;
                    } else if (go4.getRec().contains(e.getX(), e.getY())) {
                        objects.remove(go4);
                        s[4] = true;
                    }
                });
        scene.setOnMouseClicked(
                (EventHandler<MouseEvent>) e -> {
                    if (complete.getRec1().contains(e.getX(), e.getY())) {
                        factory.setState(0);
                    } else if (complete.getRec2().contains(e.getX(), e.getY())) {
                        factory.setState(3);
                        initGame();
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
                if (f[i] == false) {
                    factory.bombSound();
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
            objects.remove(go);
            go = actions.createGameObject();
            objects.add(go);
            s[i] = false;
            f[i] = false;
        }
        return go;
    }

    public void initGame() {
        this.go4 = actions.createGameObject();
        this.go3 = actions.createGameObject();
        this.go2 = actions.createGameObject();
        this.go1 = actions.createGameObject();
        this.go = actions.createGameObject();
        objects.add(go);
        objects.add(go1);
        objects.add(go2);
        objects.add(go3);
        objects.add(go4);
        score = 0;
        lives = 3;
        complete.removeRecs();
        for (int i = 0; i < 5; i++) {
            this.flag[i] = false;
            this.s[i] = false;
            this.f[i] = false;
        }
    }

    @Override
    public int getScore(){
       return score ;
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
}
