package bensku.plugin.ItemAPI.api;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.comphenix.attribute.Attributes;
import com.comphenix.attribute.Attributes.Attribute;
import com.comphenix.attribute.Attributes.AttributeType;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

import bensku.plugin.ItemAPI.api.event.ActiveEvent;
import bensku.plugin.ItemAPI.api.event.EventType;
import bensku.plugin.ItemAPI.api.event.PassiveEvent;
import bensku.plugin.ItemAPI.exception.EventNotHandledException;
import bensku.plugin.ItemAPI.exception.NullItemException;
import bensku.plugin.ItemAPI.main.StackTool;
import bensku.plugin.ItemAPI.util.ColorTags;
import bensku.plugin.ItemAPI.util.Durability;

/**
 * Extendible custom item class
 * @author bensku
 *
 */
public class CustomItem {
    private ListMultimap<Class<?>,ListMultimap<EventType,Method>> itemEventMap = 
            ArrayListMultimap.create();
    private String displayName;
    private String codeName;
    private Material material = Material.STICK;
    private double attackDamage = 0.0;
    private double defaultAttackDamage = 1.0;
    private short durability = -1;
    private List<String> lore;
    private Map<UUID,String> customDataMap = new HashMap<UUID,String>();
    private int amount = 1;
    private boolean allowCrafting = false;
    
    /**
     * Constructor that asks codeName
     * @param codeName
     */
    public CustomItem(String codeName) {
        this.setCodeName(codeName); //Set the codeName
        this.addCustomData(ItemAPIData.CUSTOMDATA_TEST, "working");
        this.saveDefaultAttackDamage();
        this.addAnnotationEvents();
    }
    
    /**
     * Sets default display name of item. Color tags may used
     * @param name
     * @see ColorTags
     */
    public void setDisplayName(String name) {
        this.displayName = ColorTags.tagsToColors(name);
    }
    
    /**
     * 
     * @return default display name of item
     */
    public String getDisplayName() {
        return this.displayName;
    }
    
    /**
     * Sets codeName of item
     * @param name
     */
    public void setCodeName(String name) {
        this.codeName = name;
    }
    
    /**
     * 
     * @return codeName of item
     */
    public String getCodeName() {
        return this.codeName;
    }
    
    /**
     * Registers new item event listener
     * @param eventType
     * @param listener
     */
    public void registerListener(Class<?> eventClass, Method listener, 
            EventType eventType) {  
        //Create map which contains Event and it's type
        ListMultimap<EventType,Method> event = ArrayListMultimap.create();
        event.put(eventType, listener);
        this.itemEventMap.put(eventClass, event);
    }
    
    public void registerListener(Class<?> eventClass, String listener, 
            EventType eventType) {  
        Method method = null;
        try {
            method = this.getClass().getDeclaredMethod(listener, eventClass);
        } catch (NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            return;
        }
        
        this.registerListener(eventClass, method, eventType);
    }
    
    @Deprecated
    public void registerListener(Class<?> eventClass, String listener) {
        this.registerListener(eventClass, listener, EventType.ACTIVE);
    }
    
    /**
     * 
     * @return plain itemEventMap
     */
    public ListMultimap<Class<?>, ListMultimap<EventType, Method>> getItemEvents() {
        return this.itemEventMap;
    }
    
    /**
     * 
     * @param event
     * @param eventType
     * @return event listener Method
     * @throws EventNotHandledException 
     */
    public List<Method> getEventListener(Class<?> eventClass, EventType eventType) 
            throws EventNotHandledException {
        List<Method> listeners = new ArrayList<Method>();
        
        for (Entry<Class<?>, ListMultimap<EventType, Method>> entry : 
                this.itemEventMap.entries()) {
            //If evenClass is same than key in map:
            if ( eventClass == entry.getKey() ) {
                //There shouldn't be more than one key-value, but we need the key
                for (Entry<EventType, Method> innerEntry : entry.getValue().entries()) {
                    //If eventType == key, event is same type
                    if ( eventType == innerEntry.getKey() ) {
                        //Add event to map
                        listeners.add(innerEntry.getValue());
                    }
                }
            }
        }
        
        if ( listeners.isEmpty() || listeners == null ) {
            throw new EventNotHandledException("Custom item " + this.getCodeName() +
                    " don't handle event " + eventClass.getName());
        }
        return listeners;
    }
    
    /**
     * Sets material used to this item by default
     * @param material
     */
    public void setMaterial(Material material) {
        this.material = material;
    }
    
    /**
     * 
     * @return material used to this item
     */
    public Material getMaterial() {
        return this.material;
    }
    
