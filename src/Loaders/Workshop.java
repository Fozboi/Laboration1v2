package Loaders;

import Cars.Car;
import Loaders.Loadable;

import java.util.ArrayList;

/**
 * Verkstad för bilar, implementerar Cars.Loadable
 * @param <T> typvariabel, beskriver vilken typ av bilar som accepteras av verkstaden
 */
public class Workshop<T extends Car> implements Loadable<T> {
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
    public void loadObject(T car){
        if(canLoadObject(car)){
            loadedCars.add(car);
        }
    }

    /**
     * Kollar om det finns plats i verkstaden
     */
    public boolean canLoadObject(T car) {
        if(loadedCars.size() > carCapacity){
            return true;
        }else
            return false;
    }

    public void unloadObject(T car) {
        loadedCars.remove(car);
    }


}
