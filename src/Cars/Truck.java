package Cars;

import Loaders.IHasTrailer;
/**
 * subklass Cars.Truck ärver Cars.Car, har en trailer
 */
public abstract class Truck extends Car implements IHasTrailer {
    public abstract void setTrailerAngle(double newAngle);
    public abstract double getTrailerAngle();
    @Override
    public abstract double speedFactor();
}
