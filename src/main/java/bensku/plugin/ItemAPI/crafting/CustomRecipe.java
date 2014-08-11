package bensku.plugin.ItemAPI.crafting;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.ItemStack;

import bensku.plugin.ItemAPI.util.restrict.WorldRestriction;

/**
 * Custom crafting recipe
 * @author bensku
 * @since 2.02
 * @since 2.03 - rewrote custom crafting
 *
 */
public class CustomRecipe {
    private List<Ingredient> recipe = new ArrayList<Ingredient>();
    private WorldRestriction worldRestrict;
    private String permissionReq;
    
    public CustomRecipe() {
        for (int i = 0; i <= 9; i++) {
            recipe.add(new Ingredient(Material.AIR));
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
    public void setIngredient(int i, Ingredient reagent) {
        recipe.set(i, reagent);
    }
    
    /**
     * Returns ingredient for specific slot
     * @param i
     * @return
     */
    public Ingredient getIngredient(int i) {
        return recipe.get(i);
    }
    
    public List<Ingredient> getIngredients() {
        return this.recipe;
    }
    
    /**
     * Sets result of recipe
     * @param result
     */
    public void setResult(Ingredient result) {
        this.setIngredient(0, result);
    }
    
    /**
     * 
     * @return ItemStack - result of recipe
     */
    public Ingredient getResult() {
        return this.getIngredient(0);
    }
    
    /**
     * Sets world restriction for this recipe
     * @param restrict
     * @since 2.03
     */
    public void setWorldRestrict(WorldRestriction restrict) {
        this.worldRestrict = restrict;
    }
    
    /**
     * 
     * @return current WorldRestriction
     * @since 2.03
     */
    public WorldRestriction getWorldRestrict() {
        return this.worldRestrict;
    }
    
    /**
     * Sets permission requirement for this recipe. Set it to null to disable.
     * @param perm
     * @since 2.03
     */
    public void setPermissionReq(String perm) {
        this.permissionReq = perm;
    }
    
    /**
     * 
     * @return permission requirement for this recipe
     * @since 2.03
     */
    public String getPermissionReq() {
        return this.permissionReq;
    }
    
    /**
     * 
     * @return true if this recipe has permission requirement
     * @since 2.03
     */
    public boolean hasPermissionReq() {
        if ( this.getPermissionReq() != null ) {
            return true;
        }
        
        return false;
    }
    
    /**
     * Checks can given entity craft this recipe immediately.
     * @param entity
     * @return is recipe craftable
     * @since 2.03
     */
    public boolean canCraft(HumanEntity entity) {
        boolean reqOk = true;
        if ( !this.getWorldRestrict().isAllowed(entity.getWorld()) ) {
            reqOk = false;
        } else if ( this.hasPermissionReq() ) {
            if ( !entity.hasPermission(this.getPermissionReq()) ) {
                reqOk = false;
            }
        }
        
        if ( !reqOk ) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "CustomRecipe={" + this.getIngredients() + "}";
    }
}
