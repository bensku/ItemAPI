package bensku.plugin.ItemAPI.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import bensku.plugin.ItemAPI.exception.NullItemException;

/**
 * Class to handle durability of items and change it to damage percentages. The values
 * will automatically changed to linked ItemStack, to prevent this, if there is one.
 * <p>
 * <b>Warning:</b> If you didn't yourself create the object using no ItemStack, 
 * you should do isLinked() to check that or use clone() or unLink()
 * @author bensku
 * @since 2.00
 * @since 2.03 - item linking
 *
 */
public class Durability {
    private int durability = 0;
    private Material material;
    /**
     * @since 2.03
     */
    private ItemStack linkedItem;
    
    /**
     * Default durability constructor
     * @param durability
     * @param material
     */
    public Durability(int durability, Material material) {
        this.setValue(durability);
        this.setMaterial(material);
    }
    
    /**
     * Constructor which sets durability to maximum
     * @param material
     */
    public Durability(Material material) {
        this.setValue(material.getMaxDurability());
        this.setMaterial(material);
    }
    
    /**
     * Creates object based by ItemStack, and links to it.
     * @param item
     * @since 2.03
     */
    public Durability(ItemStack item) {
        if ( item.getType().equals(Material.AIR) || item == null ) {
            throw new NullItemException("Can't create Durability object from null or "
                    + "AIR item.");
        }
        
        this.link(item); //Link to ItemStack
        this.fetchFromLink(); //Fetch item's data
    }
    
    /**
     * Sets durability of item
     * @param durability
     */
    public void setValue(int durability) {
        this.durability = durability;
        
        this.updateLink();
    }
    
    /**
     * 
     * @return durability of item
     */
    public int getValue() {
        this.fetchFromLink();
        
        return this.durability;
    }
    
    /**
     * Sets item material
     * @param material
     */
    public void setMaterial(Material material) {
        this.material = material;
        
        this.updateLink();
    }
    
    /**
     * 
     * @return material of item
     */
    public Material getMaterial() {
        this.fetchFromLink();
        
        return this.material;
    }
    
    /**
     * 
     * @return max durability of item
     */
    public int getMaxDurability() {
        this.fetchFromLink();
        
        return this.getMaterial().getMaxDurability();
    }
    
    /**
     * 
     * @param percentage
     * @since 2.03
     */
    public void setPercentage(float percentage) {
        
        this.updateLink();
    }
    
    /**
     * Calculates percentage about how much there is durability left.
     * @return percentage of item durability left
     */
    public float getPercentage() {
        this.fetchFromLink();
        
        float percentage = (this.getValue() * 100.0F) / this.getMaxDurability();
        return percentage;
    }
    
    /**
     * @since 2.03
     */
    private void updateLink() {
        if ( linkedItem == null ) { return; }
        
        linkedItem.setDurability((short) this.getValue());
        linkedItem.setType(this.getMaterial());
    }
    
    /**
     * @since 2.03
     */
    private void fetchFromLink() {
        if ( this.isLinked() == false ) { return; }
        
        this.setValue(linkedItem.getDurability());
        this.setMaterial(linkedItem.getType());
    }
    
    /**
     * Check is item linked to ItemStack.
     * @return is item linked to itemStack
     */
    public boolean isLinked() {
        if ( this.linkedItem == null ) return false;
        return true;
    }
    
    /**
     * Unlinks this from ItemStack
     * @since 2.03
     */
    public void unLink() {
        this.linkedItem = null;
    }
    
    /**
     * Links this object to certain ItemStack
     * @param item
     * @since 2.03
     */
    public void link(ItemStack item) {
        this.linkedItem = item;
    }
    
    /**
     * 
     * @return the linked ItemStack
     * @since 2.03
     */
    public ItemStack getLink() {
        return this.linkedItem;
    }
    
    /**
     * @since 2.03
     */
    @Override
    public boolean equals(Object obj) {
        if ( super.equals(obj) ) {
            return true;
        }
        
        if ( !(obj instanceof Durability) ) {
            return false;
        }
        
        Durability other = (Durability) obj;
        
        if ( this.getValue() ==  other.getValue()) {
            return true;
        }
        
        return false;
    }
}

