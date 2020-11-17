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
    double getEnginePower();
    void setEnginePower(double enginePower);
    void startEngine();
    void stopEngine();
    double getCurrentSpeed();
    void setCurrentSpeed(double newSpeed);

}