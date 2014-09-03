package bensku.plugin.ItemAPI.itemData;

import org.bukkit.inventory.ItemStack;

/**
 * Parent interface for all data handlers.
 * @author bensku
 * @since 2.03
 *
 */
public interface DataHandler {
    /**
     * Sets data for key in ItemStack.
     * @param item
     * @param key
     * @param value
     * @return new ItemStack with custom data
     */
    public void setData(ItemStack item, Object key, Object value);
    /**
     * 
     * @param item
     * @param key
     * @return data for given key in given ItenStack
     */
    public Object getData(ItemStack item, Object key);
}
