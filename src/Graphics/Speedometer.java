package Graphics;

import Cars.*;

import javax.swing.*;
import java.awt.*;

public class Speedometer extends JLabel{
    CarModel carModel;

    public Speedometer(CarModel carModel){
        this.carModel = carModel;
        setText();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setText();
    }

    private void setText(){
        System.out.println("hej");
        String labelText = "";
        for(Car car : carModel.cars){
            labelText = labelText + car.getModelName() + " : " + car.getCurrentSpeed() + "km/h\n";
        }
        this.setVisible(true);
    }

}
