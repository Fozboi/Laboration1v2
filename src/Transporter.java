import java.awt.*;
import java.util.ArrayList;

public class Transporter implements Loadable<Car>,Moveable{
    Scania hasATruck;
    int carCapacity;
    double pickupRange;
    private ArrayList<Car> loadedCars;

    public Transporter(int carCapacity){
        hasATruck = new Scania();
        this.carCapacity = carCapacity;
        pickupRange = 10;
        loadedCars = new ArrayList<>(carCapacity);
    }

    public ArrayList<Car> getLoadedCars(){
        return loadedCars;
    }

    void setRampUp(){
        hasATruck.setTrailerAngle(0);
    }
    void setRampDown(){
        hasATruck.setTrailerAngle(70);
    }

    public boolean canLoadCar(Car car){
        double xdif = car.getPosition().getX() - hasATruck.getPosition().getX();
        double ydif = car.getPosition().getY() - hasATruck.getPosition().getY();

        if(Math.abs(xdif) > pickupRange || Math.abs(ydif) > pickupRange){
            return false;
        } else if(car instanceof Scania){
            return false;
        }else if(loadedCars.size() >= carCapacity){
            return false;
        }else if(hasATruck.getTrailerAngle() != 70){
            return false;
        }else
            return true;
    }

    public void loadCar(Car car){
        if (canLoadCar(car)){
            loadedCars.add(car);
        }
    }

    public void unloadLastCar(){
        Car lastCar = loadedCars.get(loadedCars.size()-1);
        unloadCar(lastCar);
    }

    public void unloadCar(Car car){
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


    @Override
    public void move() {
        hasATruck.move();
        Point newPos = hasATruck.getPosition();

        for(int i = 0; i < loadedCars.size(); i++){
            loadedCars.get(i).setPosition(newPos);
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

    public Point getPosition(){return hasATruck.getPosition();}
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






}
