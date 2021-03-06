package fundamentals_of_data_structures.depth_first_search;

/**
 *
 * @author jameselder
 */
public class CircularPreRequisiteException extends Exception {

    /**
     * Creates a new instance of <code>CircularPreRequisiteException</code> without detail message.
     */
    public CircularPreRequisiteException() {
    }


    /**
     * Constructs an instance of <code>CircularPreRequisiteException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public CircularPreRequisiteException(String msg) {
        super(msg);
    }
}
