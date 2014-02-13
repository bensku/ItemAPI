package bensku.plugin.ItemAPI.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import bensku.plugin.ItemAPI.api.CustomItem;
import bensku.plugin.ItemAPI.exception.EventNotHandledException;
import bensku.plugin.ItemAPI.exception.ItemNotFoundException;
import bensku.plugin.ItemAPI.main.ItemRegistry;

/**
 * Calls custom item events using reflection
 * @author bensku
 * @since 2.00
 *
 */
public class EventReflector {
    private CustomItem item;
    private Class<?> c;
    private Object event; //Support multiple events using Object
    private Object o;
    private Class<?> cast; //Casting target
    
    /**
     * 
     * @param item
     */
    public EventReflector(CustomItem item, Object event, Class<?> cast) {
        this.item = item;
        this.c = item.getClass();
        try {
            this.o = c.newInstance(); //Set object
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        this.event = event;
        this.cast = cast;
    }
    
    /**
     * 
     * @param c
     */
    public EventReflector(Class<?> c, Object event, Class<?> cast) {
        try {
            this.item = (CustomItem) c.newInstance();
            this.o = c.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        this.c = c;
        this.event = event;
        this.cast = cast;
    }
    
    /**
     * 
     * @param codeName
     * @throws ItemNotFoundException
     */
    public EventReflector(String codeName, Object event, Class<?> cast) 
            throws ItemNotFoundException {
        Class<?> c = null;
        c = ItemRegistry.getClass(codeName);
        this.c = c; //Set class
        
        try {
            this.o = c.newInstance(); //Set object
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        
        try {
            this.item = (CustomItem) c.newInstance(); //Set CustomItem
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        this.event = event;
        this.cast = cast;
    }
    
    public void call() {
        //Bukkit.getLogger().info("Debug: call() EventReflector");
        List<Method> listeners = new ArrayList<Method>();
        try {
            //cast is actually event class, so using it...
            listeners = this.item.getEventListener(this.cast);
            //Bukkit.getLogger().info("Debug: listeners is " + listeners.toString());
        } catch (EventNotHandledException e) {
            //Do nothing
            //Bukkit.getLogger().info("Debug: EventNotHandledException catched");
        }

        Iterator<Method> it = listeners.iterator();
        while(it.hasNext()) {
            Method method = it.next();
            
            //Bukkit.getLogger().info("Debug: it has next");
            //Bukkit.getLogger().info("Debug: method is " +  method.toString());
            
            try {
                //Cast Object to event and invoke it
                //Using reflection because it's only way
                method.invoke(this.o, this.cast.cast(event));
            } catch (IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
