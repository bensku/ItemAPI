package bensku.plugin.ItemAPI.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import bensku.plugin.ItemAPI.event.PlayerLeftClickEvent;
import bensku.plugin.ItemAPI.event.PlayerRightClickEvent;

/**
 * Creates PlayerRightClickEvent and PlayerLeftClickEvent using PlayerInteractEvent
 * @author bensku
 * @see PlayerRightClickEvent
 * @see PlayerLeftClickEvent
 */
public class ClickEventListener implements Listener {
    public void interactToClickEvents(PlayerInteractEvent event) {
        if ( event.getAction() == Action.LEFT_CLICK_AIR 
                || event.getAction() == Action.LEFT_CLICK_BLOCK) {
            PlayerLeftClickEvent newEvent = 
                    new PlayerLeftClickEvent(event.getPlayer(), event.getAction()
                            , event.getItem(), event.getClickedBlock(), 
                            event.getBlockFace());
            Bukkit.getServer().getPluginManager().callEvent(newEvent);
        } else if ( event.getAction() == Action.RIGHT_CLICK_AIR 
                || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            PlayerRightClickEvent newEvent = 
                    new PlayerRightClickEvent(event.getPlayer(), event.getAction()
                            , event.getItem(), event.getClickedBlock(), 
                            event.getBlockFace());
            Bukkit.getServer().getPluginManager().callEvent(newEvent);
        }
    }
}
