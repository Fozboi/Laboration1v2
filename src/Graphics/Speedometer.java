package Graphics;

import Cars.*;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.ConcurrentModificationException;

public class Speedometer extends JLabel{
    private CarModel carModel;

    public Speedometer(CarModel carModel){
        this.carModel = carModel;
        setText();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setText();
    }

    private static final DecimalFormat df = new DecimalFormat("0.0");

    //returns a text label with the car model followed by the current speed
    private void setText(){
        try{
            String labelText = "";

            for(Car car : carModel.getCars()){
                labelText = labelText + car.getModelName() + " : " + df.format(car.getCurrentSpeed()) + "    |    ";
            }

            this.setText(labelText);
        }catch(ConcurrentModificationException e){System.out.println("Stopped that weird bug again");} //IBLAND vid start kastas denna

    }

}
