package bensku.plugin.ItemAPI.listener;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

import bensku.plugin.ItemAPI.api.CustomStack;
import bensku.plugin.ItemAPI.api.ItemAPIMeta;
import bensku.plugin.ItemAPI.api.event.EventType;
import bensku.plugin.ItemAPI.event.EventReflector;
import bensku.plugin.ItemAPI.exception.ItemNotFoundException;
import bensku.plugin.ItemAPI.exception.NullItemException;
import bensku.plugin.ItemAPI.main.ItemAPI;
import bensku.plugin.ItemAPI.util.MetaTool;

/**
 * 
 * @author bensku
 * @since 2.00
 *
 */
public class CustomItemListener implements Listener {
    private MetaTool metaTool;
    
    public CustomItemListener(ItemAPI api) {
        metaTool = new MetaTool(api);
    }
    
    /**
     * 
     * @param event
     * @throws NullItemException
     */
    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event) throws NullItemException {
        //Bukkit.getLogger().info("Debug: EntityDamageByEntityEvent handled");

        Entity attacker = event.getDamager();
        String codeName;
        //Bukkit.getLogger().info("Debug: attacker is " + attacker.toString());

        if ( !(attacker instanceof LivingEntity) ) {
            //If attacker isn't LivingEntity, it can't hold item
            codeName = (String) metaTool.getMetadata(attacker, 
                    ItemAPIMeta.LAUNCHER_CODENAME); //Get codeName of the launcher item
            
            //So we try to find item which launched it
            
            try {
                new EventReflector(codeName, event, EntityDamageByEntityEvent.class, 
                        EventType.ACTIVE).call(); //Call event
                //Bukkit.getLogger().info("Debug: EventReflector called succesfully");
            } catch (ItemNotFoundException e) {
                //Someone maybe want delete custom item plugins and
                //it gives error like this

                //Better to do nothing
            }
            return;
        }

        //Get item used:
        //NEW feature in 2.00: Can get item from any LivingEntity, not just from Player
        LivingEntity entity = (LivingEntity) attacker;
        EntityEquipment equipment = entity.getEquipment(); //Get equipment
        ItemStack weapon = equipment.getItemInHand();
        CustomStack stack = null;
        try {
            stack = new CustomStack(weapon);
        } catch (NullItemException e) {
            return;
        }

        boolean isCustom = stack.isCustom();

        if ( isCustom ) {
            //Bukkit.getLogger().info("Debug: weapon is custom item");
            codeName = stack.getCodeName();
            //Bukkit.getLogger().info("Debug: codeName is " + codeName);
            try {
                new EventReflector(codeName, event, EntityDamageByEntityEvent.class, 
                        EventType.ACTIVE).call(); //Call event
            } catch (ItemNotFoundException e) {
                //Someone maybe want delete custom item plugins and
                //it gives error like this

                //Better to do nothing
            }
        }
    }

    /**
     * 
     * @param event
     * @throws NullItemException
     */
    @EventHandler
    public void onInteract(PlayerInteractEvent event) throws NullItemException {
        ItemStack item = event.getItem();
        CustomStack stack = new CustomStack(item);
        
        boolean isCustom = stack.isCustom();

        if ( isCustom ) {
            String codeName = stack.getCodeName();
            try {
                new EventReflector(codeName, event, PlayerInteractEvent.class, 
                        EventType.ACTIVE).call(); //Call event
            } catch (ItemNotFoundException e) {
                //Someone maybe want delete custom item plugins and
                //it gives error like this

                //Better to do nothing
            }
        }
    }

    /**
     * 
     * @param event
     */
    @EventHandler
    public void onInteractEntity(PlayerInteractEntityEvent event) {
        ItemStack item = event.getPlayer().getItemInHand();
        CustomStack stack = null;
        try {
            stack = new CustomStack(item);
        } catch (NullItemException e) {
            return;
        }

        boolean isCustom = stack.isCustom();

        if ( isCustom ) {
            String codeName = null;
            codeName = stack.getCodeName();
            try {
                new EventReflector(codeName, event, PlayerInteractEntityEvent.class, 
                        EventType.ACTIVE).call(); //Call event
            } catch (ItemNotFoundException e) {
                //Someone maybe want delete custom item plugins and
                //it gives error like this

                //Better to do nothing
            }
        }
    }

    /**
     * 
     * @param event
     * @throws NullItemException
     */
    @EventHandler
    public void onConsume(PlayerItemConsumeEvent event) throws NullItemException {
        ItemStack item = event.getItem();
        CustomStack stack = null;
        try {
            stack = new CustomStack(item);
        } catch (NullItemException e) {
            return;
        }

        boolean isCustom = stack.isCustom();

        if ( isCustom ) {
            String codeName = stack.getCodeName();
            try {
                new EventReflector(codeName, event, PlayerItemConsumeEvent.class, 
                        EventType.ACTIVE).call(); //Call event
            } catch (ItemNotFoundException e) {
                //Someone maybe want delete custom item plugins and
                //it gives error like this

                //Better to do nothing
            }
        }
    }
    
    /**
     * 
     * @param event
     */
    @EventHandler
    public void onClickInInventory(InventoryClickEvent event) {
        ItemStack item = event.getCurrentItem();
        CustomStack stack = null;
        try {
            stack = new CustomStack(item);
        } catch (NullItemException e) {
            return;
        }

        boolean isCustom = stack.isCustom();

        if ( isCustom ) {
            String codeName = stack.getCodeName();
            try {
                new EventReflector(codeName, event, InventoryClickEvent.class, 
                        EventType.ACTIVE).call(); //Call event
            } catch (ItemNotFoundException e) {
                //Someone maybe want delete custom item plugins and
                //it gives error like this

                //Better to do nothing
            }
        }
    }
    
    /**
     * 
     * @param event
     */
    @EventHandler
    public void onDropByPlayer(PlayerDropItemEvent event) {
        ItemStack item = event.getItemDrop().getItemStack();
        CustomStack stack = null;
        try {
            stack = new CustomStack(item);
        } catch (NullItemException e) {
            return;
        }

        boolean isCustom = stack.isCustom();

        if ( isCustom ) {
            String codeName = stack.getCodeName();
            try {
                new EventReflector(codeName, event, PlayerDropItemEvent.class, 
                        EventType.ACTIVE).call(); //Call event
            } catch (ItemNotFoundException e) {
                //Someone maybe want delete custom item plugins and
                //it gives error like this

                //Better to do nothing
            }
        }
    }
}
