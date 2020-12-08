package Loaders;

/**
 * Samlar metoder för att lasta bilar
 */
interface Loadable<T> {
    void loadObject(T object);
    boolean canLoadObject(T object);
    void unloadObject(T object);
}
