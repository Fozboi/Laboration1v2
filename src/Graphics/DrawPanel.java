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
    CarModel carModel;

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y, CarModel carModel) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        this.carModel = carModel;

        updateCarImageMap();
    }

    public void updateCarImageMap(){

        for(Car car : carModel.getCars()){
            if (!carImageMap.containsKey(car)) {
                System.out.println("Added " + car.getModelName());
                carImageMap.put(car,findImageFromFile(car));
            }
        }

        ArrayList<Car> abundantCars = new ArrayList<>();

        carImageMap.forEach((car,image) -> {
            if(!carModel.getCars().contains(car)){abundantCars.add(car);}});

        for(Car car : abundantCars){carImageMap.remove(car);}


    }

    // This method is called each time the panel updates/refreshes/repaints itself
    @Override
    protected void paintComponent(Graphics g) {
        updateCarImageMap();
        super.paintComponent(g);
        for(Car car : carModel.getCars()){
            g.drawImage(carImageMap.get(car),(int)car.getPosition().getX(),(int)car.getPosition().getY(),null);
        }
    }


    //reads the image belonging to the input car and returns it
    public BufferedImage findImageFromFile(Car car){
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("pics/" + car.getModelName() + ".jpg"));
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return image;
    }


}
