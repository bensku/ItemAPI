package bensku.plugin.ItemAPI.crafting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;

import bensku.plugin.ItemAPI.api.CustomStack;

/**
 * Registry for custom crafting recipes with extended features.
 * @author bensku
 * @since 2.02
 * @since 2.03 - rewrote custom crafting
 *
 */
public class RecipeRegistry {
    private static List<CustomRecipe> recipes = new ArrayList<CustomRecipe>();
    private static List<Recipe> bukkitRecipes = new ArrayList<Recipe>();
    
    /**
     * Registers new custom recipe
     * @param recipe
     * @see CustomRecipe
     */
    public static void registerRecipe(CustomRecipe recipe) {
        //Register Bukkit recipe (for PrepareCraftItemEvent throwing)
        ShapedRecipe bukkitRecipe = new ShapedRecipe(recipe.getResult().getItem());
        bukkitRecipe.shape("123", "456", "789");
        for (int i = 1; i <= 9; i++) {
            if ( recipe.getIngredient(i).getItem().getType() != Material.AIR ) {
                bukkitRecipe.setIngredient((char) ('0' + i), recipe.
                        getIngredient(i).getItem().getType());
            }
        }
        Bukkit.getServer().addRecipe(bukkitRecipe);

        recipes.add(recipe);
        bukkitRecipes.add(bukkitRecipe);
    }
    
    /**
     * 
     * @return list of all custom recipes
     */
    public static List<CustomRecipe> getRecipes() {
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
        //Bukkit.getLogger().info("Debug: Got getRecipe() call");
        List<ItemStack> items = Arrays.asList(inv.getMatrix()); //getContents() failed...
        
        //Init variables
        List<CustomRecipe> matchRecipes = new ArrayList<CustomRecipe>(); 
        //Matching recipes
        
        for (int i = 0; i < recipes.size(); i++) {
            //Bukkit.getLogger().info("Debug: i for loop started, i is " + i);
            CustomRecipe recipe = recipes.get(i);
            boolean isEligible = true; //Stores is recipe eligible
            for (int i2 = 1; i2 <= 9; i2++) { //0 is crafting result
                //Bukkit.getLogger().info("Debug: i2 for loop started, i2 is " + i2);
                if ( !isEligible ) { break; }
                //Bukkit.getLogger().info("Debug: Item is eligible; continuing...");
                ItemStack item = items.get(i2 - 1);
                Ingredient ingredient = recipe.getIngredient(i2);
                ItemStack recipeItem = ingredient.getItem();
                
                boolean match[] = {false, false, false, false, false}; 
                //Will items match partially
                //0 = Material, 1 = enchantments, 2 = custom items,
                //3 = durability (exact), 4 = durability (between limits)
                
                //Checking is item or recipeItem null or AIR
                boolean isItemNull = false;
                boolean isRecipeItemNull = false;
                if ( item == null || item.getType().equals(Material.AIR) ) {
                    isItemNull = true;
                } if ( recipeItem == null || recipeItem.getType().equals(Material.AIR) ) {
                    isRecipeItemNull = true;
                }
                if ( isItemNull == isRecipeItemNull ) {
                    if ( isItemNull ) {
                        //Bukkit.getLogger().info("Debug: Null and AIR tests passed");
                        continue;
                    }
                } else {
                    //Bukkit.getLogger().info("Debug: Null test failed");
                    //Bukkit.getLogger().info("Debug: recipeItem is " + recipeItem + 
                    //        " and item is " + item);
                    isEligible = false;
                    break;
                }
                
                //Bukkit.getLogger().info("Debug: item is " + item);
                
                CustomStack stack = new CustomStack(item);
                CustomStack recipeStack = new CustomStack(recipeItem);
                //Durability dblt = new Durability(item);
                //Durability recipeDblt = new Durability(recipeItem);
                
                if ( recipeItem.getType().equals(item.getType()) ) {
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
                } /*if ( recipeDblt.equals(dblt) ) {
                    match[3] = true;
                } if ( recipeDblt.getValue() <= ingredient.getMaxDurability() && 
                        recipeDblt.getValue() >= ingredient.getMinDurability()) {
                    match[4] = true;
                }*/
                match[3] = true;
                //Bukkit.getLogger().info("Debug: match[] tests done " + match);
                
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
        
        if ( !matchRecipes.isEmpty() ) {
            return matchRecipes.get(0);
        }
        return null;
    }
    
    public static CustomRecipe getRecipe(int i) {
        return getRecipes().get(i);
    }
    
    public static Recipe getBukkitRecipe(int i) {
        return bukkitRecipes.get(i);
    }
    
    public static int getRecipeId(CustomRecipe recipe) {
        int recipeId = 0;
        for (int i = 0; i < recipes.size(); i++) {
            if ( recipes.get(i).equals(recipe) ) {
                return i;
            }
            
        }
        return recipeId;
    }
    
    public static boolean hasBukkitRecipe(CraftingInventory inv) {
        if ( bukkitRecipes.contains(inv.getRecipe()) ) {
            return true;
        }
        
        return false;
    }
}
