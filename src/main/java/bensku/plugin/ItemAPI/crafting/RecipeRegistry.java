package bensku.plugin.ItemAPI.crafting;

import java.util.ArrayList;

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
    }
    
    /**
     * 
     * @return list of all custom recipes
     */
    public static ArrayList<CustomRecipe> getRecipes() {
        return recipes;
    }
}
