package Graphics;

import Cars.*;

import java.awt.*;
import java.util.ArrayList;

public class CarController {
    CarModel carModel;
    CarView carView;

    public static void main(String[] args) {
        CarController cc = new CarController();

        ArrayList<Car> cars = new ArrayList<>(10);

        Car volvo = new Volvo240();
        Car saab = new Saab95();
        saab.setPosition(new Point(100,0));
        Car scania = new Scania();
        scania.setPosition(new Point(200,0));

        cars.add(volvo);
        cars.add(saab);
        cars.add(scania);

        cc.carModel = new CarModel(cars);
        cc.carView = new CarView("CarSim 2.0", cc.carModel);







    }






}
