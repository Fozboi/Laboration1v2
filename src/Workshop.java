import java.util.ArrayList;

public class Workshop<T> implements Loadable<T> {
    int carCapacity;
    private ArrayList<T> loadedCars = new ArrayList<>();

    public Workshop(int carCapacity){
        this.carCapacity = carCapacity;
    }

    public void loadCar(T car){
        if(canLoadCar(car)){
            loadedCars.add(car);
        }
    }

    public boolean canLoadCar(T car) {
        return false;
    }

    public void unloadCar(T car) {

    }


}
