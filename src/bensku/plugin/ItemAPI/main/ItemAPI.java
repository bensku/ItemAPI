package bensku.plugin.ItemAPI.main;

import org.bukkit.plugin.java.JavaPlugin;

public final class ItemAPI extends JavaPlugin {
    public void onEnable(){
        this.saveDefaultConfig(); //Save default config if it not exist
        
        //Tests
        getLogger().info("Running tests:");
        getLogger().info(GetItemInfo.getDisplayName("bensku.ItemAPI.custom.test.TestItem"));
        getLogger().info(GetItemInfo.getCodeName("bensku.ItemAPI.custom.test.TestItem"));
        getLogger().info("Done!");
    }
 
    public void onDisable(){
        
    }
}
