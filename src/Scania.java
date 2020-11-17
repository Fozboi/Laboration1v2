import java.awt.*;

/**
 * Lastbil Scania, har ett flak med en vinkel
 */
public class Scania extends Car {
    private double trailerAngle;

    public Scania(){
        setNrDoors(2);
        setColor(Color.white);
        setEnginePower(90);
        setModelName("Scania");
        stopEngine();
    }

    public double speedFactor(){
        if(trailerAngle == 0){
            return getEnginePower() * 0.01;
        }
        else{
            throw new IllegalStateException("Cannot move while trailer is raised");
        }
    }

    /**
     * Sätter flakets vinkel till önskad vinkel om vinkeln är mellan 0 och 70
     * och bilen ej rör sig
     * @param newAngle vinkel flaket önskas flyttas till
     */
     public void setTrailerAngle(double newAngle){
        if(newAngle <= 70 && newAngle >= 0){
            if(getCurrentSpeed() == 0){
                trailerAngle = newAngle;
            }
            else{
                throw new IllegalStateException("Cannot raise trailer while car is moving");
            }
        }
        else{
            throw new IllegalArgumentException("Only angles between 0 and 70 allowed");
        }
    }

    public double getTrailerAngle(){
        return trailerAngle;
    }

    public static void main(String[] args){
        Scania bil = new Scania();
        bil.setTrailerAngle(60);
        bil.setTrailerAngle(0);
        bil.gas(1);

    }


}
