package bensku.plugin.ItemAPI.crafting;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Ingredient object for controlling what kinds of similarities item must have
 * in crafting recipe.
 * @author bensku
 * @since 2.03
 *
 */
public class Ingredient {
    private ItemStack item;
    private boolean typeMatch = true;
    private boolean enchantmentMatch = false;
    private boolean customMatch = true;
    
    private boolean durabilityMatch = true;
    private boolean useDurabilityLimiter = false;
    private double minDurability = 0;
    private double maxDurability = 0;
    private boolean useDPercentages = false;
    
    public Ingredient(ItemStack item) {
        this.item = item;
    }
    
    public Ingredient(Material material) {
        this.item = new ItemStack(material);
    }
    
    /**
     * Gets is item material required to be same in crafting
     * @return true if material must be same, otherwise false
     */
    public boolean hasTypeMatch() {
        return typeMatch;
    }
    
    /**
     * Sets is using same material in crafting required.
     * @param typeMatch
     */
    public void setTypeMatch(boolean typeMatch) {
        this.typeMatch = typeMatch;
    }
    
    /**
     * Gets is the same enchantments required for crafting
     * @return true if same enchantments is required for crafting, false otherwise
     */
    public boolean hasEnchantmentMatch() {
        return enchantmentMatch;
    }
    
    /**
     * Sets is same enchantments required in crafting
     * @param enchantmentMatch
     */
    public void setEnchantmentMatch(boolean enchantmentMatch) {
        this.enchantmentMatch = enchantmentMatch;
    }
    
    /**
     * Gets is the same custom name required to craft items, if they're custom items
     * @return true if custom names must match, false otherwise
     */
    public boolean hasCustomMatch() {
        return customMatch;
    }
    
    /**
     * Sets is the same custom names required in crafting.
     * <p>
     * <b>Warining:</b>Setting this false is risky, because players may then use vanilla
     * items in place of custom items.
     * @param customMatch
     */
    public void setCustomMatch(boolean customMatch) {
        this.customMatch = customMatch;
    }
    
    /**
     * Gets is same durability required to craft items, like vanilla doesn't give you
     * to use broken bows to create dispensers.
     * @return true if durability must match, false otherwise
     */
    public boolean hasDurabilityMatch() {
        return durabilityMatch;
    }
    
    /**
     * Sets is the same durability required to craft items.
     * @param durabilityMatch
     */
    public void setDurabilityMatch(boolean durabilityMatch) {
        this.durabilityMatch = durabilityMatch;
    }
    
    /**
     * Gets is the durability limiter enabled to allow using items between given
     * durabilities. Limiter has no effect if exact durability match is required.
     * @return true if limiter is enabled, false otherwise
     */
    public boolean hasDurabilityLimiter() {
        return useDurabilityLimiter;
    }
    
    /**
     * Sets is the durability limiter enabled to allow using items between given
     * durabilities. Limiter has no effect if exact durability match is required.
     * @param useDurabilityLimiter
     */
    public void setUseDurabilityLimiter(boolean useDurabilityLimiter) {
        this.useDurabilityLimiter = useDurabilityLimiter;
    }
    
    /**
     * Gets required minimum durability required for crafting. 
     * If its 0, it isn't limited
     * @return required minimum durability value
     */
    public double getMinDurability() {
        return minDurability;
    }
    
    /**
     * Sets minimun durability required for crafting. Set to 0 to not limit it.
     * @param minDurability
     */
    public void setMinDurability(double minDurability) {
        this.minDurability = minDurability;
    }
    
    /**
     * Gets maximum allowed durability for crafting. If its 0, it isn't limited.
     * @return maximum allowed durability
     */
    public double getMaxDurability() {
        return maxDurability;
    }
    
    /**
     * Sets maximum allowed durability to craft items. Set to 0 to not limit it.
     * @param maxDurability
     */
    public void setMaxDurability(double maxDurability) {
        this.maxDurability = maxDurability;
    }
    
    /**
     * Gets is durability values stored in percentages instead of
     * normal the durability values. If values are between 1.01 and 100, 1 means 1 %.
     * If they're between 0-1, 1 means 100 %. But if they're something else, error will
     * thrown.
     * @return
     */
    public boolean isPercDurability() {
        return useDPercentages;
    }
    
    /**
     * Sets is durability values stored in percentages instead of the normal
     * durability values. If values are between 1.01 and 100, 1 means 1 %.
     * If they're between 0-1, 1 means 100 %. But if they're something else, error will
     * thrown.
     * @param useDPercentages
     */
    public void setUsePercDurability(boolean useDPercentages) {
        this.useDPercentages = useDPercentages;
    }
    
    /**
     * Gets item of that ingredient object.
     * @return ItemStack
     */
    public ItemStack getItem() {
        return item;
    }
    
    /**
     * Sets item of that ingredient object.
     * @param item
     */
    public void setItem(ItemStack item) {
        this.item = item;
    }
}
