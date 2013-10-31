package bensku.plugin.ItemAPI.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//import org.bukkit.Bukkit;
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
        
        String data = TagTool.newTag("", 
                "codename", codeName);
        //Bukkit.getLogger().info("Debug: Data is " + data);
        String hiddenData = HideTool.hideString(data);
        //Bukkit.getLogger().info("Debug: Hidden data is " + hiddenData);
        itemMeta.setDisplayName(hiddenData + GetItemInfo.getDisplayName(c));
        
        stack.setItemMeta(itemMeta);
        return stack;
        
    }
    public static ItemStack getNamedCustomItemStack(String codeName, 
            Material material, int count, String displayName) {
        ItemStack stack = new ItemStack(material, count);
        ItemMeta itemMeta = stack.getItemMeta();
        //Class<?> c = GetItemInfo.getClassName(codeName);
        
        String data = TagTool.newTag("", "codename", codeName);
        String hiddenData = HideTool.hideString(data);
        itemMeta.setDisplayName(hiddenData + displayName);
        
        stack.setItemMeta(itemMeta);
        return stack;
        
    }
    public static ItemStack addCustomData(ItemStack stack, String tag, String data) {
        ItemMeta itemMeta = stack.getItemMeta();
        String content = HideTool.unhideString(itemMeta.getDisplayName());
        itemMeta.setDisplayName(HideTool.hideString(TagTool.
                newTag(data, tag, content)));
        
        ItemStack finalStack = stack;
        finalStack.setItemMeta(itemMeta);
        return finalStack;
    }
    public static ItemStack setLore(ItemStack stack, ArrayList<String> lore) {
        ItemMeta itemMeta = stack.getItemMeta();
        itemMeta.setLore(lore);
        ItemStack finalStack = stack;
        finalStack.setItemMeta(itemMeta);
        return finalStack;
    }
}
