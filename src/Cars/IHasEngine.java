package Cars;
/**
 * Samlar motor-metoder
 */
public interface IHasEngine {
    void gas(double amount);
    void brake(double amount);
    void startEngine();
    void stopEngine();
    double getEnginePower();
    void setEnginePower(double enginePower);


}