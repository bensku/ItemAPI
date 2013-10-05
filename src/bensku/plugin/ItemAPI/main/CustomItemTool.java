package bensku.plugin.ItemAPI.main;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import bensku.plugin.ItemAPI.util.HideTool;
import bensku.plugin.ItemAPI.util.TagTool;



/**
 * @author bensku
 *
 */
public class CustomItemTool {
    public static Map<String,Class<?>> itemClassMap = new HashMap<String,Class<?>>();
    
    /**
     * Registers custom item
     * @param className
     */
    public static void addCustomItem(Class<?> c) {
        String codeName = GetItemInfo.getCodeName(c);
        itemClassMap.put(codeName, c);
        
    }
    /**
     * Returns custom itemStack
     * @param itemName
     * @return ItemStack
     */
    public static ItemStack getCustomItemStack(String codeName, Material material, 
            int count) {
        ItemStack stack = new ItemStack(material, count);
        ItemMeta itemMeta = stack.getItemMeta();
        Class<?> c = GetItemInfo.getClassName(codeName);
        
        String data = TagTool.newTag("", "codename", codeName);
        String hiddenData = HideTool.hideString(data);
        itemMeta.setDisplayName(hiddenData + GetItemInfo.getDisplayName(c));
        
        stack.setItemMeta(itemMeta);
        return stack;
        
    }
}
