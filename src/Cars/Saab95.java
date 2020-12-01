package Cars;

import java.awt.*;

/**
 * subklass Cars.Saab95 ärver Cars.Car, har en turbo
 */
public class Saab95 extends Car{

    private boolean turboOn;

    
    public Saab95(){
        setNrDoors(2);
        setColor(Color.red);
        setEnginePower(125);
	    setTurboOff();
        setModelName("Cars.Saab95");
        stopEngine();
    }
    


    public void setTurboOn(){
	    turboOn = true;
    }

    public void setTurboOff(){
	    turboOn = false;
    }
    
    double speedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return getEnginePower() * 0.01 * turbo;
    }





}
