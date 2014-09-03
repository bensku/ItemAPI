package bensku.plugin.ItemAPI.main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

import bensku.plugin.ItemAPI.itemData.DataHandler;
import bensku.plugin.ItemAPI.itemData.DataManager;
import bensku.plugin.ItemAPI.itemData.UUIDCache;
import bensku.plugin.ItemAPI.itemData.handlers.AttributeHandler;
import bensku.plugin.ItemAPI.listener.CraftEventListener;
import bensku.plugin.ItemAPI.listener.CustomItemListener;
import bensku.plugin.ItemAPI.test.TestWand;

public final class ItemAPI extends JavaPlugin {
    private static ItemAPI api;
    private DataManager dataManager;
    private UUIDCache uuidCache;
    private Config config;
    private DataHandler dataHandler;
    
    @EventHandler
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("testitem")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Can't give item for console!");
            } else {
                Player player = (Player) sender;
                //Bukkit.getLogger().info("Debug: item is " + item.toString());
                //Bukkit.getLogger().info("Debug: Stack is " + item.toItemStack());
                player.getInventory().addItem(new TestWand().toItemStack());
            }
            return true;
        }
        return false;
    }

    public void onEnable() {
        api = this;
        this.dataManager = new DataManager(this);
        this.uuidCache = new UUIDCache(this);
        this.config = new Config(this);
        
        this.dataManager.registerHandler(new AttributeHandler());
        this.dataHandler = this.getDataHandler();
        
        this.saveDefaultConfig(); //Save default config if it not exist
        
        getServer().getPluginManager().registerEvents(new CustomItemListener(this), 
                this);
        getServer().getPluginManager().registerEvents(new CraftEventListener(), this);
        
        //Tests
        ItemRegistry.registerCustomItem(TestWand.class);
        
        //CustomRecipe recipe = new CustomRecipe();
        //recipe.setIngredient(2, new Ingredient(new TestWand().toItemStack()));
        //recipe.setResult(new Ingredient(Material.STONE));
        //RecipeRegistry.registerRecipe(recipe);
    }
 
    public void onDisable() {
        
    }
    
    /**
     * Returns ItemAPI main object.
     * @return
     */
    public static ItemAPI getAPI() {
        return api;
    }
    
    /**
     * 
     * @return data manager of ItemAPI
     */
    public DataManager getDataManager() {
        return this.dataManager;
    }
    
    /**
     * 
     * @return ItemAPI's default UUID cache
     */
    public UUIDCache getUUIDCache() {
        return this.uuidCache;
    }
    
    /**
     * @return default config object
     */
    public Config getConfig() {
        return config;
    }
    
    /**
     * Gets default DataHandler that is specified in config
     * @return default DataHandler
     */
    public DataHandler getDataHandler() {
        if ( this.dataHandler == null ) {
            this.getLogger().warning("Unknown DataHandler " + 
                    this.config.getString("data-save.handler") + 
                    ", using AttributeHandler.");
            this.dataHandler = dataManager.getHandler("AttributeHandler");
        }
        
        return this.dataHandler;
    }
}
