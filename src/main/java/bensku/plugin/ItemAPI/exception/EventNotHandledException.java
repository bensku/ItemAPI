package bensku.plugin.ItemAPI.exception;

/**
 * Thrown when custom item don't have event listener for some event
 * @author bensku
 * @since 2.00
 *
 */
public class EventNotHandledException extends RuntimeException {
    private static final long serialVersionUID = 6077628840167277105L;

    public EventNotHandledException(String message) {
        super(message);
    }
}