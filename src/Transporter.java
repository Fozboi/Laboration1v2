import java.awt.*;
import java.util.SortedMap;
import java.util.TreeMap;

public class Transporter extends Car{
    Scania hasATruck;

    private SortedMap<Integer, String> loadedCars = new TreeMap<Integer, String>();

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
        if (loadedCars < 2){ // max 2 cars on transport
            loadedCars++;
            return true;
        }
        else return false;
    }


    @Override
    double speedFactor() {
        return hasATruck.speedFactor();
    }
}
