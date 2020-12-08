package Graphics;

import Cars.*;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

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

    private static DecimalFormat df = new DecimalFormat("0.0");

    private void setText(){
        String labelText = "<html>";

        for(Car car : carModel.cars){
            labelText = labelText + car.getModelName() + " : " + df.format(car.getCurrentSpeed()) + "km/h <br>";
        }
        labelText = labelText + "</html>";

        this.setText(labelText);
    }

}
