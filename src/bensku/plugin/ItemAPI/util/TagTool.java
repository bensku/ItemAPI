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
    public static String newTag(String data, String mainTag,
            String tag, String content) {
        String tagData = getTag(mainTag, data);
        tagData = tagData + "<" + tag + ">" + content + "</" + tag + ">";
        String[] dataParts = content.split("<" + mainTag + ">");
        String newData = dataParts[0] + "<" + mainTag + ">" + tagData + "</" 
                + mainTag + ">";
        return newData;
        
    }
    public static void editTag(String newData, String tag, String content) {
        String tagData = getTag(tag, newData);
        tagData = tagData + "<" + tag + ">" + content + "</" + tag + ">";
        
    }
}
