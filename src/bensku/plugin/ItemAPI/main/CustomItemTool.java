package bensku.plugin.ItemAPI.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
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
        Class<?> c = GetItemInfo.getClass(codeName);
        
        String data = TagTool.newTag("", 
                "codename", codeName);
        //Bukkit.getLogger().info("Debug: Data is " + data);
        String hiddenData = HideTool.hideString(data);
        //Bukkit.getLogger().info("Debug: Hidden data is " + hiddenData);
        itemMeta.setDisplayName(hiddenData + GetItemInfo.getDisplayName(c));
        
        stack.setItemMeta(itemMeta);
        return stack;
        
    }
    /**
     * Returns custom itemStack with setted name
     * @param codeName
     * @param material
     * @param count
     * @param displayName
     * @return
     */
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
    /**
     * Adds custom data. Not working!
     * @param stack
     * @param tag
     * @param content
     * @return
     */
    public static ItemStack addCustomData(ItemStack stack, String tag, String content) {
        ItemMeta itemMeta = stack.getItemMeta();
        String data = itemMeta.getDisplayName();
        itemMeta.setDisplayName(data + HideTool.hideString(TagTool.
                newTag("", tag, content)));
        
        Bukkit.getLogger().info("Debug: Display name is " + itemMeta.getDisplayName());
        
        ItemStack finalStack = stack;
        finalStack.setItemMeta(itemMeta);
        return finalStack;
    }
    /**
     * Sets lore of itemStack
     * @param stack
     * @param lore
     * @return new itemStack with lore
     */
    public static ItemStack setLore(ItemStack stack, ArrayList<String> lore) {
        ItemMeta itemMeta = stack.getItemMeta();
        itemMeta.setLore(lore);
        ItemStack finalStack = stack;
        finalStack.setItemMeta(itemMeta);
        return finalStack;
    }
    /**
     * Returns is itemStack custom
     * @param itemStack
     * @return boolean
     */
    public static boolean isCustomStack(ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        if ( itemMeta.getDisplayName().contains(HideTool.hideString("<codename>")) ) {
            return true; //If contains custom name and <codename>
        }
        return false;
    }
    /**
     * Returns codeName of itemStack
     * @param itemStack
     * @return
     */
    public static String getCodeName(ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        String unhiddenName = HideTool.unhideString(itemMeta.getDisplayName());
        String codeName = null;
        
        if ( unhiddenName.contains("<codename>") ) { //If found tag
            codeName = TagTool.getTag("codename", unhiddenName);
        }
        return codeName;
    }
}
