package bensku.plugin.ItemAPI.listener;

import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;

import bensku.plugin.ItemAPI.api.CustomStack;
import bensku.plugin.ItemAPI.event.EventReflector;
import bensku.plugin.ItemAPI.exception.ItemNotFoundException;
import bensku.plugin.ItemAPI.exception.NullItemException;

/**
 * 
 * @author bensku
 * @since 2.00
 *
 */
public class CustomItemListener implements Listener {
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
            List<MetadataValue> metaData = attacker.getMetadata("ItemAPI:CodeName");
            codeName = metaData.get(0).asString();
            
            //So we try to find item which launched it
            
            try {
                new EventReflector(codeName, event, 
                        EntityDamageByEntityEvent.class).call(); //Call event
                //Bukkit.getLogger().info("Debug: EventReflector called succesfully");
            } catch (ItemNotFoundException e) {
                //Someone maybe want delete custom item plugins and
                //it gives error like this

                //Better to do nothing
            }
        }

        //Get item used:
        //NEW feature in 2.00: Can get item from any LivingEntity, not just from Player
        LivingEntity entity = (LivingEntity) attacker;
        EntityEquipment equipment = entity.getEquipment(); //Get equipment
        ItemStack weapon = equipment.getItemInHand();
        CustomStack stack = new CustomStack(weapon); //To CustomStack
        //Bukkit.getLogger().info("Debug: weapon is " + weapon.toString());

        boolean isCustom = false; //It actually isn't, but false don't trigger if statement
        try {
            isCustom = stack.isCustom(); //Now set real value of isCustom
            //Bukkit.getLogger().info("Debug: weapon isn't null");
        } catch (NullItemException e) {
            //And if there isn't item, its false anyway
        }

        if ( isCustom ) {
            //Bukkit.getLogger().info("Debug: weapon is custom item");
            codeName = stack.getCodeName();
            //Bukkit.getLogger().info("Debug: codeName is " + codeName);
            try {
                new EventReflector(codeName, event, 
                        EntityDamageByEntityEvent.class).call(); //Call event
                //Bukkit.getLogger().info("Debug: EventReflector called succesfully");
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

        boolean isCustom = false; //It actually isn't, but false don't trigger if statement
        try {
            isCustom = stack.isCustom(); //Now set real value of isCustom
        } catch (NullItemException e) {
            //And if there isn't item, its false anyway
        }

        if ( isCustom ) {
            String codeName = stack.getCodeName();
            try {
                new EventReflector(codeName, event, 
                        PlayerInteractEvent.class).call(); //Call event
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

        boolean isCustom = false; //It actually isn't, but false don't trigger if statement
        try {
            isCustom = stack.isCustom(); //Now set real value of isCustom
        } catch (NullItemException e) {
            //And if there isn't item, its false anyway
        }

        if ( isCustom ) {
            String codeName = null;
            try {
                codeName = stack.getCodeName();
            } catch (NullItemException e) {
                e.printStackTrace();
            }
            try {
                new EventReflector(codeName, event, 
                        PlayerInteractEntityEvent.class).call(); //Call event
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
        CustomStack stack = new CustomStack(item);

        boolean isCustom = false; //It actually isn't, but false don't trigger if statement
        try {
            isCustom = stack.isCustom(); //Now set real value of isCustom
        } catch (NullItemException e) {
            //And if there isn't item, its false anyway
        }

        if ( isCustom ) {
            String codeName = stack.getCodeName();
            try {
                new EventReflector(codeName, event, 
                        PlayerItemConsumeEvent.class).call(); //Call event
            } catch (ItemNotFoundException e) {
                //Someone maybe want delete custom item plugins and
                //it gives error like this

                //Better to do nothing
            }
        }
    }
}
