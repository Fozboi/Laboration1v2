import java.awt.*;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class Transporter extends Car{
    Scania hasATruck;

    private SortedMap<Integer, Car> loadedCars = new TreeMap<Integer, Car>();

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
        Integer lastKey = loadedCars.lastKey();

        if (lastKey < 2){
            loadCar();
            return true;
        } else return false;
    }


    @Override
    double speedFactor() {
        return hasATruck.speedFactor();
    }
}
