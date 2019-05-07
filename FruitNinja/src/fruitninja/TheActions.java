package fruitninja;

import java.util.Random;

public class TheActions implements GameActions {

    float deltaX, deltaY;

    @Override
    public GameObject createGameObject() {
        Random r = new Random();
        int i = (int) (r.nextDouble() * 5);
        if (i == 0) {
            Fruit melon = new Melon();
            return melon;
        } else if (i == 1) {
            Fruit banana = new Banana();
            return banana;
        } else if (i == 2) {
            Fruit apple = new Apple();
            return apple;
        } else if (i == 3) {
            Bombs redBomb = new RedBomb();
            return redBomb;
        } else if (i >= 4) {
            Bombs regBomb = new RegularBomb();
            return regBomb;
        } else {
            return null;
        }
    }

    @Override
    public void updateObjectPlace(GameObject go) {
        go.setPosX(go.getDeltaX());
        go.setPosY(go.getDeltaY());
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
