
package fruitninja;

public interface GameActions {
    public GameObject createGameObject();
    public void updateObjectPlace(GameObject go);
    public void sliceObjects();
    public void setDeltaX(float x);
    public void setDeltaY(float y);
}
