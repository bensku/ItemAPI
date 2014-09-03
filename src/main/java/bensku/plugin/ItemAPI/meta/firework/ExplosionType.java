package bensku.plugin.ItemAPI.meta.firework;

import org.bukkit.FireworkEffect.Type;

/**
 * A better firework explosion type handler. It can give the value used in nbt and
 * may created from Bukkit's FireworkEffect.Type
 * @author bensku
 *
 */
public enum ExplosionType {
    BALL,
    BALL_LARGE,
    STAR,
    CREEPER,
    BURST,
    UNKNOWN;
    
    /**
     * Gets instance of ExplosionType using give Bukkit's one.
     * @param type
     * @return corresponding ExplosionType
     */
    public static ExplosionType fromBukkitType(Type type) {
        switch (type) {
            case BALL:
                return ExplosionType.BALL;
            case BALL_LARGE:
                return ExplosionType.BALL_LARGE;
            case STAR:
                return ExplosionType.STAR;
            case CREEPER:
                return ExplosionType.CREEPER;
            case BURST:
                return ExplosionType.BURST;
        }
        //This shouldn't happen, but we also shouldn't return null when it can avoided
        return ExplosionType.UNKNOWN;
    }
    
    /**
     * Gets firework explosion type as int for NBT. It actually only calls
     * this.ordinal(), but don't try it to Bukkit's one. In Type, the CREEPER and BURST
     * have switched places!
     * @return correct NBT int for this explosion type
     */
    public int getNbtValue() {
        return this.ordinal();
    }
}
