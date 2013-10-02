package bensku.plugin.ItemAPI.main;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
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
    public static Map<String,String> itemClassMap = new HashMap<String,String>();
    
    /**
     * Registers custom item
     * @param className
     */
    public static void addCustomItem(String className) {
        String codeName = GetItemInfo.getCodeName(className);
        itemClassMap.put(codeName, className);
        
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
        String className = GetItemInfo.getClassName(codeName);
        
        String data = "<itemapi></itemapi>" + ChatColor.RESET;
        data = TagTool.newTag(data, "itemapi", "customname", codeName);
        String hiddenData = HideTool.hideString(data);
        itemMeta.setDisplayName(hiddenData + GetItemInfo.getDisplayName(className));
        
        stack.setItemMeta(itemMeta);
        return stack;
        
    }
}
