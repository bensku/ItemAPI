package bensku.plugin.ItemAPI.main;

public interface CustomItem {
    public String getCodeName();
    public String getDisplayName();
    public void onHit();
    public void onBlockBreak();
    public void onConsume();
    public void onRightClick();
    public void onLeftClick();
}
