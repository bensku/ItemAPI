package bensku.plugin.ItemAPI.util.restrict;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.World;

/**
 * Main world restriction object which can contain world blacklist, whitelist and 
 * restriction mode.
 * @author bensku
 * @since 2.03
 * @see WorldRestrictMode
 *
 */
public class WorldRestriction {
    private WorldRestrictMode mode;
    private List<World> whitelist;
    private List<World> blacklist;
    
    public WorldRestriction(WorldRestrictMode mode, List<World> whitelist, 
            List<World> blacklist) {
        this.mode = mode;
        this.whitelist = whitelist;
        this.blacklist = blacklist;
    }
    
    public WorldRestriction() {
        this(WorldRestrictMode.NONE, new ArrayList<World>(), new ArrayList<World>());
    }
    
    /**
     * Sets world restriction mode
     * @param mode
     */
    public void setMode(WorldRestrictMode mode) {
        this.mode = mode;
    }
    
    /**
     * 
     * @return current world restriction mode
     */
    public WorldRestrictMode getMode() {
        return this.mode;
    }
    
    public void setWhitelist(List<World> worlds) {
        this.whitelist = worlds;
    }
    
    public List<World> getWhitelist() {
        return this.whitelist;
    }
    
    public void addToWhitelist(World world) {
        this.whitelist.add(world);
    }
    
    public void removeFromWhitelist(World world) {
        this.whitelist.remove(world);
    }
    
    public void setBlacklist(List<World> worlds) {
        this.blacklist = worlds;
    }
    
    public List<World> getBlacklist() {
        return this.blacklist;
    }
    
    public void addToBlacklist(World world) {
        this.blacklist.add(world);
    }
    
    public void removeFromBlacklist(World world) {
        this.blacklist.remove(world);
    }
    
    /**
     * Checks is doing task is given world allowed.
     * @param world
     * @return boolean - is task allowed
     */
    public boolean isAllowed(World world) {
        WorldRestrictMode mode = this.getMode();
        if ( mode.equals(WorldRestrictMode.NONE) ) {
            return true;
        } else if ( mode.equals(WorldRestrictMode.ALLOWING) ) {
            if ( !this.getBlacklist().contains(world) ) {
                return true;
            }
        } else if ( mode.equals(WorldRestrictMode.DENYING) ) {
            if ( this.getWhitelist().contains(world) ) {
                return true;
            }
        }
        
        return false;
    }
}
