package Graphics;

import Cars.*;

import java.util.ArrayList;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarModel {

    // A list of cars, modify if needed
    ArrayList<Car> cars = new ArrayList<>();

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

    void setTurboOn() {
        for(Car car : cars){
            if(car instanceof Saab95){
                ((Saab95) car).setTurboOn();
            }
        }
    }

    void setTurboOff() {
        for(Car car : cars){
            if(car instanceof Saab95){
                ((Saab95) car).setTurboOff();
            }
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



}
