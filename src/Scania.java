import java.awt.*;

public class Scania extends Car {
    private double trailerAngle;

    public Scania(){
        setNrDoors(2);
        setColor(Color.white);
        setEnginePower(90);
        setModelName("Scania");
        stopEngine();
    }

    double speedFactor(){
        if(trailerAngle == 0){
            return getEnginePower() * 0.01;
        }
        else{
            throw new IllegalStateException("Cannot move while trailer is raised");
        }
    }

    void setTrailerAngle(double newAngle){
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

    public static void main(String[] args){
        Scania bil = new Scania();
        bil.setTrailerAngle(60);
        bil.setTrailerAngle(0);
        bil.gas(1);

    }


}
