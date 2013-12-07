package bensku.plugin.ItemAPI.listener;

import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import bensku.plugin.ItemAPI.main.CustomItemTool;
import bensku.plugin.ItemAPI.main.GetItemInfo;

public class CraftingListener implements Listener {
    @EventHandler
    public void customItemCraft(CraftItemEvent event) {
        ShapedRecipe recipe = (ShapedRecipe) event.getRecipe();
        Map<Character, ItemStack> items = recipe.getIngredientMap();
        
        for (Entry<Character, ItemStack> entry : items.entrySet()) {
            //Character key = entry.getKey();
            ItemStack itemStack = entry.getValue();
            if ( CustomItemTool.isCustomStack(itemStack) ) {
                if ( GetItemInfo.getAllowCrafting(
                        CustomItemTool.getCodeName(itemStack))) {
                    //If crafting is allowed:
                    return;
                } else {
                    event.setCancelled(true);
                    return;
                }
            }
        }
    }
}
