package Loaders;

import Cars.*;

import java.awt.*;
import java.util.ArrayList;

/**
 * Lastbil Loaders.Transporter, har ett flak med enbart två lägen.
 */
public class Transporter implements Loadable<Car>, IHasEngine, IMoveable,IHasTrailer{
    Truck hasATruck;
    int carCapacity;
    double pickupRange;
    private ArrayList<Car> loadedCars;

    /**
     * konstruktor, skapar en Cars.Scania-lastbil som via specifikationsarv ger transporter rörelsemöjligheter
     * @param carCapacity maxkapacitet antal bilar
     */
    public Transporter(int carCapacity){
        hasATruck = new Scania();
        this.carCapacity = carCapacity;
        pickupRange = 30;
        loadedCars = new ArrayList<>(carCapacity);
    }

    public ArrayList<Car> getLoadedCars(){
        return loadedCars;
    }

    public void setRampUp(){
        hasATruck.setTrailerUp();
    }
    public void setRampDown(){
        hasATruck.setTrailerDown();
    }
    /**
     * kollar om bilen kan lastas.
     * Alltså att den är tillräckligt nöra transportern, att den inte är en lastbil,
     * att transporterns flak inte är fullt, samt att flaket är i ned-positionen.
     * @param car bilen som ska lastas
     */
    public boolean canLoadObject(Car car){
        if(!inRange(car)){
            return false;
        }else if(loadedCars.contains(car)){
            return false;
        } else if(car instanceof Truck){
            return false;
        }else if(loadedCars.size() >= carCapacity){
            return false;
        }else if(!trailersIsDown()){
            return false;
        }else
            return true;
    }

    public boolean inRange(Car car){
        double xdif = car.getPosition().getX() - hasATruck.getPosition().getX();
        double ydif = car.getPosition().getY() - hasATruck.getPosition().getY();

        if(Math.abs(xdif) > pickupRange || Math.abs(ydif) > pickupRange){
            return false;
        }else
            return true;
    }

    public void loadObject(Car car){
        if (canLoadObject(car)){
            loadedCars.add(car);
        }
    }

    public void unloadLastCar(){
        Car lastCar = loadedCars.get(loadedCars.size()-1);
        unloadObject(lastCar);
    }
    /**
     * Tar bort bilen som lastas av från listan över lastade bilar,
     * samt placerar den ett bestämt avstånd bakom transportern.
     * @param car bilen som ska lastas av flaket.
     */
    public void unloadObject(Car car){
        if (loadedCars.size() > 0){
            loadedCars.remove(car);

            int xIntPos = (int) hasATruck.getPosition().getX();
            int yIntPos = (int) hasATruck.getPosition().getY();

            if(hasATruck.getDir() == hasATruck.NORTH){
                yIntPos += pickupRange;
            }
            else if(hasATruck.getDir() == hasATruck.SOUTH){
                yIntPos += -pickupRange;
            }
            else if(hasATruck.getDir() == hasATruck.WEST){
                xIntPos += pickupRange;
            }
            else if(hasATruck.getDir() == hasATruck.EAST){
                xIntPos += -pickupRange;
            }
            Point newPos = new Point(xIntPos, yIntPos);
            car.setPosition(newPos);
        }
    }
    /**
     * Flyttar transportern, och ser till att alla lastade bilar flyttar med den.
     */
    @Override
    public void move() {
        hasATruck.move();
        Point newPos = hasATruck.getPosition();

        for(int i = 0; i < loadedCars.size(); i++){
            loadedCars.get(i).setPosition(newPos);
            loadedCars.get(i).setDir(hasATruck.getDir());
        }
    }

    @Override
    public void turnLeft() {
        hasATruck.turnLeft();
    }
    @Override
    public void turnRight() {
        hasATruck.turnLeft();
    }
    double speedFactor() {
        return hasATruck.speedFactor();
    }
    @Override
    public Point getPosition(){return hasATruck.getPosition();}
    @Override
    public void setPosition(Point newPos){ hasATruck.setPosition(newPos);}
    @Override
    public double getEnginePower() {return hasATruck.getEnginePower();}
    @Override
    public void setEnginePower(double enginePower) { }
    @Override
    public void gas(double amount){hasATruck.gas(amount);}
    @Override
    public void brake(double amount){hasATruck.brake(amount);}
    @Override
    public void startEngine(){hasATruck.startEngine();}
    @Override
    public void stopEngine(){hasATruck.stopEngine();}
    @Override
    public double getCurrentSpeed() {return hasATruck.getCurrentSpeed(); }
    @Override
    public void setCurrentSpeed(double newSpeed) {hasATruck.setCurrentSpeed(newSpeed);}
    @Override
    public int getDir() {return hasATruck.getDir();}
    @Override
    public void setDir(int newDir) {hasATruck.setDir(newDir);}
    public Truck getTruck(){ return hasATruck; }
    public double getPickupRange(){return pickupRange;}
    @Override
    public void setTrailerAngle(double newAngle) {throw new IllegalArgumentException("Transporters can only have their ramp/trailer up or down");}
    @Override
    public double getTrailerAngle() {throw new IllegalArgumentException("Transporters do now have an angle of their ramp");}
    @Override
    public boolean trailerIsUp() {return hasATruck.trailerIsUp();}
    @Override
    public boolean trailersIsDown() {return hasATruck.trailersIsDown();}
    @Override
    public void setTrailerDown() {setRampDown();}
    @Override
    public void setTrailerUp() { setRampUp();}
}
