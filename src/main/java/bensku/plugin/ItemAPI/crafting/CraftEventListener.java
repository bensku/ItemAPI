package bensku.plugin.ItemAPI.crafting;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.CraftingInventory;

/**
 * Custom recipe listener
 * @author bensku
 * @since 2.02
 * @since 2.03 - rewrote custom crafting
 *
 */
public class CraftEventListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryClick(InventoryClickEvent event) {
        if ( !(event.getInventory() instanceof CraftingInventory) ) {
            //Inventory where the click happened isn't crafting inventory
            return;
        }
        CraftingInventory inv = (CraftingInventory) event.getInventory();
        
        HumanEntity clicker = event.getWhoClicked();
        if ( !(clicker instanceof Player) ) {
            //Only player can move items currently, this is only for future proof code
        }
        
        InventoryAction action = event.getAction();
        
        switch (action) { //Test is the action related to item placing or removing
            case PLACE_ALL: break;
            case PLACE_SOME: break;
            case PLACE_ONE: break;
            case PICKUP_ALL: break;
            case PICKUP_SOME: break;
            case PICKUP_ONE: break;
            case SWAP_WITH_CURSOR: break;
            default: return; //We don't listen any other actions
        }
        
        CustomRecipe recipe = RecipeRegistry.getRecipe(inv);
        if ( recipe == null ) {
            return;
        } else if ( !recipe.canCraft(clicker) ) {
            return;
        }
        
        inv.setResult(recipe.getResult().getItem());
    }
}

