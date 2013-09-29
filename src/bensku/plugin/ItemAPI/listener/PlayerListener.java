package bensku.plugin.ItemAPI.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
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
                PlayerEvents.onHit(className, player, event.getEntity(),
                        player.getLocation(), event.getEntity().getLocation());
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
                PlayerEvents.onBlockBreak(className, player, event.getBlock(),
                        player.getLocation(), event.getBlock().getLocation());
            }
        }
    }
    @EventHandler
    public void customItemConsume(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = player.getItemInHand();
        ItemMeta itemMeta = itemStack.getItemMeta();
        if ( itemMeta.hasDisplayName() ) {
            if ( itemMeta.getDisplayName().contains("<codename>") ) { //If found tag
                String codeName = TagTool.getTag("codename", itemMeta.getDisplayName());
                String className = GetItemInfo.getClassName(HideTool.unhideString(codeName));
                PlayerEvents.onConsume(className, player, player.getLocation());
            }
        }
    }
    
}
