/**
 * Samlar metoder för att lasta bilar
 */
interface Loadable<T> {
    void loadCar(T car);
    boolean canLoadCar(T car);
    void unloadCar(T car);
}
