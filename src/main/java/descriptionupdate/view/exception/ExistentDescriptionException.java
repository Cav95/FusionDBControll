package descriptionupdate.view.exception;

/**
 * Exception thrown when a description already exists.
 */
public class ExistentDescriptionException extends RuntimeException {

    /**
     * Constructs a new ExistentDescriptionException with the specified detail
     * message.
     *
     * @param message the detail message
     */
    public ExistentDescriptionException(final String message) {
        super(message);
    }

}
