package fruitninja;

import com.sun.glass.ui.Window;
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
    private boolean[] flag = new boolean[3];
    private boolean[] s= new boolean[3];
    GameObject go;
    GameObject go1;
    GameObject go2;
    Scene scene;

    public EasyLevel(Factory factory,Scene scene) {
        
        this.actions = new TheActions();
        this.factory = factory;
        this.scene = scene;
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
                scene.setOnMouseClicked(
                (EventHandler<MouseEvent>) e -> {
                    if (go.getRec().contains(e.getX(), e.getY())) {
                        objects.remove(go);
                       s[0]=true;
                       
                    }
                   else if (go1.getRec().contains(e.getX(), e.getY())) {
                        objects.remove(go1);
                        s[1]=true;
                    }
                  else  if (go2.getRec().contains(e.getX(), e.getY())) {
                        objects.remove(go2);
                        s[2]=true;
                    }
                });
                
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
        if(s[i]==false){
        factory.drawObject(go);
        }
        if(s[i]==true){
            if(go.getObjectType()!= bomb.DEADLY && go.getObjectType()!= bomb.NORM ){
            factory.drawHalf((Fruit)go);
            
            }
            else if(go.getObjectType()==bomb.NORM)
            {
                
            }
            else if(go.getObjectType()==bomb.DEADLY){
                factory.clearCanvas();
                factory.drawMenu();
                
            }
        }
    }

    public GameObject checkEnd(GameObject go,int i) {
        if (go.getPosY() > 562) {
            flag[i]=false;
            objects.remove(go);
            go = actions.createGameObject();
            objects.add(go);
            s[i]=false;
        }
        return go;
    }

}