    /**
     * Sets item attack damage
     * @param attackDamage
     */
    public void setAttackDamage(double attackDamage) {
        this.attackDamage = attackDamage;
    }
    
    /**
     * 
     * @return attack damage of item
     */
    public double getAttackDamage() {
        return this.attackDamage;
    }
    
    /**
     * 
     * @return is attack damage of item changed manually
     */
    public boolean isAttackDamageDefault() {
        if ( this.attackDamage == this.getDefaultAttackDamage() ) {
            //It is same
            return true;
        }
        return false;
    }
    
    /**
     * Sets damage value of item
     * @param damage
     */
    public void setDurability(short durability) {
        this.durability = durability;
    }
    
    /**
     * 
     * @return custom durability of item, -1 of it's not set
     */
    public short getDurabilityValue() {
        return this.durability;
    }
    
    /**
     * Sets lore of item
     * @param lore
     */
    public void setLore(List<String> lore) {
        this.lore = lore;
    }
    
    /**
     * 
     * @return lore of item, null if it's not set
     */
    public List<String> getLore() {
        return this.lore;
    }
    
    /**
     * Adds new custom data value
     * @param key
     * @param value
     */
    public void addCustomData(UUID key, String value) {
        this.customDataMap.put(key, value);
    }
    
    /**
     * 
     * @param key
     * @return custom data value
     */
    public String getCustomData(UUID key) {
        return this.customDataMap.get(key);
    }
    
    /**
     * 
     * @return customDataMap
     */
    public Map<UUID,String> getCustomDataMap() {
        return this.customDataMap;
    }
    
    /**
     * Sets amount of item
     * @param amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    /**
     * 
     * @return amount of item
     */
    public int getAmount() {
        return this.amount;
    }
    
    /**
     * Transfers CustomItem to ItemStack
     * @return ItemStack
     */
    public ItemStack toItemStack() {
        return StackTool.customItemToStack(this, this.getAmount());
    }
    
    /**
     * Transfers CustomItem to CustomStack which can then transferred to ItemStack
     * @return CustomStack (contains ItemStack)
     */
    public CustomStack toCustomStack() {
        try {
            return new CustomStack(this.toItemStack());
        } catch (NullItemException e) {
            //This should never happen, something is really weird if this happens
            e.printStackTrace();
        }
        return null; //This is only due to Java syntax restrictions
    }
    
    public Durability getDurability() {
        Durability durability = null;
        
        if ( this.getDurabilityValue() != -1 ) {
            //If durability is set, use it
            durability = new Durability(this.getDurabilityValue(), this.getMaterial());
        } else {
            //If isn't, use default
            durability = new Durability(this.getMaterial());
        }
        
        return durability;
    }
    
    /**
     * Saves the default attack damage of item for later use
     */
    private void saveDefaultAttackDamage() {
        ItemStack stack = new ItemStack(this.getMaterial(), 1);
        Attributes attributes = new Attributes(stack);
        
        for (Attribute entry : attributes.values()) {
            if ( entry.getAttributeType() == AttributeType.GENERIC_ATTACK_DAMAGE ) {
                this.defaultAttackDamage = entry.getAmount();
            }
        }
    }
    
    /**
     * 
     * @return default attack damage of item's material
     */
    public double getDefaultAttackDamage() {
        return this.defaultAttackDamage;
    }
    
    /**
     * Sets is crafting using this item allowed as normal item made same material
     * @param allow
     */
    public void setAllowCrafting(boolean allow) {
        this.allowCrafting = allow;
    }
    
    /**
     * 
     * @return is crafting using this item allowed
     */
    public boolean getAllowCrafting() {
        return this.allowCrafting;
    }
    
    /**
     * Add's event handlers defined by annotations to itemEventMap
     */
    private void addAnnotationEvents() {
        //Bukkit.getLogger().info("Debug: Called addAnnotationEvents");
        
        Class<?> c = this.getClass();
        Method[] methods = c.getMethods();
        
        for (Method method : methods) {
            Bukkit.getLogger().info("Debug: For loop");
            if ( method.isAnnotationPresent(ActiveEvent.class) ) {
                //Getting parameter of event listener
                Class<?> eventClass = method.getParameterTypes()[0];
                //Bukkit.getLogger().info("Debug: Found ActiveEvent " + 
                //        method.toString());
                this.registerListener(eventClass, method, EventType.ACTIVE);
            } else if ( method.isAnnotationPresent(PassiveEvent.class) ) {
                //Getting parameter of event listener
                Class<?> eventClass = method.getParameterTypes()[0];
                this.registerListener(eventClass, method, EventType.PASSIVE);
            }
        }
    }
}
