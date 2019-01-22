package fundamentals_of_data_structures.integral_images;

/**
 *
 * @author jameselder
 */
public class NullSubImageException extends Exception {

    /**
     * Creates a new instance of <code>NullSubImageException</code> without
     * detail message.
     */
    public NullSubImageException() {
    }

    /**
     * Constructs an instance of <code>NullSubImageException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NullSubImageException(String msg) {
        super(msg);
    }
}
