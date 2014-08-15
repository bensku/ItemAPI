package bensku.plugin.ItemAPI.main;

import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.comphenix.attribute.AttributeStorage;
import com.comphenix.attribute.Attributes;
import com.comphenix.attribute.Attributes.Attribute;
import com.comphenix.attribute.Attributes.AttributeType;

import bensku.plugin.ItemAPI.api.CustomItem;
import bensku.plugin.ItemAPI.api.CustomStack;
import bensku.plugin.ItemAPI.api.ItemAPIData;
import bensku.plugin.ItemAPI.exception.NullItemException;

/**
 * Contains some tools that is related ItemStacks
 * <p>
 * Don't use functions from this class directly, CustomItemStack is better way
 * @author bensku
 * @since 2.00
 * @see CustomStack
 *
 */
public class StackTool {
    /**
     * Function that actually makes ItemStack from CustomItem. Don't use that manually,
     * better way is use toStack method in CustomItem, which calls that
     * @param item
     * @param amount
     * @return ItemStack from CustomItem
     */
    public static ItemStack customItemToStack(CustomItem item, int amount) {
        ItemStack itemStack = new ItemStack(item.getMaterial(), amount);
        ItemMeta itemMeta = itemStack.getItemMeta();
        
        //Setting itemMeta and itemStack data
        itemStack.setDurability((short) item.getDurability().getValue());
        itemMeta.setDisplayName(item.getDisplayName());
        itemMeta.setLore(item.lore);
        
        itemStack.setItemMeta(itemMeta); //Apply itemMeta
        
        //All other data to AttributeStorage
        
        AttributeStorage storage = AttributeStorage.newTarget(itemStack, 
                ItemAPIData.CODENAME);
        storage.setData(item.getCodeName());
        itemStack = storage.getTarget();
        
        Attributes attributes = new Attributes(itemStack);
        
        attributes.add(Attribute.newBuilder().name("generic.attackDamage")
                .type(AttributeType.GENERIC_ATTACK_DAMAGE)
                .amount(item.getAttackDamage()).build());
        itemStack = attributes.getStack();
        
        //Transfer custom data...
        for (Entry<UUID, String> entry : item.getCustomDataMap().entrySet()) {
            UUID key = entry.getKey();
            String value = entry.getValue();
            
            storage = AttributeStorage.newTarget(itemStack, key);
            storage.setData(value);
            itemStack = storage.getTarget();
        }
        
        return itemStack; //Finally, return itemStack
    }
    
    /**
     * 
     * @param stack
     * @return is stack custom item
     * @throws NullItemException 
     */
    public static boolean isStackCustom(ItemStack itemStack) 
            throws NullItemException {
        if ( itemStack == null ) { //Null stack test
            throw new NullItemException("Can't check is ItemStack custom");
        }
        
        AttributeStorage storage = AttributeStorage.newTarget(itemStack, 
                ItemAPIData.CODENAME);
        String codeName = storage.getData(null);
        
        if ( codeName != null ) {
            return true;
        }
        return false;
    }
    
    /**
     * Sets custom data to ItemStack
     * @param itemStack
     * @param key
     * @param value
     * @return new ItemStack with custom data
     * @throws NullItemException 
     */
    public static ItemStack setCustomData(ItemStack itemStack, 
            UUID key, String value) throws NullItemException {
        if ( itemStack == null ) { //Null stack test
            throw new NullItemException("Can't set data");
        }
        
        AttributeStorage storage = AttributeStorage.newTarget(itemStack, key);
        storage.setData(value);
        return storage.getTarget();
    }
    
    /**
     * Gets custom data from itemStack
     * @param itemStack
     * @param key
     * @return custom data value, null if it doesn't exist
     * @throws NullItemException 
     */
    public static String getCustomData(ItemStack itemStack, UUID key) 
            throws NullItemException {
        if ( itemStack == null ) { //Null stack test
            throw new NullItemException("Can't get custom data");
        }
        
        AttributeStorage storage = AttributeStorage.newTarget(itemStack, key);
        return storage.getData(null);
    }
    
    /**
     * 
     * @param itemStack
     * @return codeName of item
     * @throws NullItemException
     */
    public static String getCodeName(ItemStack itemStack) throws NullItemException {
        return getCustomData(itemStack, ItemAPIData.CODENAME);
    }
}
