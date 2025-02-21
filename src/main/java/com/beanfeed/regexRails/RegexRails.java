package com.beanfeed.regexRails;

import com.beanfeed.regexRails.commands.RenameCartCommand;
import com.beanfeed.regexRails.events.DetectorRailListener;
import com.beanfeed.regexRails.events.PrepareAnvilListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class RegexRails extends JavaPlugin {
    private DetectorRailListener minecartDetector;
    private PrepareAnvilListener anvilListener;

    @Override
    public void onEnable() {
        minecartDetector = new DetectorRailListener(this);
        anvilListener = new PrepareAnvilListener(this);
        getCommand("renamecart").setExecutor(new RenameCartCommand());
        getCommand("renamecart").setTabCompleter(new RenameCartCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        minecartDetector = null;
        anvilListener = null;
    }
}
