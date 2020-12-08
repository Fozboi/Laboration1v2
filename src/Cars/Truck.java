package Cars;

public abstract class Truck extends Car {
    public abstract void setTrailerAngle(double newAngle);
    public abstract double getTrailerAngle();
    @Override
    public abstract double speedFactor();
}
