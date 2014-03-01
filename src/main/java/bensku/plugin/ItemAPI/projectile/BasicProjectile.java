package bensku.plugin.ItemAPI.projectile;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Projectile;
import org.bukkit.metadata.FixedMetadataValue;

import bensku.plugin.ItemAPI.api.CustomItem;
import bensku.plugin.ItemAPI.api.ItemAPIMeta;
import bensku.plugin.ItemAPI.main.ItemAPI;

/**
 * Basic projectile object
 * @author bensku
 * @since 2.00
 *
 */
public class BasicProjectile {
    private Class<Entity> type;
    private Class<?> launcherItem;
    
    public BasicProjectile(Class<Entity> type) {
        this.setType(type);
    }
    
    public BasicProjectile(Class<Entity> type, Class<?> item) {
        this.setType(type);
        this.setLaucherItem(item);
    }
    
    public BasicProjectile(Class<Entity> type,CustomItem item) {
        this.setType(type);
        this.setLauncherItem(item);
    }
    
    /**
     * Sets type of projectile
     * @param type
     */
    public void setType(Class<Entity> type){
        this.type = type;
    }
    
    /**
     * 
     * @return type of projectile
     */
    public Class<Entity> getType() {
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
     * Sets launcher CustomItem using it as CustomItem
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
    
    public void spawn(Location location) {
        Projectile entity = (Projectile) 
                location.getWorld().spawn(location, this.getType());
        
        CustomItem item = null;
        try {
            item = (CustomItem) this.getLauncherItem().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        
        String codeName = null;
        try {
            codeName = item.getCodeName();
        } catch (NullPointerException e) {
            //There isn't any CustomItem
            return;
        }
        
        //Set the metadata
        entity.setMetadata(ItemAPIMeta.LAUNCHER_CODENAME,
                new FixedMetadataValue(new ItemAPI(), codeName));
    }
}
