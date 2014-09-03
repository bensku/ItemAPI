package bensku.plugin.ItemAPI.main;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import bensku.plugin.ItemAPI.exception.ConfigException;

/**
 * ItemAPI's yaml configuration utily.
 * @author bensku
 * @since 2.03
 *
 */
public class Config extends YamlConfiguration {
    private File configFile;
    
    public Config(File file) {
        this.configFile = file;
        if ( file.exists() ) {
            this.load(this.configFile);
        }
    }
    
    public Config(Plugin plugin) {
        this(new File(plugin.getDataFolder() + "/config.yml"));
    }
    
    @Override
    public void save(File file) {
        try {
            super.save(this.configFile);
        } catch (IOException e) {
            throw new ConfigException("Cannot save config file " + file);
        }
    }
    
    public void load(File file) {
        try {
            super.load(file);
        } catch (IOException
                | InvalidConfigurationException e) {
            throw new ConfigException("Cannot load config file " + file);
        }
    }
    
    /**
     * Saves config to file defined in constructor.
     */
    public void save() {
        this.save(this.configFile);
    }
    
    /**
     * 
     * @return config file
     */
    public File getConfigFile() {
        return this.configFile;
    }
    
    public void copyFromJar(Plugin plugin) {
        if ( !this.configFile.exists() ) {
            plugin.saveResource(this.configFile.getName(), false);
        }
    }
}
