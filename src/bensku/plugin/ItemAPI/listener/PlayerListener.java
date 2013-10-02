package bensku.plugin.ItemAPI.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import bensku.plugin.ItemAPI.event.PlayerEvents;
import bensku.plugin.ItemAPI.main.GetItemInfo;
import bensku.plugin.ItemAPI.util.HideTool;
import bensku.plugin.ItemAPI.util.TagTool;

public class PlayerListener implements Listener {
    @EventHandler
    public void customItemAttack(EntityDamageByEntityEvent event) {
        Player player =  (Player) event.getDamager();
        ItemStack weaponItemStack = player.getItemInHand();
        ItemMeta itemMeta = weaponItemStack.getItemMeta();
        if ( itemMeta.hasDisplayName() ) {
            if ( itemMeta.getDisplayName().contains("<codename>") ) { //If found tag
                String codeName = TagTool.getTag("codename", itemMeta.getDisplayName());
                String className = GetItemInfo.getClassName(HideTool.unhideString(codeName));
                PlayerEvents.onHit(className, event);
            }
        }
    }
    @EventHandler
    public void customItemBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = player.getItemInHand();
        ItemMeta itemMeta = itemStack.getItemMeta();
        if ( itemMeta.hasDisplayName() ) {
            if ( itemMeta.getDisplayName().contains("<codename>") ) { //If found tag
                String codeName = TagTool.getTag("codename", itemMeta.getDisplayName());
                String className = GetItemInfo.getClassName(HideTool.unhideString(codeName));
                PlayerEvents.onBlockBreak(className, event);
            }
        }
    }
    @EventHandler
    public void customItemConsume(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = player.getItemInHand();
        ItemMeta itemMeta = itemStack.getItemMeta();
        if ( itemMeta.hasDisplayName() ) {
            if ( itemMeta.getDisplayName().contains("<codename>") ) { //If found tag
                String codeName = TagTool.getTag("codename", itemMeta.getDisplayName());
                String className = GetItemInfo.getClassName(HideTool.unhideString(codeName));
                PlayerEvents.onConsume(className, event);
            }
        }
    }
    public void customItemInteract(PlayerInteractEvent event) {
        if ( event.getAction() == Action.LEFT_CLICK_AIR || 
                event.getAction() == Action.LEFT_CLICK_BLOCK ) {
            
        }
        if ( event.getAction() == Action.RIGHT_CLICK_AIR || 
                event.getAction() == Action.LEFT_CLICK_BLOCK ) {
            
        }
    }
    
}
