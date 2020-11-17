import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TransporterTest {

    Saab95 testCar;
    Transporter testTruck;

    @BeforeEach
    public void init(){
        testCar = new Saab95();
        testTruck = new Transporter(1);
    }

    @Test
    public void canLoadCarWorks(){
        Transporter testTruck2 = new Transporter(1);
        Car testCar2 = new Volvo240();

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
        testTruck.loadCar(testCar);
        assertFalse(testTruck.canLoadCar(testCar2)); //transporter can't load car when 2 others are already on it

    }

    @Test
    public void loadCarWorks(){
        testTruck.setRampDown();
        Point pos1 = new Point(100,100);
        Point pos2 = new Point(109,109);
        testTruck.setPosition(pos1);
        testCar.setPosition(pos2);
        testTruck.loadCar(testCar);
        ArrayList<Car> carlist = testTruck.getLoadedCars();

        assertTrue(carlist.size() == 1); //car was loaded and therefore added to the list of loaded cars
    }

    @Test
    public void unloadLastCarWorks(){
        Car testCar2 = new Volvo240();

        testTruck.setRampDown();
        Point pos1 = new Point(100,100);
        Point pos2 = new Point(109,109);
        testTruck.setPosition(pos1);
        testCar.setPosition(pos2);
        testCar2.setPosition(pos2);

        testTruck.loadCar(testCar2);
        System.out.println(testTruck.getLoadedCars().get(0).getModelName());
        System.out.println(testTruck.getLoadedCars().size());

        testTruck.loadCar(testCar);
        System.out.println(testTruck.getLoadedCars().get(0).getModelName());
        System.out.println(testTruck.getLoadedCars().size());


        testTruck.unloadLastCar();
        System.out.println(testTruck.getLoadedCars().size());

        assertTrue(testTruck.getLoadedCars().size() == 1);
    }
}