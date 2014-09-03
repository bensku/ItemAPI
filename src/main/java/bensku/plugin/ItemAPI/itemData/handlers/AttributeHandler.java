package bensku.plugin.ItemAPI.itemData.handlers;

import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import bensku.plugin.ItemAPI.itemData.DataHandler;
import bensku.plugin.ItemAPI.main.ItemAPI;

import com.comphenix.attribute.AttributeStorage;

/**
 * Data handler for AttributeStorage. Names using Strings instead of UUIDs are using
 * UUID caching.
 * @author bensku
 * @since 2.03
 *
 */
public class AttributeHandler implements DataHandler {
    @Override
    public void setData(ItemStack item, Object key, Object value) {
        if ( item == null || item.getType().equals(Material.AIR) ) {
            return;
        }
        
        if ( key instanceof UUID ) {
            AttributeStorage storage = AttributeStorage.newTarget(item, (UUID) key);
            storage.setData(value.toString());
        } else {
            UUID uuid = ItemAPI.getAPI().getUUIDCache().getUUID(key.toString());
            AttributeStorage storage = AttributeStorage.newTarget(item, uuid);
            storage.setData(value.toString());
        }
    }

    @Override
    public Object getData(ItemStack item, Object key) {
        if ( item == null || item.getType().equals(Material.AIR) ) {
            return null;
        }
        
        if ( key instanceof UUID ) {
            AttributeStorage storage = AttributeStorage.newTarget(item, (UUID) key);
            return storage.getData(null);
        } else {
            UUID uuid = ItemAPI.getAPI().getUUIDCache().getUUID(key.toString());
            AttributeStorage storage = AttributeStorage.newTarget(item, uuid);
            return storage.getData(null);
        }
    }

    @Override
    public String getName() {
        return "AttributeHandler";
    }
}
