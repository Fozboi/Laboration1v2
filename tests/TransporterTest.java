import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class TransporterTest {

    Saab95 testCar;
    Transporter testTruck;

    @BeforeEach
    public void init(){
        testCar = new Saab95();
        testTruck = new Transporter();
    }

    @Test
    public void canLoadCarWorks(){
        Transporter testTruck2 = new Transporter();
        Car testCar2 = new Saab95();
        Car testCar3 = new Volvo240();

        testTruck.setRampDown();

        Point pos1 = new Point(100,100);
        Point pos2 = new Point(109,109);
        testTruck.setPosition(pos1);
        testCar.setPosition(pos2);
        assertTrue(testTruck.canLoadCar(testCar)); //transporter can load car that complies to the requirements

        testTruck2.setPosition(pos2);
        assertFalse(testTruck.canLoadCar(testTruck2)); //transporter can't load another transporter

        testTruck.setRampUp();
        assertFalse(testTruck.canLoadCar(testCar)); //transporter can't load car when ramp is up
        testTruck.setRampDown();

        testCar2.setPosition(pos2);
        testCar3.setPosition(pos2);
        testTruck.loadCar(testCar);
        testTruck.loadCar(testCar2);
        assertFalse(testTruck.canLoadCar(testCar3)); //transporter can't load car when 2 others are already on it

    }
}