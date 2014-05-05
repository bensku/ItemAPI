package bensku.plugin.ItemAPI.crafting;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import bensku.plugin.ItemAPI.api.CustomStack;
import bensku.plugin.ItemAPI.exception.NullItemException;

/**
 * Custom recipe listener
 * @author bensku
 * @since 2.02
 *
 */
public class CraftEventListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST) //Highest due to CraftingListener
    public void onPrepareCraft(PrepareItemCraftEvent event) {
        //Bukkit.getLogger().info("Debug: PrepareCraftItemEvent catched");

        ItemStack[] items = event.getInventory().getContents();
        ArrayList<CustomRecipe> recipes = RecipeRegistry.getRecipes();

        //Bukkit.getLogger().info("Debug: items is " + items.toString());
        //Bukkit.getLogger().info("Debug: recipes is " + recipes.toString());

        for (int i = 0; i < recipes.size(); i++) {
            CustomRecipe recipe = recipes.get(i);
            //Bukkit.getLogger().info("Debug: recipe is " +  recipe.toString());

            boolean same = true;
            for (int i2 = 1; i2 <= 9; i2++) {
                //Bukkit.getLogger().info("Debug: for loop: i2");
                ItemStack item = items[i2];
                ItemStack recipeItem = recipe.getIngredient(i2);
                same = compareItemStack(item, recipeItem);
                
                if ( !same ) {
                    //Stacks aren't same
                    break;
                }
            }

            if ( compareItemStack(event.getInventory().getResult(), 
                    recipe.getResult()) ) {
                //Set result to null, if happens that this is custom recipe
                //TODO: Make better solution, this may suck sometimes
                event.getInventory().setResult(null);
            }

            if ( same ) {
                event.getInventory().setResult(recipe.getResult());
                return;
            }
        }
    }

    public static boolean compareItemStack(ItemStack a, ItemStack b) {
        CustomStack stack = null;
        CustomStack bStack = null;
        
        try {
            stack = new CustomStack(a);
            bStack = new CustomStack(b);
        } catch (NullItemException e) {
            //Ignore exception, AIR stacks get handled later
        }
        
        if ( a == null || a.getType().equals(Material.AIR) ) {
            if ( b == null | b.getType().equals(Material.AIR) ) {
                //Both items are AIR
                return true;
            } else {
                //Only a is AIR
                return false;
            }
        }

        //Do some tests...
        if ( !a.getData().equals(b.getData()) ) { //MaterialData
            return false;
        }

        if ( a.getDurability() != b.getDurability() ) {
            return false;
        }

        if ( !a.getEnchantments().equals(b.getEnchantments()) ) {
            return false;
        }

        if ( bStack.isCustom() ) {
            if ( stack.isCustom() ) {
                //Both stacks are custom
                if ( bStack.getCodeName().equals(stack.getCodeName()) ) {
                    //They're same custom items
                    return true;
                } else {
                    //They aren't same custom items
                    return false;
                }
            } else {
                //stack isn't custom
                return false;
            }
        }
        
        //All vanilla item tests are fine and item isn't custom item, so...
        return true;
    }
}

