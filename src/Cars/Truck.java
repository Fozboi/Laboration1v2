package Cars;

public abstract class Truck extends Car {
    abstract void setTrailerAngle(double newAngle);
    abstract double getTrailerAngle();
    @Override
    abstract double speedFactor();
}
