package bensku.plugin.ItemAPI.api.event;

/**
 * Possible item event types
 * @author bensku
 *
 */
public enum EventType {
    /**
     * Active event handler, such as click or attack
     */
    ACTIVE,
    
    /**
     * "Passive" event handler, such as item holder getting damaged etc.
     */
    PASSIVE
    ;
}
