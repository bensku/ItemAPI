package bensku.plugin.ItemAPI.main;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

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
        }
        return false;
    }

    public void onEnable(){
        this.saveDefaultConfig(); //Save default config if it not exist
        
        //Tests
        getLogger().info("Running tests:");
        getLogger().info(GetItemInfo.getDisplayName(TestItem.class));
        getLogger().info(GetItemInfo.getCodeName(TestItem.class));
        CustomItemTool.addCustomItem(TestWand.class);
        getLogger().info("Wand of testing: OK!");
        getLogger().info("Done!");
    }
 
    public void onDisable(){
        
    }
}
