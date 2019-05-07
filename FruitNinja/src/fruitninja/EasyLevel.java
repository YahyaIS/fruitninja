package fruitninja;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;

public class EasyLevel implements Level {

    private Factory factory;
    private GameActions actions;
    private List<GameObject> objects = new ArrayList<>();
    private boolean[] flag = new boolean[3];
    GameObject go;
    GameObject go1;
    GameObject go2;

    public EasyLevel(Factory factory) {
        
        this.actions = new TheActions();
        this.factory = factory;

    }

    @Override
    public void manageLevel() {
        this.go2 = actions.createGameObject();
        this.go1 = actions.createGameObject();
        this.go = actions.createGameObject();
        objects.add(go);
        objects.add(go1);
        objects.add(go2);
        new AnimationTimer() {
            @Override
            public void handle(long l) {
                factory.clearCanvas();
                factory.drawBackGround();
                objectMotion(go, 0);
                objectMotion(go1, 1);
                objectMotion(go2, 2);
                go = checkEnd(go,0);
                go1 = checkEnd(go1,1);
                go2= checkEnd(go2,2);
            }
        }.start();
    }

    public void objectMotion(GameObject go, int i) {
        if(flag[i]==true){
            go.setDeltaY(go.getDownY());
        }
        if(go.getPosY()<=30){
            flag[i]=true;
            
        }
        actions.updateObjectPlace(go);
        factory.drawObject(go);

    }

    public GameObject checkEnd(GameObject go,int i) {
        if (go.getPosY() > 562) {
            flag[i]=false;
            objects.remove(go);
            go = actions.createGameObject();
            objects.add(go);
        }
        return go;
    }

}
