import java.awt.*;
import java.util.SortedMap;
import java.util.TreeMap;

public class Transporter extends Car{

    private boolean rampDown; //false by default
    private SortedMap<Integer, String> loadedCars = new TreeMap<Integer, String>();

    public Transporter(){
        setNrDoors(2);
        setColor(Color.black);
        setEnginePower(90);
        setModelName("Transporter");
        stopEngine();
    }


    double speedFactor(){
        if(rampDown == false){
            return getEnginePower() * 0.01;
        }
        else{
            return 0;
        }
    }

    boolean canLoadCar(){
        if (loadedCars < 2){ // max 2 cars on transport
            loadedCars++;
            return true;
        }
        else return false;
    }


}
