package bensku.plugin.ItemAPI.itemData.handlers;

import java.util.List;

import org.bukkit.inventory.ItemStack;

import bensku.plugin.ItemAPI.itemData.DataHandler;

/**
 * TODO: Class not ready
 * @author benjami
 *
 */
public class LoreHandler implements DataHandler {
    @Override
    public void setData(ItemStack item, Object key, Object value) {
        
    }

    @Override
    public Object getData(ItemStack item, Object key) {
        List<String> lore = item.getItemMeta().getLore();
        for (int i = 1; i < lore.size(); i++) {
            String line = lore.get(i);
            
            if ( line.startsWith("[ItemAPI]") ) {
                line = line.replace("[ItemAPI]", "");
                String data = line.split("|")[1];
            }
        }
        
        return lore;
    }

}
