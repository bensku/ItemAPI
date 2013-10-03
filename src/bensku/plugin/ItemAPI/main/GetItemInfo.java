package bensku.plugin.ItemAPI.main;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class GetItemInfo {
    public static String getDisplayName(String className) {
        try {
            Class<?> c = Class.forName(className);
            Object o = c.newInstance();
            Method main = c.getDeclaredMethod("getDisplayName");
            String ret = (String) main.invoke(o, null);
            return ret;

        } catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
        
    }
    public static String getCodeName(String className) {
        try {
            Class<?> c = Class.forName(className);
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
     * @return
     */
    public static String getClassName(String codeName) {
        String itemClass = CustomItemTool.itemClassMap.get(codeName);
        return itemClass;
    }
}
