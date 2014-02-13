package bensku.plugin.ItemAPI.exception;

/**
 * Thrown when trying to get properties of null ItemStack
 * @author bensku
 * @since 2.00
 *
 */
public class NullItemException extends Exception {
    private static final long serialVersionUID = -7217039421715770929L;

    public NullItemException(String message) {
        super(message);
    }
}
