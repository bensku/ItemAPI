package bensku.plugin.ItemAPI.itemData;

import org.bukkit.inventory.ItemStack;

import bensku.plugin.ItemAPI.main.Config;
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
    private DataManager dataMgr;
    private ItemAPI api;
    
    public ItemData(ItemStack item, ItemAPI api, DataManager dataMgr) {
        this.item = item;
        this.api = api;
        this.dataMgr = dataMgr;
    }
    
    /**
     * Creates new instance of ItemData for given ItemStack
     * @param item
     */
    public ItemData(ItemStack item) {
        this(item, ItemAPI.getAPI(), ItemAPI.getAPI().getDataManager());
    }
    
    /**
     * Sets data in item.
     * @param key
     * @param value
     */
    public void setData(Object key, Object value) {
        Config config = this.api.getConfig();
        String handlerName = config.getString("data-save.handler");
        DataHandler handler = this.dataMgr.getHandler(handlerName);
        handler.setData(this.item, key, value);
    }
    
    /**
     * 
     * @param key
     * @return data using given key from item
     */
    public Object getData(Object key) {
        Config config = this.api.getConfig();
        String handlerName = config.getString("data-save.handler");
        DataHandler handler = this.dataMgr.getHandler(handlerName);
        return handler.getData(this.item, key);
    }
}
