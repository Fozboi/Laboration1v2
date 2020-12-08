package Graphics;

import Cars.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarModel {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    public final int delay = 50;
    // The timer is started with an listener (see below) that executes the statements
    // each step between delays.
    public Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Car> cars = new ArrayList<>();

    public CarModel(ArrayList<Car> carsList){
        cars = carsList;
        timer.start();

    }

    public CarModel(){

    }

    public static void main(String[] args) {
        // Instance of this class
        CarModel cm = new CarModel();

        Car volvo = new Volvo240();
        Car saab = new Saab95();
        saab.setPosition(new Point(100,0));
        Car scania = new Scania();
        scania.setPosition(new Point(200,0));

        cm.cars.add(volvo);
        cm.cars.add(saab);
        cm.cars.add(scania);


        // Start a new view and send a reference of self
        cm.frame = new CarView("CarSim 1.0", cm);

        // Start the timer
        cm.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    public class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int mapWidth = frame.drawPanel.getWidth();
            int mapHeight = frame.drawPanel.getHeight();


            for (Car car : cars) {
                int x = (int) car.getPosition().getX();
                int y = (int) car.getPosition().getY();
                int carDir = car.getDir();

                if(        (x <= 0 && carDir == Car.WEST)
                        || (x >= mapWidth  - frame.drawPanel.carImageMap.get(car).getWidth() && carDir == Car.EAST)
                        || (y <= 0 && carDir == Car.NORTH)
                        || (y >= mapHeight - frame.drawPanel.carImageMap.get(car).getHeight() && carDir == Car.SOUTH)  ){

                    car.stopEngine();
                    car.turnLeft();
                    car.turnLeft();
                    car.startEngine();
                }

                car.move();

                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();

            }
        }
    }

    // Calls the gas method for each car once
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
