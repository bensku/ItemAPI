package bensku.plugin.ItemAPI.listener;

//import org.bukkit.Bukkit;
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

@Deprecated
@SuppressWarnings("unused")
public class LegacyPlayerListener implements Listener {
    @EventHandler
    public void customItemAttack(EntityDamageByEntityEvent event) {
        Player player =  (Player) event.getDamager();
        ItemStack itemStack = null; //Needed to initialize
        try {
           itemStack = player.getItemInHand();
        } catch (NullPointerException e) {
            return;
        }
        ItemMeta itemMeta = null;
        try {
            itemMeta = itemStack.getItemMeta();
            String exception = itemMeta.toString();
            //It throws exception if itemMeta = null
        } catch (NullPointerException e) {
            return;
        }
        
        if ( itemMeta.hasDisplayName() ) {
            String unhiddenName = HideTool.unhideString(itemMeta.getDisplayName());
            if ( unhiddenName.contains("<codename>") ) { //If found tag
                String codeName = TagTool.getTag("codename", unhiddenName);
                Class<?> c = GetItemInfo.getClass(codeName);
                PlayerEvents.onHit(c, event);
            }
        }
    }
    @EventHandler
    public void customItemBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = null; //Needed to initialize
        try {
           itemStack = player.getItemInHand();
        } catch (NullPointerException e) {
            return;
        }
        ItemMeta itemMeta = null;
        try {
            itemMeta = itemStack.getItemMeta();
            String exception = itemMeta.toString();
            //It throws exception if itemMeta = null
        } catch (NullPointerException e) {
            return;
        }
        
        if ( itemMeta.hasDisplayName() ) {
            String unhiddenName = HideTool.unhideString(itemMeta.getDisplayName());
            if ( unhiddenName.contains("<codename>") ) { //If found tag
                String codeName = TagTool.getTag("codename", unhiddenName);
                Class<?> c = GetItemInfo.getClass(codeName);
                PlayerEvents.onBlockBreak(c, event);
            }
        }
    }
    @EventHandler
    public void customItemConsume(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = player.getItemInHand();
        ItemMeta itemMeta = itemStack.getItemMeta();
        if ( itemMeta.hasDisplayName() ) {
            String unhiddenName = HideTool.unhideString(itemMeta.getDisplayName());
            if ( unhiddenName.contains("<codename>") ) { //If found tag
                String codeName = TagTool.getTag("codename", unhiddenName);
                Class<?> c = GetItemInfo.getClass(codeName);
                PlayerEvents.onConsume(c, event);
            }
        }
    }
    @EventHandler
    public void customItemInteract(PlayerInteractEvent event) {
        //Bukkit.getLogger().info("Debug: Event called");
        Player player = event.getPlayer();
        ItemStack itemStack = null; //Needed to initialize
        try {
           itemStack = player.getItemInHand();
        } catch (NullPointerException e) {
            return;
        }
        //Bukkit.getLogger().info("Debug: ItemStack is " + itemStack.toString());
        ItemMeta itemMeta = null;
        try {
            itemMeta = itemStack.getItemMeta();
            String exception = itemMeta.toString();
            //It throws exception if itemMeta = null
        } catch (NullPointerException e) {
            return;
        }
        //Bukkit.getLogger().info("Debug: itemStack and itemMeta OK");
        
        if ( itemMeta.hasDisplayName() ) {
            //Bukkit.getLogger().info("Debug: Found custom name");
            String unhiddenName = HideTool.unhideString(itemMeta.getDisplayName());
            //Bukkit.getLogger().info("Debug: Unhidden whole name is " + unhiddenName);
            if ( unhiddenName.contains("<codename>") ) { //If found tag
                //Bukkit.getLogger().info("Debug: Found tag <codename>");
                String codeName = TagTool.getTag("codename", unhiddenName);
                //Bukkit.getLogger().info("Debug: codeName is " + codeName);
                Class<?> c = GetItemInfo.getClass(codeName);
                //Bukkit.getLogger().info("Debug: Action is " + event.getAction());
                //Bukkit.getLogger().info("Debug: Class is " + c.getName());
                if ( event.getAction() == Action.LEFT_CLICK_AIR || 
                        event.getAction() == Action.LEFT_CLICK_BLOCK ) {
                    PlayerEvents.onLeftClick(c, event);
                    //Bukkit.getLogger().info("Debug: Reflection succeful");
                }
                else if ( event.getAction() == Action.RIGHT_CLICK_AIR || 
                        event.getAction() == Action.RIGHT_CLICK_BLOCK ) {
                    //Bukkit.getLogger().info("Debug: Running PlayerEvents.onRightClick");
                    PlayerEvents.onRightClick(c, event);
                }
            }
        }
    }
    
}
