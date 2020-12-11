package Graphics;

import Cars.*;

import java.awt.*;
import java.util.ArrayList;

/**
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarModel {

    public final int carDistance = 100;
    // A list of cars, modify if needed
    ArrayList<Car> cars;

    public CarModel(ArrayList<Car> carsList){
        cars = carsList;
    }

    public void breakTurn(Car car){
        car.stopEngine();
        car.turnLeft();
        car.turnLeft();
        car.startEngine();
    }

    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Car car : cars
                ) {
            car.gas(gas);
        }
    }

    void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Car car : cars
        ) {
            car.brake(brake);
        }
    }

    <T extends IHasTurbo> void setTurboOn() {
        for(Car car : cars){
            try{
                ((T) car).setTurboOn();
            }catch(Exception e){}
        }
    }

    <T extends IHasTurbo> void setTurboOff() {
        for(Car car : cars){
            try{
                ((T) car).setTurboOff();
            }catch(Exception e){}
        }
    }

    void liftBed() {
        for(Car car : cars){
            if(car instanceof Scania){
                ((Scania) car).setTrailerAngle(70);
            }
        }
    }

    void lowerBed() {
        for(Car car : cars){
            if(car instanceof Scania){
                ((Scania) car).setTrailerAngle(0);
            }
        }
    }

    void startEngine() {
        for (Car car : cars
        ) {
            car.startEngine();
        }
    }

    void stopEngine() {
        for (Car car : cars
        ) {
            car.stopEngine();
        }
    }

    public ArrayList<Car> getCars(){return cars;}

    public void addCar(Car inputCar) {
        Boolean found = false;

        for(int i = 0; i <= 9; i++){

            for(Car car : cars){
                if(car.getPosition().getX() == i*carDistance){
                    break;
                }else if(car == cars.get(cars.size()-1)){
                    found = true;
                }
            }

            if(found){
                inputCar.setPosition(new Point(i*carDistance,0));
                break;
            }
        }

        if(!found){
            System.out.println("No free space!");
        }else{
            cars.add(inputCar);
        }
    }

    public void removeCar(Car car) {
        cars.remove(car);
    }

    public Car stringToCar(String carName){
        Car newCar = null;

        try{
            Class carClass = Class.forName("Cars." + carName);
            newCar = (Car) carClass.getConstructor().newInstance();

        }catch(Exception e){
            System.out.println(e.toString());
        }
        return newCar;
    }
}
