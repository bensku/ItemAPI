package bensku.plugin.ItemAPI.crafting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import bensku.plugin.ItemAPI.api.CustomStack;
import bensku.plugin.ItemAPI.util.Durability;

/**
 * Registry for custom crafting recipes with extended features.
 * @author bensku
 * @since 2.02
 * @since 2.03 - rewrote custom crafting
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
    
    /**
     * Returns custom recipe which given CraftingInventory has ready. If no recipe is
     * found, returns null
     * @param inv
     * @return recipe corresponding to given inventory or null
     * @since 2.03
     */
    public static CustomRecipe getRecipe(CraftingInventory inv) {
        ItemStack[] items = inv.getContents();
        
        //Init variables
        List<CustomRecipe> matchRecipes = new ArrayList<CustomRecipe>(); 
        //Matching recipes
        
        for (int i = 0; i < recipes.size(); i++) {
            CustomRecipe recipe = recipes.get(i);
            boolean isEligible = true; //Stores is recipe eligible
            for (int i2 = 1; i2 <= 9; i++) { //0 is crafting result
                if ( !isEligible ) { break; }
                ItemStack item = items[i2];
                Ingredient ingredient = recipe.getIngredient(i2);
                ItemStack recipeItem = ingredient.getItem();
                CustomStack stack = new CustomStack(item);
                CustomStack recipeStack = new CustomStack(recipeItem);
                Durability dblt = new Durability(item);
                Durability recipeDblt = new Durability(recipeItem);
                
                boolean match[] = {false, false, false, false, false}; 
                //Will items match partially
                //0 = Material, 1 = enchantments, 2 = custom items,
                //3 = durability (exact), 4 = durability (between limits)
                
                //Now we check if the two items has any similarities
                if ( recipeItem == null && item == null ) {
                    continue; //To next item
                } if (recipeItem.getType().equals(Material.AIR) && 
                        item.getType().equals(Material.AIR)) {
                    continue; //To next item
                } if ( recipeItem.getType().equals(item.getType()) ) {
                    match[0] = true;
                } if ( recipeItem.getEnchantments().equals(item.getEnchantments()) ) {
                    match[1] = true;
                } if ( recipeStack.isCustom() ) {
                    if ( stack.isCustom() ) {
                        //Both stacks are custom
                        if ( recipeStack.getCodeName().equals(stack.getCodeName()) ) {
                            //They're same custom items
                            match[2] = true;
                        }
                    }
                } if ( recipeDblt.equals(dblt) ) {
                    match[3] = true;
                } if ( recipeDblt.getValue() <= ingredient.getMaxDurability() && 
                        recipeDblt.getValue() >= ingredient.getMinDurability()) {
                    match[4] = true;
                }
                
                //Test match[] against ingredient limiters
                if ( ingredient.hasTypeMatch() && !match[0] ) {
                    isEligible = false;
                    break;
                } if ( ingredient.hasEnchantmentMatch() && !match[1] ) {
                    isEligible = false;
                    break;
                } if ( ingredient.hasCustomMatch() && !match[2] ) {
                    isEligible = false;
                    break;
                } if ( ingredient.hasDurabilityMatch() && !match[3] ) {
                    isEligible = false;
                    break;
                } if ( ingredient.hasDurabilityLimiter() && !match[4] ) {
                    isEligible = false;
                    break;
                }
            }
            
            if ( isEligible ) {
                matchRecipes.add(recipe);
            }
        }
        
        return matchRecipes.get(0);
    }
}
