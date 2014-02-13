package bensku.plugin.ItemAPI.util;

import org.bukkit.ChatColor;

public class ColorTags {
    /**
     * Returns color tags as color codes
     * @param newMessage
     * @return
     */
    public static String tagsToColors(String message) {
        //Add reset to start of newMessage
        String newMessage = "<reset>" + message;
        
        newMessage = newMessage.replace("<aqua>", "" + ChatColor.AQUA);
        newMessage = newMessage.replace("<black>", "" + ChatColor.BLACK);
        
        newMessage = newMessage.replace("<blue>", "" + ChatColor.BLUE);
        
        newMessage = newMessage.replace("<b>", "" + ChatColor.BOLD);
        newMessage = newMessage.replace("<bold>", "" + ChatColor.BOLD);
        
        newMessage = newMessage.replace("<daqua>", "" + ChatColor.DARK_AQUA);
        
        newMessage = newMessage.replace("<dblue", "" + ChatColor.DARK_BLUE);
        
        newMessage = newMessage.replace("<dgray>", "" + ChatColor.DARK_GRAY);
        
        newMessage = newMessage.replace("<dgreen>", "" + ChatColor.DARK_GREEN);
        
        newMessage = newMessage.replace("<dpurple>", "" + ChatColor.DARK_PURPLE);
        
        newMessage = newMessage.replace("<dred>", "" + ChatColor.DARK_RED);
        
        newMessage = newMessage.replace("<gold>", "" + ChatColor.GOLD);
        
        newMessage = newMessage.replace("<gray>", "" + ChatColor.GRAY);
        
        newMessage = newMessage.replace("<green>", "" + ChatColor.GREEN);
        
        newMessage = newMessage.replace("<i>", "" + ChatColor.ITALIC);
        newMessage = newMessage.replace("<italic>", "" + ChatColor.ITALIC);
        
        newMessage = newMessage.replace("<lpurple>", "" + ChatColor.LIGHT_PURPLE);
        
        newMessage = newMessage.replace("<magic>", "" + ChatColor.MAGIC);
        newMessage = newMessage.replace("<random>", "" + ChatColor.MAGIC);
        
        newMessage = newMessage.replace("<red>", "" + ChatColor.RED);
        
        newMessage = newMessage.replace("<reset>", "" + ChatColor.RESET);
        newMessage = newMessage.replace("<r>", "" + ChatColor.RESET);
        
        newMessage = newMessage.replace("<s>", "" + ChatColor.STRIKETHROUGH);
        newMessage = newMessage.replace("<strikethrough>", "" + ChatColor.STRIKETHROUGH);
        
        newMessage = newMessage.replace("<u>", "" + ChatColor.UNDERLINE);
        newMessage = newMessage.replace("<underline>", "" + ChatColor.UNDERLINE);
        
        newMessage = newMessage.replace("<white>", "" + ChatColor.WHITE);
        
        newMessage = newMessage.replace("<yellow>", "" + ChatColor.YELLOW);
        
        return newMessage;
    }
}
