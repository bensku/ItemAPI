package bensku.plugin.ItemAPI.itemData;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

import bensku.plugin.ItemAPI.main.ItemAPI;

/**
 * Item data object. You should use CustomItem and CustomStack usually, this
 * is implementation, not api.
 * @author bensku
 * @since 2.03
 *
 */
public class ItemData {
    private ItemStack item;
    private ItemAPI api;
    
    public ItemData(ItemStack item, ItemAPI api) {
        this.item = item;
        this.api = api;
    }
    
    /**
     * Creates new instance of ItemData for given ItemStack
     * @param item
     */
    public ItemData(ItemStack item) {
        this(item, ItemAPI.getAPI());
    }
    
    /**
     * Sets data in item.
     * @param key
     * @param value
     */
    public void setData(Object key, Object value) {
        DataHandler handler = this.api.getDataHandler();
        handler.setData(this.item, key, value);
    }
    
    /**
     * 
     * @param key
     * @return data using given key from item
     */
    public Object getData(Object key) {
        DataHandler handler = this.api.getDataHandler();
        return handler.getData(this.item, key);
    }
}
