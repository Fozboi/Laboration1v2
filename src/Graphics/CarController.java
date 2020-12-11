package Graphics;

import Cars.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarController {
    CarModel carModel;
    CarView carView;

    public final int delay = 50;
    public Timer timer = new Timer(delay, new TimerListener());

    public static void main(String[] args) {
        CarController cc = new CarController();

        cc.carModel = new CarModel();

        cc.carModel.addCar(new Volvo240());
        cc.carModel.addCar(new Saab95());
        cc.carModel.addCar(new Scania());

        cc.carView = new CarView("CarSim 2.0", cc.carModel);


        cc.timer.start();
        cc.initButtonFunctionality();
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
            carView.repaint();
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

    private void initButtonFunctionality(){
        carView.gasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carModel.gas(carView.getGasAmount());
            }
        });

        carView.brakeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carModel.brake(carView.getGasAmount());
            }
        });

        carView.turboOnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carModel.setTurboOn();
            }
        });

        carView.turboOffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carModel.setTurboOff();
            }
        });

        carView.liftBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carModel.liftBed();
            }
        });

        carView.lowerBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carModel.lowerBed();
            }
        });

        carView.startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carModel.startEngine();
            }
        });

        carView.stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carModel.stopEngine();
            }
        });

        carView.addCarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(carModel.cars.size() < 10){
                    carModel.addCar(carModel.stringToCar(carView.carModelField.getText()));
                    carView.drawPanel.addCar(carModel.cars.get(carModel.cars.size()-1));
                    fitXCarPanel();
                }else{
                    throw new IllegalStateException("No more space");
                }
            }
        });

        //Metoden går igenom cars bakifrån och tar bort den första bilen den hittar
        carView.removeCarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(carModel.cars.size() == 0){
                    throw new IllegalStateException("No cars exist");
                }else{
                    for(int i = carModel.cars.size()-1; i >= 0; i--){
                        if(carModel.cars.get(i).getModelName().equals(carView.carModelField.getText())){

                            carView.drawPanel.removeCar(carModel.cars.get(i));
                            carModel.removeCar(carModel.cars.get(i));

                            break;
                        }
                        if(i == 0){
                            throw new IllegalStateException("No such car exists");
                        }
                    }
                fitXCarPanel();
                }
            }
        });
    }

    private void fitXCarPanel(){
        int x = 0;
        for(Car car : carModel.cars){
            if(car.getPosition().getX() > x){
                x = (int) car.getPosition().getX();
            }
        }
        x += carModel.carDistance;

        if(x > carView.getX()){
            carView.drawPanel.setPreferredSize(new Dimension(x,carView.drawPanel.getHeight()));
            carView.setPreferredSize(new Dimension(x,carView.getHeight()));

            carView.pack();
        }else if(x <= carView.getX()){
            carView.drawPanel.setPreferredSize(new Dimension(carView.getX(),carView.drawPanel.getHeight()));
            carView.setPreferredSize(new Dimension(carView.getX(),carView.getHeight()));

            carView.pack();
        }
    }
}
