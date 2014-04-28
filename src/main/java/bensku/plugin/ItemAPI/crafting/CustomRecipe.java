package bensku.plugin.ItemAPI.crafting;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Custom crafting recipe
 * @author bensku
 * @since 2.03
 *
 */
public class CustomRecipe {
    private ArrayList<ItemStack> recipe = new ArrayList<ItemStack>();
    
    public CustomRecipe() {
        for (int i = 0; i <= 9; i++) {
            recipe.add(new ItemStack(Material.AIR));
        }
    }
    
    /**
     * Sets reagent of specific crafting slot. the i is determined following way:
     * 1 2 3
     * 4 5 6 => 0
     * 7 8 9
     * <p>
     * Please don't use this to set result of recipe, use instead setResult
     * @param i
     * @param reagent
     * @see CustomRecipe.setResult
     */
    public void setReagent(int i, ItemStack reagent) {
        recipe.set(i, reagent);
    }
    
    /**
     * Get's reagent of specific crafting slot
     * @param i
     * @return 
     */
    public ItemStack getReagent(int i) {
        return recipe.get(i);
    }
    
    public ArrayList<ItemStack> getReagents() {
        return this.recipe;
    }
    
    /**
     * Sets result of recipe
     * @param result
     */
    public void setResult(ItemStack result) {
        this.setReagent(0, result);
    }
    
    /**
     * 
     * @return ItemStack - result of recipe
     */
    public ItemStack getResult() {
        return this.getReagent(0);
    }
}
