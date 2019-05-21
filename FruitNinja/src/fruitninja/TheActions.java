package fruitninja;

import java.util.Random;

public class TheActions implements GameActions {

    float deltaX, deltaY;

    @Override
    public GameObject createGameObject(int x) {
        Random r = new Random();
        int i;
        if (x == 4) {
            i = (int) (1 + r.nextDouble() * 16);
        } else {
            i = (int) (1 + r.nextDouble() * 22);
        }
        if (i <= 4) {
            Fruit melon = new Melon();
            return melon;
        } else if (i <= 8) {
            Fruit banana = new Banana();
            return banana;
        } else if (i <= 12) {
            Fruit apple = new Apple();
            return apple;
        } else if (i <= 14) {
            SuperFruit1 superFruit1 = new SuperFruit1();
            return superFruit1;
        } else if (i <= 15) {
            SuperFruit2 superFruit2 = new SuperFruit2();
            return superFruit2;
        } else if (i <= 16) {
            PowerFruit powerFruit = new PowerFruit();
            return powerFruit;
        } else if (i <= 18) {
            Bombs redBomb = new RedBomb();
            return redBomb;
        } else if (i <= 21) {
            Bombs regBomb = new RegularBomb();
            return regBomb;
        } else if (i <= 22) {
            LifeFruit lifeFruit = new LifeFruit();
            return lifeFruit;
        } else {
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
    public void updateHalf(Fruit go) {
        go.setPosY(go.getDeltaY());
        go.setHalfX1(-go.getDeltaX());
        go.setHalfX2(go.getDeltaX());
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

    @Override
    public void saveGame() {
    }

    @Override
    public void loadGame() {
    }

    @Override
    public void ResetGame() {
    }

}
