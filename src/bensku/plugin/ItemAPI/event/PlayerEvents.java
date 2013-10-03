package bensku.plugin.ItemAPI.event;

import java.lang.reflect.Method;

import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class PlayerEvents {
    public static String onHit(String className, EntityDamageByEntityEvent event) {
        try {
            Class<?> c = Class.forName(className);
            Object o = c.newInstance();
            Method main = c.getDeclaredMethod("onHit", EntityDamageByEntityEvent.class);
            String ret = (String) main.invoke(o, event);
            return ret;

        } catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    public static String onBlockBreak(String className, BlockBreakEvent event) {
        try {
            Class<?> c = Class.forName(className);
            Object o = c.newInstance();
            Method main = c.getDeclaredMethod("onBlockBreak", BlockBreakEvent.class);
            String ret = (String) main.invoke(o, event);
            return ret;

        } catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    public static String onConsume(String className, PlayerItemConsumeEvent event) {
        try {
            Class<?> c = Class.forName(className);
            Object o = c.newInstance();
            Method main = c.getDeclaredMethod("onConsume", PlayerItemConsumeEvent.class);
            String ret = (String) main.invoke(o, event);
            return ret;

        } catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    public static String onRightClick(String className, PlayerInteractEvent event) {
        try {
            Class<?> c = Class.forName(className);
            Object o = c.newInstance();
            Method main = c.getDeclaredMethod("onRightClick", PlayerInteractEvent.class);
            String ret = (String) main.invoke(o, event);
            return ret;

        } catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    public static String onLeftClick(String className, PlayerInteractEvent event) {
        try {
            Class<?> c = Class.forName(className);
            Object o = c.newInstance();
            Method main = c.getDeclaredMethod("onLeftClick", PlayerInteractEvent.class);
            String ret = (String) main.invoke(o, event);
            return ret;

        } catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}