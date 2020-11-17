import java.awt.*;
import java.util.TreeMap;

public class Transporter extends Car{
    Scania hasATruck;
    int carCapacity;
    double pickupRange;

    private TreeMap<Integer, Car> loadedCars = new TreeMap<Integer, Car>();

    public Transporter(){
        hasATruck = new Scania();
        carCapacity = 3;
        pickupRange = 10;
    }

    void setRampUp(){
        hasATruck.setTrailerAngle(0);
    }
    void setRampDown(){
        hasATruck.setTrailerAngle(70);
    }

    boolean canLoadCar(Car car){
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
            loadedCars.put(loadedCars.lastKey()+1,car);
        }
    }

    public void unloadCar(){
        if (loadedCars.size() > 0){
            Car i = loadedCars.get(loadedCars.lastKey());
            loadedCars.remove(loadedCars.lastKey());
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
