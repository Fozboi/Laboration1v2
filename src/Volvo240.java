import java.awt.*;

/**
 * subklass Volvo240 ärver car, är trimmad
 */
public class Volvo240 extends Car{

    private final static double trimFactor = 1.25;

    
    public Volvo240(){
        setNrDoors(4);
        setColor(Color.black);
        setEnginePower(100);
        setModelName("Volvo240");
        stopEngine();
    }
    

    
    double speedFactor(){
        return getEnginePower() * 0.01 * trimFactor;
    }

}
