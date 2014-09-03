package bensku.plugin.ItemAPI.api;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.FireworkEffectMeta;
import org.bukkit.inventory.meta.ItemMeta;

import bensku.plugin.ItemAPI.exception.ItemNotFoundException;
import bensku.plugin.ItemAPI.exception.NullItemException;
import bensku.plugin.ItemAPI.itemData.ItemData;
import bensku.plugin.ItemAPI.main.ItemRegistry;
import bensku.plugin.ItemAPI.main.StackTool;
import bensku.plugin.ItemAPI.meta.firework.ExplosionType;
import bensku.plugin.ItemAPI.nbt.ItemCompound;
import bensku.plugin.ItemAPI.util.Durability;

/**
 * Better api for modifying ItemStacks from CustomItems
 * @author bensku
 * @since 2.00
 * @since 2.03 - rewrote, API recreated
 *
 */
public class CustomStack {
    private ItemStack handle;
    private ItemCompound nbt;
    private ItemData data;
    
    public CustomStack(ItemStack stack) {
        this.handle = stack;
        this.nbt = new ItemCompound(stack);
        this.data = new ItemData(stack);
    }
    
    /**
     * 
     * @return is this stack actually custom
     * @throws NullItemException 
     */
    public boolean isCustom() {
        return (data.getData(ItemAPIData.CODENAME) == null) ? false : true;
    }
    
    /**
     * Adds custom data to itemStack
     * @param key
     * @param value
     * @throws NullItemException 
     */
    public void setCustomData(Object key, String value) {
        this.data.setData(key, value);
    }
    
    /**
     * Gets custom data
     * @param key
     * @return custom data for key
     * @throws NullItemException 
     */
    public Object getCustomData(Object key) {
        return this.data.getData(key);
    }
    
    /**
     * 
     * @return codeName of item, null if item isn't custom
     * @throws NullItemException
     */
    public String getCodeName() {
        return this.getCustomData(ItemAPIData.CODENAME).toString();
    }
    
    /**
     * 
     * @return durability handler of this ItemStack
     * @see Durability
     * @since 2.03 - now returns linked Durability object
     */
    public Durability getDurabilityUtil() {
        return new Durability(this.handle);
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
            //Something weird happened
        }
        return null;
    }
    
    public ItemMeta getItemMeta() {
        return handle.getItemMeta();
    }
    
    public boolean setItemMeta(ItemMeta meta) {
        this.nbt.set("display.Name", meta.getDisplayName());
        this.nbt.set("display.Lore", meta.getLore());
        
        if ( meta instanceof BookMeta ) {
            BookMeta casted = (BookMeta) meta;
            this.nbt.set("author", casted.getAuthor());
            this.nbt.set("title", casted.getTitle());
            this.nbt.set("pages", casted.getPages());
        } else if ( meta instanceof EnchantmentStorageMeta ) {
            EnchantmentStorageMeta casted = (EnchantmentStorageMeta) meta;
            this.nbt.set("StoredEnchantments", casted.getStoredEnchants());
        } else if ( meta instanceof FireworkEffectMeta ) {
            FireworkEffectMeta casted = (FireworkEffectMeta) meta;
            FireworkEffect effect = casted.getEffect();
            
            this.nbt.set("Explosion.Flicker", effect.hasFlicker());
            this.nbt.set("Explosion.Trail", effect.hasTrail());
            this.nbt.set("Explosion.Type", ExplosionType.
                    fromBukkitType(effect.getType()).getNbtValue());
            List<Integer> colors = new ArrayList<Integer>();
            for (int i = 0; i < effect.getColors().size(); i++) {
                colors.add(effect.getColors().get(i).asRGB());
            }
            this.nbt.set("Explosion.Colors", colors);
            List<Integer> fadeColors = new ArrayList<Integer>();
            for (int i = 0; i < effect.getFadeColors().size(); i++) {
                colors.add(effect.getFadeColors().get(i).asRGB());
            }
            this.nbt.set("Explosion.FadeColors", fadeColors);
        }
        
        return true;
    }
}
