import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

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
        assertTrue(testCar.dir==1);

    }

    @Test
    public void turnLeftWorks(){
        testCar.turnLeft();
        assertTrue(testCar.dir==3);

    }

    @Test
    public void breakDecreasesSpeed(){
        testCar.currentSpeed = 20;
        double preSpeed = testCar.currentSpeed;
        testCar.brake(0.5);
        double postSpeed = testCar.currentSpeed;

        assertTrue(postSpeed < preSpeed);
    }

    @Test
    public void gasIncreasesSpeed(){
        testCar.currentSpeed = 20;
        double preSpeed = testCar.currentSpeed;
        testCar.gas(0.5);
        double postSpeed = testCar.currentSpeed;

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