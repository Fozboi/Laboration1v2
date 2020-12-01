import Cars.Saab95;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    public Point position = new Point(200,200);
    Saab95 testCar;
    double currentSpeed = 0.1;
    public int dir = 0; //north

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

    @Test
    public void turnRightWorks(){
        testCar.turnRight();
        assertTrue(testCar.getDir()==1);

    }

    @Test
    public void turnLeftWorks(){
        testCar.turnLeft();
        assertTrue(testCar.getDir()==3);

    }

    @Test
    public void breakDecreasesSpeed(){
        testCar.setCurrentSpeed(20);
        double preSpeed = testCar.getCurrentSpeed();
        testCar.brake(0.5);
        double postSpeed = testCar.getCurrentSpeed();

        assertTrue(postSpeed < preSpeed);
    }

    @Test
    public void gasIncreasesSpeed(){
        testCar.setCurrentSpeed(20);
        double preSpeed = testCar.getCurrentSpeed();
        testCar.gas(0.5);
        double postSpeed = testCar.getCurrentSpeed();

        assertTrue(postSpeed > preSpeed);
    }

    @Test
    public void brakeOnlyAcceptsValidValues(){
        try{
            testCar.brake(1.5);
            fail("should have thrown exception but it didn't");
        } catch (IllegalArgumentException expectedException){ }
    }

    @Test
    public void gasOnlyAcceptsValidValues(){
        try{
            testCar.gas(-1.5);
            fail("should have thrown exception but it didn't");
        } catch (IllegalArgumentException expectedException){ }
    }
}