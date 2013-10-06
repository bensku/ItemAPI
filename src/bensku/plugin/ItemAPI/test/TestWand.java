package bensku.plugin.ItemAPI.test;

//import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import bensku.plugin.ItemAPI.main.CustomItem;

public class TestWand implements CustomItem {

    @Override
    public String getCodeName() {
        // TODO Auto-generated method stub
        return "testWand";
    }

    @Override
    public String getDisplayName() {
        // TODO Auto-generated method stub
        return ChatColor.AQUA + "Staff of testing";
    }
    
    @Override
    public void onHit(EntityDamageByEntityEvent event) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onBlockBreak(BlockBreakEvent event) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onConsume(PlayerItemConsumeEvent event) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onRightClick(PlayerInteractEvent event) {
        event.getPlayer().sendMessage("You used Staff of testing!");
        //Bukkit.getLogger().info("Debug: Runned onRightClick in TestWand.class");
        
    }

    @Override
    public void onLeftClick(PlayerInteractEvent event) {
        // TODO Auto-generated method stub
        
    }

}
