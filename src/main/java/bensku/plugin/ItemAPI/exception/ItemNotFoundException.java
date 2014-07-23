package bensku.plugin.ItemAPI.exception;

/**
 * Thrown when custom item not found (when using codeName)
 * @author bensku
 * @since 2.00
 *
 */
public class ItemNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 7935731441128409105L;

    public ItemNotFoundException(String message) {
        super(message);
    }
}