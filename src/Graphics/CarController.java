package Graphics;

import Cars.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class CarController implements Observer {
    private CarModel carModel;
    private CarView carView;


    public static void main(String[] args) {
        CarController cc = new CarController();

        cc.carModel = new CarModel();
        cc.carModel.addObserver(cc);

        cc.carView = new CarView("CarSim 2.0", cc.carModel);

        cc.addCar("Volvo240");
        cc.addCar("Saab95");
        cc.addCar("Scania");

        cc.initButtonFunctionality();
    }

    @Override
    public void update(Observable carModel, Object arg) {
        carView.repaint();
    }


    private void addCar(String carName){
        Integer carHeight = carView.drawPanel.findImageFromFile(carModel.stringToCar(carName)).getHeight();
        Integer carWidth = carView.drawPanel.findImageFromFile(carModel.stringToCar(carName)).getWidth();
        carModel.createCar(carName,new Dimension(carWidth,carHeight));
    }



    //initialiserar alla knappar i UI
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

        //metoden lägger till en bil ifall det finns färre än 10
        carView.addCarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(carModel.getCars().size() < carModel.maxNrCars){
                    addCar(carView.carModelField.getText());
                    fitXCarPanel();
                }else{
                    throw new IllegalStateException("Only " + carModel.maxNrCars + " cars allowed");
                }
            }
        });

        //Metoden går igenom cars bakifrån och tar bort den första bilen den hittar
        carView.removeCarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(carModel.getCars().size() == 0){
                    throw new IllegalStateException("No cars exist");
                }else{
                    for(int i = carModel.getCars().size()-1; i >= 0; i--){
                        if(carModel.getCars().get(i).getModelName().equals(carView.carModelField.getText())){
                            carModel.removeCar(carModel.getCars().get(i));

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

    //går igenom cars och omplacerar bilarna så de har rätt distans till varandra
    private void fitXCarPanel(){
        int x = 0;
        for(Car car : carModel.getCars()){
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
