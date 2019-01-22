package fundamentals_of_data_structures.integral_images;

/**
 *
 * @author jameselder
 */
public class InvalidImageException extends Exception {

    /**
     * Creates a new instance of <code>InvalidImageException</code> without detail message.
     */
    public InvalidImageException() {
    }


    /**
     * Constructs an instance of <code>InvalidImageException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public InvalidImageException(String msg) {
        super(msg);
    }
}
