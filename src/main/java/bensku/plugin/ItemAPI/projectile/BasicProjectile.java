package bensku.plugin.ItemAPI.projectile;

import bensku.plugin.ItemAPI.api.CustomItem;

/**
 * Basic projectile object
 * @author bensku
 * @since 2.00
 *
 */
public class BasicProjectile {
    private Class<?> type;
    private Class<?> launcherItem;
    
    public BasicProjectile(Class<?> type) {
        this.setType(type);
    }
    
    public BasicProjectile(Class<?> type, Class<?> item) {
        this.setType(type);
        this.setLaucherItem(item);
    }
    
    public BasicProjectile(Class<?> type,CustomItem item) {
        this.setType(type);
        this.setLauncherItem(item);
    }
    
    /**
     * Sets type of projectile
     * @param type
     */
    public void setType(Class<?> type){
        this.type = type;
    }
    
    /**
     * 
     * @return type of projectile
     */
    public Class<?> getType() {
        return this.type;
    }
    
    /**
     * Sets launcher CustomItem using it's Class
     * @param item
     */
    public void setLaucherItem(Class<?> item) {
        this.launcherItem = item;
    }
    
    /**
     * Sets laucher CustomItem using it as CustomItem
     * @param item
     */
    public void setLauncherItem(CustomItem item) {
        this.setLaucherItem(item.getClass());
    }
    
    /**
     * 
     * @return item which launches the projectile
     */
    public Class<?> getLauncherItem() {
        return this.launcherItem;
    }
}
