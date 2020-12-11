package Cars;

import Loaders.IHasTrailer;

public abstract class Truck extends Car implements IHasTrailer {
    public abstract void setTrailerAngle(double newAngle);
    public abstract double getTrailerAngle();
    @Override
    public abstract double speedFactor();
}
