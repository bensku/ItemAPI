package bensku.plugin.ItemAPI.exception;

/**
 * Thrown when config cannot loaded from file due to some error.
 * @author bensku
 * @since 2.03
 *
 */
public class ConfigException extends RuntimeException {
    private static final long serialVersionUID = 7402056170661950246L;

    public ConfigException(String message) {
        super(message);
    }
}