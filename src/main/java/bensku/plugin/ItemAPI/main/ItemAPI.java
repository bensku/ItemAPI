package bensku.plugin.ItemAPI.main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

import bensku.plugin.ItemAPI.crafting.CraftEventListener;
import bensku.plugin.ItemAPI.crafting.CustomRecipe;
import bensku.plugin.ItemAPI.crafting.RecipeRegistry;
import bensku.plugin.ItemAPI.listener.CraftingListener;
import bensku.plugin.ItemAPI.listener.CustomItemListener;
import bensku.plugin.ItemAPI.test.TestWand;

public final class ItemAPI extends JavaPlugin {
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
                player.sendMessage("This feature removed in ItemAPI 2.01");
            }
            return true;
        }
        return false;
    }

    public void onEnable() {
        this.saveDefaultConfig(); //Save default config if it not exist
        
        getServer().getPluginManager().registerEvents(new CraftingListener(), this);
        getServer().getPluginManager().registerEvents(new CustomItemListener(this), 
                this);
        getServer().getPluginManager().registerEvents(new CraftEventListener(), this);
        
        //Tests
        ItemRegistry.registerCustomItem(TestWand.class);
        
        CustomRecipe recipe = new CustomRecipe();
        recipe.setIngredient(2, new TestWand().toItemStack());
        recipe.setResult(new TestWand().toItemStack());
        RecipeRegistry.registerRecipe(recipe);
        
        //ShapedRecipe bukkitRecipe = new ShapedRecipe(new TestWand().toItemStack());
        //bukkitRecipe.shape(" a ", "   ", "   ");
        //bukkitRecipe.setIngredient('a', Material.DIAMOND);
        //this.getServer().addRecipe(bukkitRecipe);
        //Bukkit.getLogger().info("Debug: Ingredients are " + bukkitRecipe.getIngredientMap());
    }
 
    public void onDisable(){
        
    }
}
