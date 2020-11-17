import java.awt.*;
import java.util.ArrayList;
import java.util.TreeMap;

public class Transporter extends Car implements Loadable<Car>{
    Scania hasATruck;
    int carCapacity;
    double pickupRange;

    private ArrayList<Car> loadedCars = new ArrayList<Car>();

    public Transporter(int carCapacity){
        hasATruck = new Scania();
        this.carCapacity = carCapacity;
        pickupRange = 10;
    }

    public ArrayList<Car> getLoadedCars(){
        return loadedCars;
    }

    void setRampUp(){
        hasATruck.setTrailerAngle(0);
    }
    void setRampDown(){
        hasATruck.setTrailerAngle(70);
    }

    public boolean canLoadCar(Car car){
        double xdif = car.getPosition().getX() - hasATruck.getPosition().getX();
        double ydif = car.getPosition().getY() - hasATruck.getPosition().getY();

        if(xdif > pickupRange || ydif > pickupRange){
            return false;
        } else if(car instanceof Transporter){
            return false;
        }else if(loadedCars.size() >= carCapacity){
            return false;
        }else if(hasATruck.getTrailerAngle() != 70){
            return false;
        }else
            return true;
    }

    public void loadCar(Car car){
        if (canLoadCar(car)){
            loadedCars.add(car);
        }
    }

    public void unloadLastCar(){
        Car lastCar = loadedCars.get(loadedCars.size()-1);
        unloadCar(lastCar);
    }

    public void unloadCar(Car car){
        if (loadedCars.size() > 0){
            loadedCars.remove(car);

            int xIntPos = (int) hasATruck.getPosition().getX();
            int yIntPos = (int) hasATruck.getPosition().getY();

            if(hasATruck.getDir() == NORTH){
                yIntPos += pickupRange;
            }
            else if(hasATruck.getDir() == SOUTH){
                yIntPos += -pickupRange;
            }
            else if(hasATruck.getDir() == WEST){
                xIntPos += pickupRange;
            }
            else if(hasATruck.getDir() == EAST){
                xIntPos += -pickupRange;
            }
            Point newPos = new Point(xIntPos, yIntPos);
            car.setPosition(newPos);
        }
    }


    @Override
    public void move() {
        hasATruck.move();
        Point newPos = hasATruck.getPosition();

        for(int i = 0; i < loadedCars.size(); i++){
            loadedCars.get(i).setPosition(newPos);
        }
    }

    @Override
    double speedFactor() {
        return hasATruck.speedFactor();
    }
}
