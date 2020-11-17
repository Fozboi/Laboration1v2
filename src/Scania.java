public class Scania extends Car {
    private double trailerAngle;



    double speedFactor(){
        if(trailerAngle == 0){
            return getEnginePower() * 0.01;
        }
        else{
            return 0;
        }
    }

    void setTrailerAngle(double newAngle){
        
    }


}
