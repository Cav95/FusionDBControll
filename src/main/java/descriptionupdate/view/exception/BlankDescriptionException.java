package descriptionupdate.view.exception;

/**
 * Exception thrown when a description field is left blank.
 */
public class BlankDescriptionException extends RuntimeException {

    /**
     * Constructs a new BlankDescriptionException with the specified detail message.
     *
     * @param message the detail message
     */
    public BlankDescriptionException(final String message) {
        super(message);
    }

}
