package bensku.plugin.ItemAPI.util;

public class TagTool {
    /**
     * Returns tag content
     * @param tag
     * @param content
     * @return tag content
     */
    public static String getTag(String tag, String content) {
        String pattern = "(?i)(<" + tag + ">)(.+?)(</" + tag + ">)"; //Regex pattern
        String result = content.replaceAll(pattern, "$2");
        return result;
    }
    public static String newTag(String data, String tag, String content) {
        String newData = data + "<" + tag + ">" + content + "</" + tag + ">";
        return newData;
        
    }
}
