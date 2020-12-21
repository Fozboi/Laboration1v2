package Graphics;

import Cars.*;
import Loaders.IHasTrailer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Observable;


/**
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarModel extends Observable {

    public final int carDistance = 100;
    public final int maxNrCars = 10;
    private final int delay = 50;
    private Timer timer = new Timer(delay, new TimerListener());
    private ArrayList<Car> cars = new ArrayList<>(10);
    private HashMap<Car,Dimension> carSizes = new HashMap<>();
    public Dimension worldSize = new Dimension(carDistance*maxNrCars,560);
    private boolean fullyInitialized = false;

    public CarModel(){
        timer.start();
    }

    public class TimerListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if(fullyInitialized){
                orderCars();

                for (Car car : cars) {
                    if (hitWall(car)) {
                        breakTurn(car);
                    }
                    car.move();
                }
                setChanged();
                notifyObservers();
            }
        }
    }

    private boolean hitWall(Car car){
        int x = (int) car.getPosition().getX();
        int y = (int) car.getPosition().getY();
        int carDir = car.getDir();

        if(        (x <= 0 && carDir == Car.WEST)
                || (x >= worldSize.width  - carSizes.get(car).width && carDir == Car.EAST)
                || (y <= 0 && carDir == Car.NORTH)
                || (y >= worldSize.height - carSizes.get(car).height && carDir == Car.SOUTH)  ){
            return true;
        }
        return false;
    }

    //vänder bilen 180 grader och återställer dess hastighet
    public void breakTurn(Car car){
        car.stopEngine();
        car.turnLeft();
        car.turnLeft();
        car.startEngine();
    }

    public void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Car car : cars
                ) {
            car.gas(gas);
        }
    }

    public void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Car car : cars
        ) {
            car.brake(brake);
        }
    }

    public <T extends IHasTurbo> void setTurboOn() {
        for(Car car : cars){
            try{
                ((T) car).setTurboOn();
            }catch(ClassCastException e){}
        }
    }

    public <T extends IHasTurbo> void setTurboOff() {
        for(Car car : cars){
            try{
                ((T) car).setTurboOff();
            }catch(ClassCastException e){}
        }
    }

    public <T extends IHasTrailer>void liftBed(){
        for(Car car : cars){
            try{
                ((T) car).setTrailerUp();
            }catch(ClassCastException e){}
        }
    }

    public <T extends IHasTrailer>void lowerBed(){
        for(Car car : cars){
            try{
                ((T) car).setTrailerDown();
            }catch(ClassCastException e){}
        }
    }

    public void startEngine() {
        for (Car car : cars
        ) {
            car.startEngine();
        }
    }

    public void stopEngine() {
        for (Car car : cars
        ) {
            car.stopEngine();
        }
    }

    public ArrayList<Car> getCars(){return cars;}


    public void addCar(Car inputCar, Dimension carSize) {
        inputCar.setPosition(new Point(cars.size()*carDistance,0));
        carSizes.put(inputCar,carSize);
        cars.add(inputCar);
        fullyInitialized = true;
    }

    //Metod vars funktion ej mer behövs
    private Point findOpening(Car inputCar){
        Boolean found = false;

        if(cars.size() == 0)
            return new Point(0,0);
        else {
            for (int i = 0; i <= maxNrCars-1; i++) {

                for (Car car : cars) {
                    if (car.getPosition().getX() == i * carDistance) {
                        break;
                    } else if (car == cars.get(cars.size() - 1)) {
                        found = true;
                    }
                }

                if (found) {
                    return new Point(i * carDistance, 0);
                }
            }
        }

        throw new IllegalStateException("No openings for the car");
    }

    public void removeCar(Car car) {
        cars.remove(car);
        fitCars();
    }

    private void fitCars(){
        orderCars();
        adjustCars();
    }

    private void orderCars(){
        ArrayList<Car> orderedCars = new ArrayList<>(Collections.nCopies(maxNrCars,null));

        for(Car car : cars){
            int i = (int) car.getPosition().getX()/carDistance;
            orderedCars.add(i,car);
        }

        for(int i = orderedCars.size()-1; i >= 0; i--){
            if(orderedCars.get(i) == null){
                orderedCars.remove(i);
            }
        }

        cars = orderedCars;
    }

    private void adjustCars(){
        for(int i = 0; i < cars.size(); i++){
            Point oldPos = cars.get(i).getPosition();
            Point newPos = new Point((int) i*carDistance,(int) oldPos.getY());
            cars.get(i).setPosition(newPos);
        }
    }



    //kollar om det finns en sorts bil som matchar input-strängen
    //och returnerar isåfall ett objekt av den bilsorten
    public Car stringToCar(String carName){
        Car newCar = null;

        try{
            Class carClass = Class.forName("Cars." + carName);
            newCar = (Car) carClass.getConstructor().newInstance();

        }catch(Exception e){
            throw new IllegalArgumentException("Unrecognised car model");
        }
        return newCar;
    }
}
