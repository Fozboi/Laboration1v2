import java.awt.*;

/**
 * Samlar rörelse-metoder
 */
interface Moveable{
    void move();
    void turnLeft();
    void turnRight();
    void gas(double amount);
    void brake(double amount);
    Point getPosition();
    void setPosition(Point newPos);


}