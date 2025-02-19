package com.beanfeed.regexRails;

import com.beanfeed.regexRails.events.DetectorRailListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class RegexRails extends JavaPlugin {
    private DetectorRailListener minecartDetector;

    @Override
    public void onEnable() {
        minecartDetector = new DetectorRailListener(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        minecartDetector = null;
    }
}
