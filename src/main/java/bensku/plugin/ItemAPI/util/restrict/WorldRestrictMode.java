package bensku.plugin.ItemAPI.util.restrict;

/**
 * Nice enumeration for world restriction types
 * @author bensku
 * @since 2.03
 *
 */
public enum WorldRestrictMode {
    /**
     * Automatically enabled in any world. Use that instead of ALLOWING if you don't 
     * have any restrictions, it saves resources.
     */
    NONE,
    
    /**
     * Only blacklisted world are disabled. Use NONE instead if you don't have any
     * restrictions, it saves resources.
     */
    ALLOWING,
    
    /**
     * Only whitelisted worlds are enabled. This is the he safest solution if you have
     * many worlds
     */
    DENYING
}
