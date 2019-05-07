
package fruitninja;

import java.util.Random;

public abstract class Fruit implements GameObject{
    int posX,posY;
    float rand;
    float deltaX, deltaY , downY;
    public Fruit() {
        Random r = new Random();
        posX=(int) (100+r.nextDouble()*800);
        posY=561;
        rand=(float) (100+r.nextDouble()*800);
        Random rx= new Random();
        deltaX = 2+rx.nextFloat()*3;
        Random ry= new Random();
        deltaY = -(8+ry.nextFloat()*8);
        downY=-deltaY;
    }

    @Override
    public float getRand() {
        return rand;
    }
    
    @Override
    public abstract Enum getObjectType();
    enum fruit{ 
        MELON, BANANA, APPLE; 
    } 

    @Override
    public void setPosX(float x) {
        this.posX +=x;
    }

    @Override
    public void setPosY(float y) {
        this.posY +=y;
    }

    @Override
    public int getPosX() {
        return posX;
    }

    @Override
    public int getPosY() {
        return posY;
    }

    @Override
    public int getDownY() {
        return (int) downY;
    }
    
    @Override
    public int getDeltaX() {
        if(rand>=400)
            return -(int) deltaX;
        return (int) deltaX;
    }

    @Override
    public int getDeltaY() {
        return (int) deltaY;
    }

    @Override
    public void setDeltaY(int y){
        deltaY=y;
    }
}
