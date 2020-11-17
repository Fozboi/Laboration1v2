import java.awt.*;
import java.util.TreeMap;

public class Transporter extends Car{
    Scania hasATruck;

    private TreeMap<Integer, Car> loadedCars = new TreeMap<Integer, Car>();

    public Transporter(){
        hasATruck = new Scania();
    }

    void setRampUp(){
        hasATruck.setTrailerAngle(0);
    }
    void setRampDown(){
        hasATruck.setTrailerAngle(70);
    }

    boolean canLoadCar(){
        int lastKey = loadedCars.lastKey();
        double trailerAngle = hasATruck.getTrailerAngle();

        if (lastKey < 2 && trailerAngle == 70)
            return true;
        else return false;
    }

    public void loadCar(){
        if (canLoadCar()){
            loadedCars.put(loadedCars.lastKey()+1,);
        }
    }

    public void unLoadCar(){

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
