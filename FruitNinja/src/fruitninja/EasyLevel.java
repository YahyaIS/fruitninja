package fruitninja;

import fruitninja.Bombs.bomb;
import fruitninja.Fruit.fruit;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

public class EasyLevel implements Level {

    private Factory factory;
    private GameActions actions;
    private List<GameObject> objects = new ArrayList<>();
    private boolean[] flag;
    private boolean[] s;
    private boolean[] f;
    private GameObject go;
    private GameObject go1;
    private GameObject go2;
    private Scene scene;
    private int score, lives;

    public EasyLevel(Factory factory, Scene scene) {
        this.lives = 3;
        this.score = 0;
        this.flag = new boolean[3];
        this.s = new boolean[3];
        this.f = new boolean[3];
        this.actions = new TheActions();
        this.factory = factory;
        this.scene = scene;
    }

    @Override
    public void manageLevel() {
        initGame();
        new AnimationTimer() {
            @Override
            public void handle(long l) {
                factory.clearCanvas();
                factory.drawBackGround();
                factory.showScore(score);
                objectMotion(go, 0);
                objectMotion(go1, 1);
                objectMotion(go2, 2);
                go = checkEnd(go, 0);
                go1 = checkEnd(go1, 1);
                go2 = checkEnd(go2, 2);
                scene.setOnMouseClicked(
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
                            }
                        });

            }
        }.start();
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
                    score = factory.setScore((Fruit) go, score);
                    f[i] = true;
                }
                actions.updateHalf((Fruit) go);
                factory.drawHalf((Fruit) go);
            } else if (go.getObjectType() == bomb.NORM) {
                if (f[i] == false) {
                    lives--;
                    go.setPosY(563);
                    f[i] = true;
                }
                actions.updateObjectPlace(go);
            } else if (go.getObjectType() == bomb.DEADLY) {
                factory.drawMenu();
            }
        }
    }

    public GameObject checkEnd(GameObject go, int i) {
        if (go.getPosY() > 562) {
            if(s[i] == false&&(go.getObjectType() == fruit.APPLE ||go.getObjectType() == fruit.BANANA
                    || go.getObjectType() == fruit.MELON)){
            lives--;
            }
            flag[i] = false;
            objects.remove(go);
            go = actions.createGameObject();
            objects.add(go);
            s[i] = false;
            f[i] = false;
        }
        if (lives == 0) {
            factory.drawMenu();
        }
        return go;
    }

    public void initGame() {
        this.go2 = actions.createGameObject();
        this.go1 = actions.createGameObject();
        this.go = actions.createGameObject();
        objects.add(go);
        objects.add(go1);
        objects.add(go2);
        score = 0;
        lives = 3;
        for (int i = 0; i < 3; i++) {
            this.flag[i] = false;
            this.s[i] = false;
            this.f[i] = false;
        }
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

}
