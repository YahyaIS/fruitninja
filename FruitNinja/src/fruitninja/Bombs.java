
package fruitninja;

import java.util.Random;

public abstract class Bombs implements GameObject{
int posX,posY;

    float deltaX, deltaY , downY;
    float rand;
    public Bombs() {
        Random r = new Random();
        posX=(int) (100+r.nextDouble()*800);
        posY=561;
        rand=(float) (100+r.nextDouble()*800);
    }

    @Override
    public float getRand() {
        return rand;
    }
    
    @Override
    public abstract Enum getObjectType();
    enum bomb{ 
        DEADLY, NORM; 
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
    public int getDownY() {
        return (int) downY;
    }

    @Override
    public void setDeltaY(int y){
        deltaY=y;
    }
    
}
