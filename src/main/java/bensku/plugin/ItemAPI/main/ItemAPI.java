package bensku.plugin.ItemAPI.main;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import bensku.plugin.ItemAPI.listener.CraftingListener;
import bensku.plugin.ItemAPI.listener.CustomItemListener;
import bensku.plugin.ItemAPI.listener.LegacyPlayerListener;
import bensku.plugin.ItemAPI.test.NameWand;
import bensku.plugin.ItemAPI.test.TestItem;
import bensku.plugin.ItemAPI.test.TestWand;

@SuppressWarnings("deprecation") //There is legacy code
public final class ItemAPI extends JavaPlugin {
    /**
     * Constructor for logger
     * @since 2.00
     * 
     */
    public ItemAPI() {
        
    }
    
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
                ItemStack stack = CustomItemTool.getNamedCustomItemStack(
                        "nameWand", Material.STICK, 1, ChatColor.AQUA + 
                        player.getName() + "'s Staff of naming");
                player.getInventory().addItem(stack);
            }
            return true;
        }
        return false;
    }

    public void onEnable(){
        this.saveDefaultConfig(); //Save default config if it not exist
        
        getServer().getPluginManager().registerEvents(new LegacyPlayerListener(), this);
        getServer().getPluginManager().registerEvents(new CraftingListener(), this);
        getServer().getPluginManager().registerEvents(new CustomItemListener(), this);
        
        //Tests
        getLogger().info("Running tests:");
        getLogger().info(GetItemInfo.getDisplayName(TestItem.class));
        getLogger().info(GetItemInfo.getCodeName(TestItem.class));
        ItemRegistry.registerCustomItem(TestWand.class);
        getLogger().info("Wand of testing: OK!");
        CustomItemTool.addCustomItem(NameWand.class);
        getLogger().info("Wand of naming (legacy): OK!");
        getLogger().info("Done!");
    }
 
    public void onDisable(){
        
    }
}
