package Graphics;

import Cars.*;

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
            addCar(car);
        }
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        carImageMap.forEach((k,v) -> g.drawImage(v, (int) k.getPosition().getX(), (int) k.getPosition().getY(), null));
    }

    private BufferedImage getImage(Car car){
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("pics/" + car.getModelName() + ".jpg"));
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return image;
    }

    public void addCar(Car car){ carImageMap.put(car,getImage(car));}
    public void removeCar(Car car) { carImageMap.remove(car);}
}
