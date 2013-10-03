package bensku.plugin.ItemAPI.main;

import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public interface CustomItem {
    public String getCodeName();
    public String getDisplayName();
    public void onHit(EntityDamageByEntityEvent event);
    public void onBlockBreak(BlockBreakEvent event);
    public void onConsume(PlayerItemConsumeEvent event);
    public void onRightClick(PlayerInteractEvent event);
    public void onLeftClick(PlayerInteractEvent event);
}
