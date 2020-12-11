package Loaders;
/**
 * Samlar trailer-metoder
 */
public interface IHasTrailer {
    void setTrailerAngle(double newAngle);
    double getTrailerAngle();
    boolean trailerIsUp();
    boolean trailersIsDown();
    void setTrailerDown();
    void setTrailerUp();
}
