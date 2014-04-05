package bensku.plugin.ItemAPI.api;

import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import bensku.plugin.ItemAPI.exception.ItemNotFoundException;
import bensku.plugin.ItemAPI.exception.NullItemException;
import bensku.plugin.ItemAPI.main.ItemRegistry;
import bensku.plugin.ItemAPI.main.StackTool;
import bensku.plugin.ItemAPI.util.Durability;

/**
 * Better api for modifying ItemStacks from CustomItems
 * @author bensku
 * @since 2.00
 *
 */
public class CustomStack {
    private ItemStack itemStack;
    
    public CustomStack(ItemStack itemStack) throws NullItemException {
        if ( itemStack != null ) {
            if ( itemStack.getType() != Material.AIR ) {
                this.setItemStack(itemStack);
            } else {
                throw new NullItemException("Cannot create CustomStack "
                        + "from AIR itemStack");
            }
        } else {
            throw new NullItemException("Cannot create CustomStack "
                    + "from null itemStack");
        }
    }
    
    /**
     * Sets itemStack
     * @param itemStack
     */
    private void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }
    
    /**
     * 
     * @return actual itemStack
     */
    public ItemStack getItemStack() {
        return this.itemStack;
    }
    
    /**
     * 
     * @return is this stack actually custom
     * @throws NullItemException 
     */
    public boolean isCustom() {
        try {
            return StackTool.isStackCustom(this.getItemStack());
        } catch (NullItemException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * Adds custom data to itemStack
     * @param key
     * @param value
     * @throws NullItemException 
     */
    public void setCustomData(UUID key, String value) {
        try {
            this.setItemStack(StackTool.setCustomData(this.itemStack, key, value));
        } catch (NullItemException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Gets custom data
     * @param key
     * @return custom data for key
     * @throws NullItemException 
     */
    public String getCustomData(UUID key) {
        try {
            return StackTool.getCustomData(itemStack, key);
        } catch (NullItemException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 
     * @return codeName of item, null if item isn't custom
     * @throws NullItemException
     */
    public String getCodeName() {
        try {
            return StackTool.getCodeName(itemStack);
        } catch (NullItemException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 
     * @return durability handler of this ItemStack
     * @see Durability
     */
    public Durability getDurability() {
        return new Durability(this.getItemStack().getDurability(), 
                this.getItemStack().getType());
    }
    
    /**
     * 
     * @return class of CustomItem used with this, null if this isn't custom item
     * @throws NullItemException
     */
    public Class<?> getItemClass() {
        try {
            return ItemRegistry.getClass(this.getCodeName());
        } catch (ItemNotFoundException e) {
            //Do nothing
        }
        return null;
    }
    
    /**
     * Gets original CustomItem of this CustomStack, without any runtime modifications.
     * @return CustomItem
     */
    public CustomItem getOriginalCustomItem() {
        Class<?> c;
        c = this.getItemClass();
        try {
            CustomItem item = (CustomItem) c.newInstance();
            return item;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            //Something wrong happened
        }
        return null;
    }
}
