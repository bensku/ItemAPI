package bensku.plugin.ItemAPI.crafting;

import java.util.ArrayList;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Custom recipe listener
 * @author bensku
 * @since 2.02
 *
 */
public class CraftEventListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST) //Highest due to CraftingListener
    public void onPrepareCraft(PrepareItemCraftEvent event) {
        ItemStack[] items = event.getInventory().getContents();
        ArrayList<CustomRecipe> recipes = RecipeRegistry.getRecipes();
        
        while (recipes.iterator().hasNext()) { //Iterate all recipes
            CustomRecipe recipe = recipes.iterator().next();
            
            for (int i = 1; i <= 9; i++) {
                if ( !recipe.getReagent(i).equals(items[i]) ) {
                    //If at least one of items isn't same, break this...
                    break;
                }
                
                //All reagents are same than in recipe, set the result
                event.getInventory().setResult(recipe.getResult());
            }
            
            continue; //...and continue here
        }
    }
}
