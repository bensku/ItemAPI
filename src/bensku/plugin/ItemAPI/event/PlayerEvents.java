package bensku.plugin.ItemAPI.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class PlayerEvents {
    public static String onHit(String className, Player self, Entity target,
            Location playerCoords, Location targetCoords) {
        try {
            Class<?> c = Class.forName(className);
            Method main = c.getDeclaredMethod("onHit", Player.class, Entity.class,
                    Location.class, Location.class);
            String ret = (String) main.invoke(null, self, target, playerCoords,
                    targetCoords);
            return ret;

        } catch (ClassNotFoundException x) {
            x.printStackTrace();
        } catch (NoSuchMethodException x) {
            //If some method don't exist, do nothing
        } catch (IllegalAccessException x) {
            x.printStackTrace();
        } catch (InvocationTargetException x) {
            x.printStackTrace();
        }
        return null;
    }
    public static String onBlockBreak(String className, Player self, Block target,
            Location playerCoords, Location targetCoords) {
        try {
            Class<?> c = Class.forName(className);
            Method main = c.getDeclaredMethod("onBlockBreak", Player.class, Block.class,
                    Location.class, Location.class);
            String ret = (String) main.invoke(null, self, target, playerCoords,
                    targetCoords);
            return ret;

        } catch (ClassNotFoundException x) {
            x.printStackTrace();
        } catch (NoSuchMethodException x) {
            //If some method don't exist, do nothing
        } catch (IllegalAccessException x) {
            x.printStackTrace();
        } catch (InvocationTargetException x) {
            x.printStackTrace();
        }
        return null;
    }
    public static String onConsume(String className, Player self,
            Location playerCoords) {
        try {
            Class<?> c = Class.forName(className);
            Method main = c.getDeclaredMethod("onConsume", Player.class,
                    Location.class);
            String ret = (String) main.invoke(null, self, playerCoords);
            return ret;

        } catch (ClassNotFoundException x) {
            x.printStackTrace();
        } catch (NoSuchMethodException x) {
            //If some method don't exist, do nothing
        } catch (IllegalAccessException x) {
            x.printStackTrace();
        } catch (InvocationTargetException x) {
            x.printStackTrace();
        }
        return null;
    }
}