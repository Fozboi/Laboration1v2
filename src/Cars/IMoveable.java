package Cars;

import java.awt.*;
/**
 * Samlar rörelse-metoder
 */
public interface IMoveable {
    void move();
    void turnLeft();
    void turnRight();
    Point getPosition();
    void setPosition(Point newPos);
    double getCurrentSpeed();
    void setCurrentSpeed(double newSpeed);
    int getDir();
    void setDir(int newDir);

}
