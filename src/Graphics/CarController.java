package Graphics;

import Cars.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CarController {
    CarModel carModel;
    CarView carView;

    public final int delay = 50;
    public Timer timer = new Timer(delay, new TimerListener());

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

        cc.timer.start();

    }

    public class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Car car : carModel.cars) {
                if (hitWall(car)) {
                    carModel.breakTurn(car);
                }
                car.move();
            }
            // repaint() calls the paintComponent method of the panel
            carView.drawPanel.repaint();

        }
    }
    
    public boolean hitWall(Car car){
        int mapWidth = carView.drawPanel.getWidth();
        int mapHeight = carView.drawPanel.getHeight();

        int x = (int) car.getPosition().getX();
        int y = (int) car.getPosition().getY();
        int carDir = car.getDir();

        if(        (x <= 0 && carDir == Car.WEST)
                || (x >= mapWidth  - carView.drawPanel.carImageMap.get(car).getWidth() && carDir == Car.EAST)
                || (y <= 0 && carDir == Car.NORTH)
                || (y >= mapHeight - carView.drawPanel.carImageMap.get(car).getHeight() && carDir == Car.SOUTH)  ){
            return true;
        }
        return false;
    }



}
