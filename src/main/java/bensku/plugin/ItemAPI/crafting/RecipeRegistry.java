package bensku.plugin.ItemAPI.crafting;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ShapedRecipe;

/**
 * Registry for custom crafting recipes with extended features.
 * @author bensku
 * @since 2.02
 *
 */
public class RecipeRegistry {
    private static ArrayList<CustomRecipe> recipes = new ArrayList<CustomRecipe>();
    
    /**
     * Registers new custom recipe
     * @param recipe
     * @see CustomRecipe
     */
    public static void registerRecipe(CustomRecipe recipe) {
        recipes.add(recipe);
        
        //Register Bukkit recipe (for PrepareCraftItemEvent throwing)
        ShapedRecipe bukkitRecipe = new ShapedRecipe(recipe.getResult());
        bukkitRecipe.shape("123", "456", "789");
        for (int i = 1; i <= 9; i++) {
            if ( recipe.getIngredient(i).getType() != Material.AIR ) {
                bukkitRecipe.setIngredient((char) ('0' + i), recipe.
                        getIngredient(i).getType());
            }
        }
        
        Bukkit.getServer().addRecipe(bukkitRecipe);
        //Bukkit.getLogger().info("Debug: bukkitRecipe is " + bukkitRecipe.toString());
        //Bukkit.getLogger().info("Debug: Ingredients are " + bukkitRecipe.getIngredientMap());
    }
    
    /**
     * 
     * @return list of all custom recipes
     */
    public static ArrayList<CustomRecipe> getRecipes() {
        return recipes;
    }
}
