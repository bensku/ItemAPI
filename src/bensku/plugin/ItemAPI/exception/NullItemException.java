package bensku.plugin.ItemAPI.exception;

/**
 * Thrown when trying to get properties of null ItemStack
 * @author bensku
 *
 */
public class NullItemException extends Exception {
    public NullItemException(String message) {
        super(message);
    }
}
