package bensku.plugin.ItemAPI.listener;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;

import bensku.plugin.ItemAPI.api.CustomStack;
import bensku.plugin.ItemAPI.crafting.CustomRecipe;
import bensku.plugin.ItemAPI.crafting.RecipeRegistry;
import bensku.plugin.ItemAPI.exception.NullItemException;

/**
 * Custom recipe listener
 * @author bensku
 * @since 2.02
 * @since 2.03 - rewrote custom crafting
 *
 */
public class CraftEventListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPrepareCraft(PrepareItemCraftEvent event) {
        //Bukkit.getLogger().info("Debug: Got PrepareItemCraftEvent");
        HumanEntity entity = event.getViewers().get(0);
        if ( !(entity instanceof Player) ) {
            //Only player can move items currently, this is only for future proof code
            return;
        }
        
        CraftingInventory inv = event.getInventory();

        CustomRecipe recipe = RecipeRegistry.getRecipe(inv);
        if ( recipe == null ) {
            if ( RecipeRegistry.hasBukkitRecipe(inv) ) {
                event.getInventory().setResult(null);
            } else {
                //Code from old CraftingListener... temporary

                ItemStack[] items = event.getInventory().getContents();
                //Bukkit.getLogger().info("Debug: items is " + items.toString());
                for (int i = 1; i < items.length; i++) {
                    //i starts from 1 because 0 is crafting result (and maybe custom item)
                    ItemStack stack = items[i];
                    CustomStack item = null;
                    try {
                        item = new CustomStack(stack);
                    } catch (NullItemException e) {
                        //If itemStack is null (or air), there isn't any item, so...
                        continue;
                        //It can also avoid IllegalArgumentExceptions
                    }
                    if ( item.isCustom() ) {
                        //Bukkit.getLogger().info("Debug: Stack is custom item");
                        if ( item.getOriginalCustomItem().getAllowCrafting() ) {
                            //If crafting is allowed:
                            continue;
                        } else {
                            //Bukkit.getLogger().info("Debug: Crafting is disallowed");
                            event.getInventory().setResult(null);
                            return;
                        }
                    }
                }
            }
        } else if ( !recipe.canCraft(entity) ) {
            event.getInventory().setResult(null);
        }

        //entity.openInventory(inv);

        //((Player) entity).updateInventory();
    }
}


