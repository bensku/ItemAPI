package bensku.plugin.ItemAPI.itemData;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.minecraft.util.org.apache.commons.io.FileUtils;

import org.bukkit.Bukkit;
import org.bukkit.World;

import bensku.plugin.ItemAPI.exception.UUIDCacheException;
import bensku.plugin.ItemAPI.main.Config;
import bensku.plugin.ItemAPI.main.ItemAPI;

/**
 * UUID cache, which allows using normal strings in AttributeStorage.
 * @author bensku
 * @since 2.03
 *
 */
public class UUIDCache extends Config {
    /**
     * Don't use this constructor.
     * @param api
     */
    public UUIDCache(ItemAPI api) {
        super(new File(api.getDataFolder() + "/uuidcache.yml"));
        
        this.copyFromJar(api);
        
        List<World> worlds = Bukkit.getServer().getWorlds();
        
        for (int i = 0; i < worlds.size(); i++) { //Loop thought all worlds
            File folder = worlds.get(i).getWorldFolder();
            File worldCache = new File(folder + "/uuidcache.yml");
            
            try {
                if ( !FileUtils.contentEquals(this.getConfigFile(), worldCache) ) {
                    //Files have different contents so we merge the contents from worldCache
                    Config worldCfg = new Config(worldCache);
                    Map<String,Object> worldValues = worldCfg.getValues(false);
                    
                    for (Map.Entry<String, Object> entry : worldValues.entrySet()) {
                        String key = entry.getKey(); //Its UUID
                        Object value = entry.getValue();
                        
                        //UUID not found in current file, so adding it
                        if ( !this.contains(key) ) {
                            this.set(key, value);
                        } else {
                            //UUID found in file
                            if ( value.equals(this.get(key)) ) {
                                //All is good; continuing
                                continue;
                            } else {
                                //This is rare... And causes problems
                                api.getLogger().severe("Found duplicate UUID in "
                                        + worldCfg.getConfigFile().getCanonicalPath() + "!");
                                api.getLogger().severe("UUID " + key + " is also found"
                                        + " in ItemAPi's own uuidcache.yml, but with "
                                        + "different meaning.");
                                Bukkit.getPluginManager().disablePlugin(api);
                            }
                        }
                    }
                }
            } catch (IOException e) {
                //File not found; no need to merge.
               continue;
            }
        }
    }
    
    @Override
    public void save() {
        super.save();
        List<World> worlds = Bukkit.getServer().getWorlds();
        
        for (int i = 0; i < worlds.size(); i++) { //Loop thought all worlds
            File folder = worlds.get(i).getWorldFolder();
            this.save(new File(folder + "/uuidcache.yml")); //Save to world's cache
        }
    }
    
    /**
     * Gets UUID for given name. If it won't exist in cache, it will created.
     * @param name
     * @return UUID for name
     */
    public UUID getUUID(String name) {
        Config config = ItemAPI.getAPI().getConfig();
        if ( !config.getBoolean("data-save.parameters.uuid-caching") ) {
            throw new UUIDCacheException(); //UUID caching is disabled
        }
        
        Map<String,Object> values = this.getValues(false);
        for (Map.Entry<String, Object> entry : values.entrySet()) {
            String key = entry.getKey(); //Its UUID
            Object value = entry.getValue();
            
            if ( value.equals(name) ) {
                return UUID.fromString(key);
            }
        }
        
        UUID random = UUID.randomUUID();
        this.set(random.toString(), name);
        return random;
    }
}
