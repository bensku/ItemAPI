package bensku.plugin.ItemAPI.main;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import bensku.plugin.ItemAPI.listener.PlayerListener;
import bensku.plugin.ItemAPI.test.NameWand;
import bensku.plugin.ItemAPI.test.TestItem;
import bensku.plugin.ItemAPI.test.TestWand;

public final class ItemAPI extends JavaPlugin {
    @EventHandler
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("testitem")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Can't give item for console!");
            } else {
                Player player = (Player) sender;
                player.getInventory().addItem(CustomItemTool.getCustomItemStack(
                        "testWand", Material.STICK, 1));
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
        
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        
        //Tests
        getLogger().info("Running tests:");
        getLogger().info(GetItemInfo.getDisplayName(TestItem.class));
        getLogger().info(GetItemInfo.getCodeName(TestItem.class));
        CustomItemTool.addCustomItem(TestWand.class);
        getLogger().info("Wand of testing: OK!");
        CustomItemTool.addCustomItem(NameWand.class);
        getLogger().info("Wand of naming: OK!");
        getLogger().info("Done!");
    }
 
    public void onDisable(){
        
    }
}
