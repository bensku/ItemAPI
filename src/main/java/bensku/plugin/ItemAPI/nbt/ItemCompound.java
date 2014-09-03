package bensku.plugin.ItemAPI.nbt;

import org.bukkit.inventory.ItemStack;

import com.comphenix.attribute.NbtFactory;
import com.comphenix.attribute.NbtFactory.NbtCompound;

import bensku.plugin.ItemAPI.main.ItemAPI;

/**
 * Simple item NBT wrapper with NbtFactory support. ProtocolLib support coming soon.
 * @author bensku
 * @since 2.03
 *
 */
public class ItemCompound {
    private ItemStack stack;
    private ItemAPI api;
    private String nbtLib;
    
    public ItemCompound(ItemStack stack) {
        this.stack = stack;
        this.api = ItemAPI.getAPI();
        this.nbtLib = this.api.getConfig().getString("nbt-lib");
    }
    
    public Object get(String path) {
        switch (this.nbtLib) {
            case "ProtocolLib":
                
            default: //By default, use NbtFactory
                NbtCompound nbt = NbtFactory.fromItemTag(this.stack);
                return nbt.getPath(path);
        }
    }
    
    public void set(String path, Object value) {
        switch (this.nbtLib) {
            case "ProtocolLib":
                
            default: //By default, use NbtFactory
                NbtCompound nbt = NbtFactory.fromItemTag(this.stack);
                nbt.putPath(path, value);
        }
    }
}
