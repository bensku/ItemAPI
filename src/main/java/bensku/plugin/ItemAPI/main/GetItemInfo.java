package bensku.plugin.ItemAPI.main;

import java.lang.reflect.Method;

@Deprecated
public class GetItemInfo {
    /**
     * Returns class by codename
     * @param c
     * @return class
     */
    public static String getDisplayName(Class<?> c) {
        try {
            Object o = c.newInstance();
            Method main = c.getDeclaredMethod("getDisplayName");
            String ret = (String) main.invoke(o, null);
            return ret;

        } catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
        
    }
    /**
     * Return codename by class
     * @param c
     * @return codeName
     */
    public static String getCodeName(Class<?> c) {
        try {
            Object o = c.newInstance();
            Method main = c.getDeclaredMethod("getCodeName");
            String ret = (String) main.invoke(o, null);
            return ret;
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
        
    }
    /**
     * Return class of item codename which given
     * @param codeName
     * @return class
     */
    public static Class<?> getClass(String codeName) {
        Class<?> itemClass = CustomItemTool.itemClassMap.get(codeName);
        return itemClass;
    }
    /**
     * Return is crafting using custom item allowed
     * @param codeName
     * @return boolean
     */
    public static boolean getAllowCrafting(String codeName) {
        Class<?> c = getClass(codeName);
        try {
            Object o = c.newInstance();
            Method main = c.getDeclaredMethod("allowCrafting");
            boolean ret = (boolean) main.invoke(o, null);
            return ret;

        } catch(Exception ex){
            return false; //Crafting is disallowed by default
        }
    }
}
