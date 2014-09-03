package bensku.plugin.ItemAPI.main;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

import bensku.plugin.ItemAPI.crafting.CustomRecipe;
import bensku.plugin.ItemAPI.crafting.Ingredient;
import bensku.plugin.ItemAPI.crafting.RecipeRegistry;
import bensku.plugin.ItemAPI.itemData.DataManager;
import bensku.plugin.ItemAPI.itemData.UUIDCache;
import bensku.plugin.ItemAPI.listener.CraftEventListener;
import bensku.plugin.ItemAPI.listener.CustomItemListener;
import bensku.plugin.ItemAPI.test.TestWand;

public final class ItemAPI extends JavaPlugin {
    private static ItemAPI api;
    private DataManager dataManager;
    private UUIDCache uuidCache;
    private Config config;
    
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
        } else if (cmd.getName().equalsIgnoreCase("nameditem")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Can't give item for console!");
            } else {
                Player player = (Player) sender;
                player.sendMessage("This test feature removed in ItemAPI 2.01, but "
                        + "dynamic custom names are of course still supported.");
            }
            return true;
        }
        return false;
    }

    public void onEnable() {
        this.saveDefaultConfig(); //Save default config if it not exist
        
        getServer().getPluginManager().registerEvents(new CustomItemListener(this), 
                this);
        getServer().getPluginManager().registerEvents(new CraftEventListener(), this);
        
        this.dataManager = new DataManager(this);
        this.uuidCache = new UUIDCache(this);
        this.config = new Config(this);
        api = this;
        
        //Tests
        ItemRegistry.registerCustomItem(TestWand.class);
        
        CustomRecipe recipe = new CustomRecipe();
        recipe.setIngredient(2, new Ingredient(new TestWand().toItemStack()));
        recipe.setResult(new Ingredient(Material.STONE));
        RecipeRegistry.registerRecipe(recipe);
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
    
    public DataManager getDataManager() {
        return this.dataManager;
    }
    
    public UUIDCache getUUIDCache() {
        return this.uuidCache;
    }
    
    public Config getConfig() {
        return config;
    }
}
