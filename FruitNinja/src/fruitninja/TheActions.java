package fruitninja;

import java.util.Random;

public class TheActions implements GameActions {

    float deltaX, deltaY;

    @Override
    public GameObject createGameObject() {
        Random r = new Random();
        int i = (int) (r.nextDouble() * 11);
        if (i <= 2) {
            Fruit melon = new Melon();
            return melon;
        } else if (i <= 4) {
            Fruit banana = new Banana();
            return banana;
        } else if (i <= 6) {
            Fruit apple = new Apple();
            return apple;
        } else if (i <= 7) {
            Bombs redBomb = new RedBomb();
            return redBomb;
        } else if (i <= 9) {
            Bombs regBomb = new RegularBomb();
            return regBomb;
        }else if (i <= 10){
            SuperFruit1 superFruit1 = new SuperFruit1();
            return superFruit1;
        }else if(i <=11){
            SuperFruit2 superFruit2 = new SuperFruit2();
            return superFruit2;
        }
        else {
            return null;
        }
    }

    @Override
    public void updateObjectPlace(GameObject go) {
        go.setPosX(go.getDeltaX());
        go.setPosY(go.getDeltaY());
        go.setRec();
    }

    @Override
    public void sliceObjects() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public float getDeltaX() {
        return deltaX;
    }

    @Override
    public void setDeltaX(float deltaX) {
        this.deltaX = deltaX;
    }

    public float getDeltaY() {
        return deltaY;
    }

    @Override
    public void setDeltaY(float deltaY) {
        this.deltaY = deltaY;
    }

}
