package bensku.plugin.ItemAPI.test;

import org.bukkit.ChatColor;

import bensku.plugin.ItemAPI.main.CustomItem;

public class TestWand implements CustomItem {

    @Override
    public String getCodeName() {
        // TODO Auto-generated method stub
        return "testWand";
    }

    @Override
    public String getDisplayName() {
        // TODO Auto-generated method stub
        return ChatColor.AQUA + "Staff of testing";
    }

    @Override
    public void onHit() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onBlockBreak() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onConsume() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onRightClick() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onLeftClick() {
        // TODO Auto-generated method stub
        
    }

}
