package bensku.plugin.ItemAPI.event;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Cancellable;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public final class PlayerRightClickEvent extends PlayerInteractEvent 
        implements Cancellable {
    
    public PlayerRightClickEvent(Player who, Action action, ItemStack item,
            Block clickedBlock, BlockFace clickedFace) {
        super(who, action, item, clickedBlock, clickedFace);
    }

    private static final HandlerList handlers = new HandlerList();

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}