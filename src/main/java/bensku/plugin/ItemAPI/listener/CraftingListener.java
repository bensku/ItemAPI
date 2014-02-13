package bensku.plugin.ItemAPI.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

import bensku.plugin.ItemAPI.api.CustomStack;
import bensku.plugin.ItemAPI.exception.NullItemException;
import bensku.plugin.ItemAPI.main.CustomItemTool;
import bensku.plugin.ItemAPI.main.GetItemInfo;

@SuppressWarnings("deprecation")
public class CraftingListener implements Listener {
    @Deprecated
    @EventHandler
    public void legacyCustomItemCraft(PrepareItemCraftEvent event) {
        ItemStack[] items = event.getInventory().getContents();
        
        //Bukkit.getLogger().info("Debug: items is " + items.toString());
        
        for (int i = 1; i < items.length; i++) {
            //i starts from 1 because 0 is crafting result (and maybe custom item)
            ItemStack itemStack = items[i];
            //Bukkit.getLogger().info("Debug: items is" +  items.toString());
            try {
                //Bukkit.getLogger().info("Debug: Try started");
                if ( CustomItemTool.isCustomStack(itemStack) ) {
                    //Bukkit.getLogger().info("Debug: Stack is custom item");
                    if ( GetItemInfo.getAllowCrafting(
                            CustomItemTool.getCodeName(itemStack))) {
                        /*Bukkit.getLogger().info("Debug: Crafting is allowed");
                        Bukkit.getLogger().info("Debug: codeName is " +
                                CustomItemTool.getCodeName(itemStack)); */
                        //If crafting is allowed:
                        continue;
                    } else {
                        //Bukkit.getLogger().info("Debug: Crafting is disallowed");
                        event.getInventory().setResult(null);
                        return;
                    }
                }
            } catch (NullItemException e) {
                //Bukkit.getLogger().info("Debug: Catched NullItemException");
                //If itemStack is null, there isn't any item, so...
                continue;
            }
        }
    }
    @EventHandler
    public void customItemCraftBlocker(PrepareItemCraftEvent event) {
        ItemStack[] items = event.getInventory().getContents();
        
        //Bukkit.getLogger().info("Debug: items is " + items.toString());
        
        for (int i = 1; i < items.length; i++) {
            //i starts from 1 because 0 is crafting result (and maybe custom item)
            ItemStack stack = items[i];
            CustomStack item = null;
            
            //Bukkit.getLogger().info("Debug: i is " + i);
            //Bukkit.getLogger().info("Debug: itemStack is " + stack.toString());
            
            try {
                item = new CustomStack(stack);
            } catch (NullItemException e) {
                //If itemStack is null (or air), there isn't any item, so...
                continue;
                
                //It can also avoid IllegalArgumentExceptions
            }
            try {
                if ( item.isCustom() ) {
                    //Bukkit.getLogger().info("Debug: Stack is custom item");
                    if ( item.getOriginalCustomItem().getAllowCrafting() ) {
                        /*Bukkit.getLogger().info("Debug: Crafting is allowed");
                        Bukkit.getLogger().info("Debug: codeName is " +
                                CustomItemTool.getCodeName(itemStack)); */
                        //If crafting is allowed:
                        continue;
                    } else {
                        //Bukkit.getLogger().info("Debug: Crafting is disallowed");
                        event.getInventory().setResult(null);
                        return;
                    }
                }
            } catch (NullItemException e) {
                //Due to restrictions of Java
                continue;
            }
        }
    }
}
