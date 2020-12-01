package Cars;

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
    void startEngine();
    void stopEngine();

    Point getPosition();
    void setPosition(Point newPos);
    double getEnginePower();
    void setEnginePower(double enginePower);
    double getCurrentSpeed();
    void setCurrentSpeed(double newSpeed);
    int getDir();
    void setDir(int newDir);

}