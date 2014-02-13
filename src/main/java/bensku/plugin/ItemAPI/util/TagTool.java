package bensku.plugin.ItemAPI.util;

import org.jsoup.Jsoup;

public class TagTool {
    /**
     * Returns tag content
     * @param tag
     * @param content
     * @return tag content
     */
    public static String getTag(String tag, String content) {
        String result = Jsoup.parse(content).select(tag).first().text();
        return result;
    }
    /**
     * Creates new tag and outputs data with it
     * @param data
     * @param tag
     * @param content
     * @return new data
     */
    public static String newTag(String data, String tag, String content) {
        String newData = data + "<" + tag + ">" + content + "</" + tag + ">";
        return newData;
        
    }
}
