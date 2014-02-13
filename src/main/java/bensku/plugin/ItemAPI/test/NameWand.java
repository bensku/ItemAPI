package bensku.plugin.ItemAPI.test;

import org.bukkit.ChatColor;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import bensku.plugin.ItemAPI.main.CustomItem;

@SuppressWarnings("deprecation")
public class NameWand implements CustomItem {

    @Override
    public String getCodeName() {
        return "nameWand";
    }

    @Override
    public String getDisplayName() {
        return ChatColor.AQUA + "Wand of naming";
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
        event.getPlayer().sendMessage("You used Staff of naming!");
        
    }

    @Override
    public void onLeftClick(PlayerInteractEvent event) {
        // TODO Auto-generated method stub
        
    }

}
