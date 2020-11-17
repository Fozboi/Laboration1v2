import java.util.ArrayList;

/**
 * Verkstad för bilar, implementerar Loadable
 * @param <T> typvariabel, beskriver vilken typ av bilar som accepteras av verkstaden
 */
public class Workshop<T> implements Loadable<T> {
    int carCapacity;
    private ArrayList<T> loadedCars;

    /**
     * Konstruktor, initierar en ArrayList loadedCars som håller reda på "lastade" bilar
     */
    public Workshop(int carCapacity){
        this.carCapacity = carCapacity;
        loadedCars = new ArrayList<>(carCapacity);
    }

    /**
     * Lastar bil
     */
    public void loadCar(T car){
        if(canLoadCar(car)){
            loadedCars.add(car);
        }
    }

    /**
     * Kollar om det finns plats i verkstaden
     */
    public boolean canLoadCar(T car) {
        if(loadedCars.size() > carCapacity){
            return true;
        }else
            return false;
    }

    public void unloadCar(T car) {
        loadedCars.remove(car);
    }


}
