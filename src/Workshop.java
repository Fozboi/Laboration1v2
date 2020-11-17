import java.util.TreeMap;

public class Workshop implements Loadable {
    int carCapacity;
    private TreeMap<Integer, Car> loadedCars = new TreeMap<>();

    public Workshop(int carCapacity){
        this.carCapacity = carCapacity;
    }

    public void loadCar(Car car){

    }

    public boolean canLoadCar(Car car) {

    }

    public void unloadCar(Car car) {

    }


}
