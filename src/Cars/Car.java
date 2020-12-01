package Cars;

import java.awt.*;

/**
 * Superklass Cars.Car, håller reda på en bils riktning, position och hastighet
 * Innehåller samtliga bilars funktioner men vissa override:as av subklasser
 */
public abstract class Car implements Moveable{
    private Point position = new Point(0,0);
    private double xcoord;
    private double ycoord;

    private int dir;
    public static final int NORTH = 0;
    public static final int EAST = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;

    private int nrDoors; // Number of doors on the car
    private double enginePower; // Engine power of the car
    private double currentSpeed; // The current speed of the car
    private Color color; // Color of the car
    private String modelName; // The car model name


    /**
     * metoden move, tar en position för en bil och utifrån rikting förskjuter den längs axel
     */
    public void move(){
        if(dir == NORTH){
            ycoord += -currentSpeed;
        }
        else if(dir == SOUTH){
            ycoord += currentSpeed;
        }
        else if(dir == WEST){
            xcoord += -currentSpeed;
        }
        else if(dir == EAST){
            xcoord += +currentSpeed;
        }
        position = new Point((int) xcoord,(int) ycoord);
    } //metoden tar bilens position och ändrar den till en ny position baserad på bilens rikting och hastighet

    public void turnLeft(){
        dir = (dir+3)%4;
    }
    public void turnRight(){
        dir = (dir+1)%4;
    }

    /**
     * Anropar incrementSpeed, skickar vidare "gas-faktor"
     * Kollar att amount ligger mellan 0 och 1
     *
     * @param amount
     * "gas-faktor"
     */
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

    abstract double speedFactor();

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

    public void setPosition(Point position){this.position = position;}
    public Point getPosition(){return position;}


}
