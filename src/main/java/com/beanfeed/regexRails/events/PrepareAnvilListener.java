package com.beanfeed.regexRails.events;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class PrepareAnvilListener implements Listener {

    public PrepareAnvilListener(Plugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPrepareAnvil(PrepareAnvilEvent event) {
        ItemStack item = event.getInventory().getItem(0);
        if (item != null && item.getType().toString().contains("MINECART")) {
            event.getView().setRepairCost(0);
        }
    }
}
