package Graphics;

import Cars.*;
import Loaders.Transporter;

import javax.swing.*;
import java.awt.*;
import java.lang.*;

/**
 * En GUI-värld där bilen åker runt
 */
public class CarGame extends JFrame{
    ImageIcon carIcon;
    ImageIcon truckIcon;

    public static void main(String[] args){
        Car car = new Volvo240(); //skapa ny bil
        Transporter truck = new Transporter(2);

        car.startEngine(); //starta bilen
        truck.startEngine();

        CarGame map = new CarGame(car); //skapa ny karta

        car.setPosition(new Point(350,350));
        truck.setPosition(new Point(370,350));

        for (int i = 0; i <= 900; i++){
            if(i%90 == 1)
                car.turnLeft();
                car.brake(0.15);
            if(i%120 == 1)
                truck.turnLeft();
                truck.brake(0.15);

            car.gas(0.1);
            truck.gas(0.1);


            if(truck.inRange(car) && !truck.getLoadedCars().contains(car) && i%175 == 1){
                car.setCurrentSpeed(0);
                truck.setCurrentSpeed(0);
                truck.setRampDown();
                truck.loadObject(car);
                truck.setRampUp();
                System.out.println("Pålastad");
            }

            if(truck.getLoadedCars().size() != 0 && i%200 == 101){
                car.setCurrentSpeed(0);
                truck.setCurrentSpeed(0);
                truck.setRampDown();
                truck.unloadLastCar();
                truck.setRampUp();
                System.out.println("Avlastad");

            }




            car.move();
            truck.move();

            JLabel newCarLabel = map.createLabel(car); //skapar ny bil-etikett
            JLabel newTruckLabel = map.createLabel(truck);
            map.getContentPane().removeAll(); //rensar fönstret (borde nog ta bort bara bilen specifikt)
            map.add(newCarLabel); //lägger till nya bilen
            map.add(newTruckLabel);
            map.repaint(); //uppdaterar fönstret
            wait(5); //väntar 17 millisekunder = 60FPS
        }
        System.out.println("run over");
    }

    public CarGame(Car car){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,500);
        getContentPane().setBackground(Color.green);
        setLocation(600,400);
        setLayout(null);
        setVisible(true);
    }//konstruktor skapar ny karta med bil

    public JLabel createLabel(Car inputCar){
        int xcoord = (int) Math.round(inputCar.getPosition().getX());
        int ycoord = (int) Math.round(inputCar.getPosition().getY());

        if(inputCar.getDir() == Car.NORTH){
            carIcon = new ImageIcon("carIconNORTH.png");
        } else if(inputCar.getDir()== Car.EAST){
            carIcon = new ImageIcon("carIconEAST.png");
        } else if(inputCar.getDir() == Car.SOUTH){
            carIcon = new ImageIcon("carIconSOUTH.png");
        } else if(inputCar.getDir() == Car.WEST){
            carIcon = new ImageIcon("carIconWEST.png");
        }

        JLabel carLabel = new JLabel("",carIcon,JLabel.CENTER);
        Dimension size = carLabel.getPreferredSize();
        carLabel.setBounds(xcoord-size.width/2,ycoord-size.height/2,size.width,size.height);

        return carLabel;
    }//returnerar en etikett med bilen som gavs

    public JLabel createLabel(Transporter inputTruck){
        int xcoord = (int) Math.round(inputTruck.getPosition().getX());
        int ycoord = (int) Math.round(inputTruck.getPosition().getY());

        if(inputTruck.getDir() == Car.NORTH){
            truckIcon = new ImageIcon("truckIconNORTH.png");
        } else if(inputTruck.getDir()== Car.EAST){
            truckIcon = new ImageIcon("truckIconEAST.png");
        } else if(inputTruck.getDir() == Car.SOUTH){
            truckIcon = new ImageIcon("truckIconSOUTH.png");
        } else if(inputTruck.getDir() == Car.WEST){
            truckIcon = new ImageIcon("truckIconWEST.png");
        }

        JLabel truckLabel = new JLabel("",truckIcon,JLabel.CENTER);
        Dimension size = truckLabel.getPreferredSize();
        truckLabel.setBounds(xcoord-size.width/2,ycoord-size.height/2,size.width,size.height);

        return truckLabel;
    }


    public static void wait(int ms)
    {
        try
        {
            Thread.sleep(ms);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }



}
