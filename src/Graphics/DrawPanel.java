package Graphics;

import Cars.Car;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

// This panel represent the animated part of the view with the car images.

public class DrawPanel extends JPanel{
    HashMap<Car,BufferedImage> carImageMap = new HashMap<>();

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y, ArrayList<Car> cars) {

        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);

        for(Car car : cars){
            try {
                // You can remove the "pics" part if running outside of IntelliJ and
                // everything is in the same main folder.
                // volvoImage = ImageIO.read(new File("Cars.Volvo240.jpg"));

                // Rememember to rightclick src New -> Package -> name: pics -> MOVE *.jpg to pics.
                // if you are starting in IntelliJ.

                carImageMap.put(car,ImageIO.read(new File("pics/" + car.getClass().getName() + ".jpg")));
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
        System.out.println(carImageMap.size());



    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        carImageMap.forEach((k,v) -> g.drawImage(v,(int) k.getPosition().getX(),(int) k.getPosition().getY(),null));
    }
}
