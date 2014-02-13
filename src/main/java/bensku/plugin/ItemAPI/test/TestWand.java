package bensku.plugin.ItemAPI.test;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import bensku.plugin.ItemAPI.api.CustomItem;

public class TestWand extends CustomItem {
    public TestWand() {
        super("testWand"); //CodeName of item
        this.setDisplayName("<aqua>Staff of Testing");
        this.setMaterial(Material.STICK);
        this.setAttackDamage(10.0);
        
        this.registerListener(EntityDamageByEntityEvent.class, "onHit");
        this.registerListener(EntityDamageByEntityEvent.class, "onHit2");
        this.registerListener(PlayerInteractEvent.class, "onInteract");
    }
    
    public void onHit(EntityDamageByEntityEvent event) {
        Player player = (Player) event.getDamager();
        player.sendMessage("You hitted with Staff of Testing");
    }
    
    public void onHit2(EntityDamageByEntityEvent event) {
        Player player = (Player) event.getDamager();
        player.sendMessage("Hit caused 10 damage");
    }
    
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        player.sendMessage("You interacted with Staff of Testing");
    }
}
