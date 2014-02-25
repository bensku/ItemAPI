package bensku.plugin.ItemAPI.util;

import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

public class MetaTool {
    public void setMetadata(Entity entity, String key, Object value, Plugin plugin){
        entity.setMetadata(key,new FixedMetadataValue(plugin,value));
      }
      public Object getMetadata(Entity entity, String key, Plugin plugin){
        List<MetadataValue> values = entity.getMetadata(key);  
        for (MetadataValue value : values){
           if ( value.getOwningPlugin().getDescription().getName().equals(
                   plugin.getDescription().getName()) ) {
              return value.value();
           }
        }
        return null;
      }
}
