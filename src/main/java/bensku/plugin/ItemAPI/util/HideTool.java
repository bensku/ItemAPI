package bensku.plugin.ItemAPI.util;

import org.bukkit.ChatColor;

public class HideTool {
    /**
     * Hides String using Minecraft ChatColor
     * @param string
     * @return
     */
    public static String hideString(String string)
    {
        char[] data = new char[string.length() * 2];
        
        for(int i = 0; i < data.length; i += 2)
        {
            data[i] = ChatColor.COLOR_CHAR;
            data[i + 1] = string.charAt(i == 0 ? 0 : i / 2);
        }
        
        return new String(data);
    }
    
    /**
     * Decodes String back to readable
     * @param string
     * @return
     */
    public static String unhideString(String string)
    {
        return string.replace(String.valueOf(ChatColor.COLOR_CHAR), "");
    }
}
