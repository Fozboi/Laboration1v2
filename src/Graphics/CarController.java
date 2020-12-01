package Graphics;

import Cars.Car;
import Cars.Volvo240;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with an listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Car> cars = new ArrayList<>();

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        cc.cars.add(new Volvo240());

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int mapWidth = frame.drawPanel.getWidth();
            int mapHeight = frame.drawPanel.getHeight();


            for (Car car : cars) {
                System.out.println(car.getPosition().getY());
                System.out.println(Math.ceil(car.getPosition().getY()));
                int x = (int) Math.floor(car.getPosition().getX());
                int y = (int) Math.ceil(car.getPosition().getY());
                int carDir = car.getDir();

                if(        (x <= 0 && carDir == Car.WEST)
                        || (x >= mapWidth  - frame.drawPanel.volvoImage.getWidth() && carDir == Car.EAST)
                        || (y <= 0 && carDir == Car.NORTH)
                        || (y >= mapHeight - frame.drawPanel.volvoImage.getHeight() && carDir == Car.SOUTH)  ){

                    System.out.println("hit wall");
                    System.out.println(car.getCurrentSpeed());
                    car.stopEngine();
                    car.turnLeft();
                    car.turnLeft();
                    car.startEngine();
                }
                System.out.println(car.getCurrentSpeed());
                System.out.println();
                car.move();
                x = (int) Math.round(car.getPosition().getX());
                y = (int) Math.round(car.getPosition().getY());


                frame.drawPanel.moveit(x, y);
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


}
