import java.awt.*;

/**
 * Superklass Car, håller reda på en bils riktning, position och hastighet
 * Innehåller samtliga bilars funktioner men vissa override:as av subklasser
 */
public class Car implements Moveable{
    private Point position = new Point(200,200);

    private int dir;
    static final int NORTH = 0;
    static final int EAST = 1;
    static final int SOUTH = 2;
    static final int WEST = 3;

    private int nrDoors; // Number of doors on the car
    private double enginePower; // Engine power of the car
    private double currentSpeed; // The current speed of the car
    private Color color; // Color of the car
    private String modelName; // The car model name


    public void move(){
        int xIntPos = (int) position.getX();
        int yIntPos = (int) position.getY();

        if(dir == NORTH){
            yIntPos += -currentSpeed;
        }
        else if(dir == SOUTH){
            yIntPos += currentSpeed;
        }
        else if(dir == WEST){
            xIntPos += -currentSpeed;
        }
        else if(dir == EAST){
            xIntPos += +currentSpeed;
        }
        position = new Point(xIntPos,yIntPos);
    } //metoden tar bilens position och ändrar den till en ny position baserad på bilens rikting och hastighet

    public void turnLeft(){
        dir = (dir+3)%4;
    }
    public void turnRight(){
        dir = (dir+1)%4;
    }

    public void gas(double amount){
        if (0 <= amount && amount <= 1) {
            incrementSpeed(amount);
        }
        else {
            throw new IllegalArgumentException("Only values between 0 and 1 are valid");
        }
    }

    public void brake(double amount){
        if (0 <= amount && amount <= 1) {
            decrementSpeed(amount);
        }
        else {
            throw new IllegalArgumentException("Only values between 0 and 1 are valid");
        }
    }

    private void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + this.speedFactor() * amount,enginePower);
    }

    private void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - this.speedFactor() * amount,0);
    }

    double speedFactor(){
        return 1;
    }

    public void setNrDoors(int nrDoors){this.nrDoors = nrDoors;}
    public int getNrDoors(){
        return nrDoors;
    }

    public void setEnginePower(double enginePower){this.enginePower = enginePower;}
    public double getEnginePower(){
        return enginePower;
    }

    public void setCurrentSpeed(double speed){currentSpeed = speed;}
    public double getCurrentSpeed(){
        return currentSpeed;
    }

    public void setColor(Color clr){ color = clr;}
    public Color getColor(){
        return color;
    }

    public void startEngine(){
        currentSpeed = 0.1;
    }
    public void stopEngine(){
        currentSpeed = 0;
    }

    public void setDir(int dir){this.dir = dir;}
    public int getDir(){return dir;}

    public void setModelName(String modelName){this.modelName = modelName;}
    public String getModelName(){return modelName;}

    public Point getPosition(){return position;}


}
