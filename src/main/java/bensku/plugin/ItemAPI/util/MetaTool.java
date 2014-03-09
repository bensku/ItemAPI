package bensku.plugin.ItemAPI.util;

import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

/**
 * Tools for metadata
 *
 */
public class MetaTool {
    private Plugin plugin;

    /**
     * Constructor
     * @param plugin
     */
    public MetaTool(Plugin plugin) {
        this.setPlugin(plugin);
    }

    /**
     * Sets plugin of metadata
     * @param plugin
     */
    public void setPlugin(Plugin plugin) {
        this.plugin = plugin;
    }

    /**
     * 
     * @return plugin of the metadata
     */
    public Plugin getPlugin() {
        return this.plugin;
    }

    /**
     * Set metadata
     * @param entity
     * @param key
     * @param value
     * @param plugin
     */
    public void setMetadata(Entity entity, String key, Object value){
        entity.setMetadata(key, new FixedMetadataValue(this.plugin, value));
    }

    /**
     * @param entity
     * @param key
     * @param plugin
     * @return metadata value
     */
    public Object getMetadata(Entity entity, String key) {
        List<MetadataValue> values = entity.getMetadata(key);  
        for (MetadataValue value : values){
            if ( value.getOwningPlugin().getDescription().getName().equals(
                    this.plugin.getDescription().getName()) ) {
                return value.value();
            }
        }
        return null;
    }
}
