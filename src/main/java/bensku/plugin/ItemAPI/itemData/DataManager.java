package bensku.plugin.ItemAPI.itemData;

import java.util.ArrayList;
import java.util.List;

import bensku.plugin.ItemAPI.main.ItemAPI;

/**
 * Data manager, which contains all registered data handlers.
 * @author bensku
 * @since 2.03
 *
 */
public class DataManager {
    private List<DataHandler> handlers;
    
    /**
     * Don't use this constructor.
     * @param api
     */
    public DataManager(ItemAPI api) {
        handlers = new ArrayList<DataHandler>();
    }
    
    /**
     * Registers new DataHandler
     * @param handler
     */
    public void registerHandler(DataHandler handler) {
        handlers.add(handler);
    }
    
    /**
     * Unregisters given handler from registry.
     * @param handler
     */
    public void unregisterHandler(DataHandler handler) {
        handlers.remove(handler);
    }
    
    /**
     * Gets instance of handler by its name.
     * @param name
     * @return handler
     */
    public DataHandler getHandler(String name) {
        for (int i = 0; i < handlers.size(); i++) {
            DataHandler handler = this.handlers.get(i);
            if ( handler.getClass().getName().equals(name) ) {
                return handler;
            }
        }
        
        return null;
    }
}
