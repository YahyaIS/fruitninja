
package fruitninja;

public interface Level {
    public void manageLevel();
    public void setHighScore(int score);
    public void setXY(double x , double y);
    public int getScore();
    public int getTime();
}
