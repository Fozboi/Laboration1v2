interface Loadable<T> {
    void loadCar(T car);
    boolean canLoadCar(T car);
    void unloadCar(T car);
}
