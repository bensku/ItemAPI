package bensku.plugin.ItemAPI.crafting;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Custom crafting recipe
 * @author bensku
 * @since 2.02
 *
 */
public class CustomRecipe {
    private ArrayList<ItemStack> recipe = new ArrayList<ItemStack>();
    
    public CustomRecipe() {
        for (int i = 0; i <= 9; i++) {
            recipe.add(new ItemStack(Material.AIR, 0));
        }
    }
    
    /**
     * Sets ingredient of specific crafting slot. The i is determined following way:
     * <pre>
     * 1 2 3
     * 4 5 6 => 0
     * 7 8 9
     * </pre>
     * Please don't use this to set result of recipe, use instead setResult
     * @param i
     * @param reagent
     * @see setResult
     */
    public void setIngredient(int i, ItemStack reagent) {
        recipe.set(i, reagent);
    }
    
    /**
     * Returns ingredient for specific slot
     * @param i
     * @return
     */
    public ItemStack getIngredient(int i) {
        return recipe.get(i);
    }
    
    public ArrayList<ItemStack> getIngredients() {
        return this.recipe;
    }
    
    /**
     * Sets result of recipe
     * @param result
     */
    public void setResult(ItemStack result) {
        this.setIngredient(0, result);
    }
    
    /**
     * 
     * @return ItemStack - result of recipe
     */
    public ItemStack getResult() {
        return this.getIngredient(0);
    }
    
    @Override
    public String toString() {
        return "CustomRecipe={" + this.getIngredients() + "}";
    }
}
