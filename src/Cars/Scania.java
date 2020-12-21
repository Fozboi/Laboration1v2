package Cars;

import java.awt.*;

/**
 * Lastbil Cars.Scania, har ett flak med en vinkel
 */
public class Scania extends Truck{
    private double trailerAngle;
    private final double trailerMaxAngle = 70;

    public Scania(){
        setNrDoors(2);
        setColor(Color.white);
        setEnginePower(90);
        setModelName("Scania");
        stopEngine();
    }

    @Override
    public double speedFactor(){
        if(trailerIsUp()){
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
    @Override
     public void setTrailerAngle(double newAngle){

        if(newAngle <= trailerMaxAngle && newAngle >= 0){

            if(getCurrentSpeed() == 0){
                trailerAngle = newAngle;
            }
            else{
                throw new IllegalStateException("Cannot move trailer while car is moving");
            }
        }
        else{
            throw new IllegalArgumentException("Only angles between 0 and 70 allowed");
        }
    }

    /**
     * ger vinkeln på flaker
     */
    @Override
    public double getTrailerAngle(){
        return trailerAngle;
    }

    @Override
    public boolean trailerIsUp() {
        return trailerAngle == 0;
    }

    @Override
    public boolean trailersIsDown() {
        return trailerAngle == trailerMaxAngle;
    }

    @Override
    public void setTrailerDown() {
        setTrailerAngle(trailerMaxAngle);
    }

    @Override
    public void setTrailerUp() {
        setTrailerAngle(0);
    }


}
