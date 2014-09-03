package bensku.plugin.ItemAPI.exception;

/**
 * Thrown when UUID caching is disabled an some plugin tries to use it.
 * @author bensku
 * @since 2.03
 *
 */
public class UUIDCacheException extends RuntimeException {
    private static final long serialVersionUID = -4116795320090270740L;

    public UUIDCacheException() {
        super("UUID caching is not enabled!");
    }
}