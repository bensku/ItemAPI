package bensku.plugin.ItemAPI.main;

import java.util.HashMap;
import java.util.Map;

import bensku.plugin.ItemAPI.api.CustomItem;
import bensku.plugin.ItemAPI.exception.ItemNotFoundException;

/**
 * New item registry
 * @author bensku
 * @since 2.00
 *
 */
public class ItemRegistry {
    /**
     * Contains all custom item codenames and classes
     */
    public static Map<String,Class<?>> itemClassMap = new HashMap<String,Class<?>>();
    
    /**
     * Registers custom item
     * @param class
     */
    public static void registerCustomItem(Class<?> c) {
        String codeName = null;
        try {
            codeName = getCodeName(c);
        } catch (ItemNotFoundException e) {
            return;
        }
        itemClassMap.put(codeName, c);
    }
    
    /**
     * Registers custom item (using Object instead of Class)
     * @param object of item
     */
    public static void registerCustomItem(Object o) {
    	registerCustomItem(o.getClass());
    }
    
    /**
     * Returns class of item by codeName
     * @param codeName
     * @return class of item
     * @throws ItemNotFoundException if item doesn't exist
     * @see CustomItem
     */
    public static Class<?> getClass(String codeName) throws ItemNotFoundException {
        Class<?> c = itemClassMap.get(codeName);
        
        if ( c == null ) {
            throw new ItemNotFoundException("Custom item using codeName " +
                    codeName + " not found");
        }
        return c;
    }
    
    /**
     * 
     * @param c
     * @return codeName of custom item
     * @throws ItemNotFoundException
     */
    public static String getCodeName(Class<?> c) throws ItemNotFoundException {
        CustomItem item = null;
        try {
            Object o = c.newInstance();
            item = (CustomItem) o;
        } catch(Exception e) {
            throw new ItemNotFoundException("Class " +
                    c.getName() + " not found or it isn't custom item class");
        }
        
        return item.getCodeName();
    }
}
