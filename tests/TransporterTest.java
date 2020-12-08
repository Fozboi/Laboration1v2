import Cars.Car;
import Cars.Saab95;
import Loaders.Transporter;
import Cars.Volvo240;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TransporterTest {

    Saab95 testCar;
    Transporter testTruck;
    Point pos1 = new Point(100,100);
    Point pos2 = new Point(109,109);

    @BeforeEach
    public void init(){
        testCar = new Saab95();
        testTruck = new Transporter(2);
        testTruck.setRampDown();
        testTruck.setPosition(pos1);
        testCar.setPosition(pos2);
    }

    @Test
    public void canLoadCarWorks(){
        Transporter testTruck2 = new Transporter(1);
        Car testCar2 = new Volvo240();

        assertTrue(testTruck.canLoadObject(testCar)); //transporter can load car that complies to the requirements

        testCar.setPosition(new Point(50,50));
        assertFalse(testTruck.canLoadObject(testCar)); //transporter cannot load car out of range
        testCar.setPosition(pos1);

        testTruck2.setPosition(pos2);
        assertFalse(testTruck.canLoadObject(testTruck2.getTruck())); //transporter can't load Cars.Scania

        testTruck.setRampUp();
        assertFalse(testTruck.canLoadObject(testCar)); //transporter can't load car when ramp is up
        testTruck.setRampDown();

        testCar2.setPosition(pos2);
        testTruck.loadObject(testCar);
        testTruck.loadObject(testCar2);
        assertFalse(testTruck.canLoadObject(testCar2)); //transporter can't load car when 2 others are already on it



    }

    @Test
    public void loadCarWorks(){
        testTruck.loadObject(testCar);
        ArrayList<Car> carlist = testTruck.getLoadedCars();

        assertTrue(carlist.size() == 1); //car was loaded and therefore added to the list of loaded cars
    }

    @Test
    public void unloadLastCarWorks(){
        Car testCar2 = new Volvo240();

        testCar2.setPosition(pos2);

        testTruck.loadObject(testCar2);
        testTruck.loadObject(testCar);

        testTruck.unloadLastCar();

        assertTrue(testTruck.getLoadedCars().size() == 1);
    }

    @Test
    public void carPositionIsCorrectWhenUnloaded(){
        testTruck.loadObject(testCar);
        testTruck.unloadLastCar();
        double truckYPos = testTruck.getPosition().getY();
        double carYPos = testCar.getPosition().getY();

        assertTrue(truckYPos == carYPos-testTruck.getPickupRange()); // pickupRange is 10 and dir is NORTH by default

    }

    @Test
    public void moveMovesBothWhenACarIsLoaded(){
        testTruck.loadObject(testCar);
        testTruck.setRampUp();
        testTruck.setCurrentSpeed(1);
        double truckYPosBefore = testTruck.getPosition().getY();
        testTruck.move(); //should move both car and truck by -1 on the y-axis
        double truckYPosAfter = testTruck.getPosition().getY();
        double carYPosAfter = testTruck.getPosition().getY();

        assertTrue(carYPosAfter == truckYPosAfter);
        assertTrue(truckYPosAfter == truckYPosBefore-1);
    }
}