package bensku.plugin.ItemAPI.util;

import org.bukkit.Material;

/**
 * Class to handle durability of items and change it to damage percentages
 * @author bensku
 * @since 2.00
 *
 */
public class Durability {
    int durability = 0;
    Material material = null;
    
    /**
     * Default durability constructor
     * @param durability
     * @param material
     */
    public Durability(int durability, Material material) {
        this.setDurability(durability);
        this.setMaterial(material);
    }
    
    /**
     * Constructor which sets durability to maximum
     * @param material
     */
    public Durability(Material material) {
        this.setDurability(material.getMaxDurability());
        this.setMaterial(material);
    }
    
    /**
     * Sets durability of item
     * @param durability
     */
    public void setDurability(int durability) {
        this.durability = durability;
    }
    
    /**
     * 
     * @return durability of item
     */
    public int getDurability() {
        return this.durability;
    }
    
    /**
     * Sets item material
     * @param material
     */
    public void setMaterial(Material material) {
        this.material = material;
    }
    
    /**
     * 
     * @return material of item
     */
    public Material getMaterial() {
        return this.material;
    }
    
    /**
     * 
     * @return max durability of item
     */
    public int getMaxDurability() {
        return this.getMaterial().getMaxDurability();
    }
    
    /**
     * Calculates percentage about how much there is durability left.
     * @return percentage of item durability left
     */
    public float getPercentage() {
        float percentage = (this.getDurability() * 100.0F) / this.getMaxDurability();
        return percentage;
    }
}

