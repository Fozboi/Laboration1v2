import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    public Point position = new Point(200,200);
    Saab95 testCar;
    double currentSpeed = 0.1;
    int dir = 0; //north

    @BeforeEach
    public void init(){
        testCar = new Saab95();
    }

    @Test
    public void moveMovesCar(){
        int yIntPos = (int) position.getY();
        int yPosBefore = yIntPos;
        testCar.move();
        int yPosAfter = yIntPos;

        assertNotSame(yPosBefore, yPosAfter);


    }
}