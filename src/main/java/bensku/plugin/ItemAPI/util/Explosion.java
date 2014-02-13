package bensku.plugin.ItemAPI.util;

import org.bukkit.Location;
import org.bukkit.World;

/**
 * Fancy handler for explosions
 * @author bensku
 * @since 2.00
 *
 */
public class Explosion {
    private Location location;
    private float force;
    private boolean breakBlocks = true;
    private boolean setFire = false;
    
    public Explosion(Location location, int force) {
        
    }
    
    /**
     * Sets location of explosion
     * @param location
     */
    public void setLocation(Location location) {
        this.location = location;
    }
    
    /**
     * 
     * @return location of explosion
     */
    public Location getLocation() {
        return this.location;
    }
    
    /**
     * Sets force of explosion
     * @param force
     */
    public void setForce(float force) {
        this.force = force;
    }
    
    /**
     * 
     * @return force of explosion
     */
    public float getForce() {
        return this.force;
    }
    
    /**
     * Sets can explosion break blocks
     * @param breakBlocks
     */
    public void setBreakBlocks(boolean breakBlocks) {
        this.breakBlocks = breakBlocks;
    }
    
    /**
     * 
     * @return true if explosion can break blocks, false otherwise
     */
    public boolean canBreakBlocks() {
        return this.breakBlocks;
    }
    
    /**
     * Sets will explosion set blocks fire
     * @param setFire
     */
    public void setFire(boolean setFire) {
        this.setFire = setFire;
    }
    
    /**
     * 
     * @return will explosion set blocks fire 
     */
    public boolean isSetFire() {
        return this.setFire;
    }
    
    /**
     * Spawns explosion to location
     * @return true if explosion exploded successfully, false otherwise
     */
    public boolean spawn() {
        Location location = this.getLocation();
        World world = location.getWorld();
        
        //Spawn the explosion
        return world.createExplosion(location.getX(), location.getY(), location.getZ(), 
                this.getForce(), this.isSetFire(), this.canBreakBlocks());
    }
}
