package fundamentals_of_data_structures.emergency_ward_triage;

/**
 * Interface for locating location-aware entries with integer locations.
 * @author elder
 */
public interface Locator<E> {    
    public int get(E e);
    public void set (E e, int pos);
}
