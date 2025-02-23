package com.beanfeed.regexRails.events;

import com.google.common.collect.ImmutableList;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.block.data.AnaloguePowerable;
import org.bukkit.block.data.type.RedstoneWire;
import org.bukkit.block.sign.Side;
import org.bukkit.entity.Minecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.plugin.Plugin;

import java.util.logging.Logger;

public class DetectorRailListener implements Listener {
    private final Plugin plugin;
    private final Logger logger;

    public DetectorRailListener(Plugin plugin) {
        this.plugin = plugin;
        logger = plugin.getLogger();
        Bukkit.getPluginManager().registerEvents(this, plugin);
        //logger.info("DetectorRailListener registered");
    }

    @EventHandler
    public void onBlockRedstone(BlockRedstoneEvent event) {

        Block block = event.getBlock();

        if (block.getType() != Material.DETECTOR_RAIL) return;

        Minecart nearbyMinecart = getNearbyMinecart(block);

        if (nearbyMinecart != null && event.getNewCurrent() == 15) {
            handleDetection(nearbyMinecart, block, event);
        }
    }

    private Minecart getNearbyMinecart(Block rail) {
        Location loc = rail.getLocation();
        return (Minecart) rail.getWorld().getNearbyEntities(
                        loc.clone().add(0.5, 0.5, 0.5),
                        1.0, 1.0, 1.0
                ).stream()
                .filter(entity -> entity instanceof Minecart)
                .findFirst()
                .orElse(null);
    }

    private void handleDetection(Minecart minecart, Block rail, BlockRedstoneEvent event) {
        Sign sign = null;
        for (Faces face : Faces.values()) {
            Block adjacent = rail.getRelative(face.modX, face.modY, face.modZ);
            //logger.info("Adjacent block: " + adjacent.getType());
            if (adjacent.getState() instanceof Sign s) {
                sign = s;
                break;
            }
        }

        if(sign == null) return;

        String[] lines = sign.getSide(Side.FRONT).getLines();

        //Modes: "compare", "append", "set"
        String mode = lines[0].toLowerCase().trim();

        //logger.info("Mode: -" + mode + "-");

        if (mode.equals("compare")) {
            String compareType = lines[1];
            String value = lines[2];
            value = value.toLowerCase().trim();
            switch (compareType) {
                case "equals":
                    if(!minecart.getName().toLowerCase().trim().equals(value)) event.setNewCurrent(0);
                    break;
                case "contains":
                    if(!minecart.getName().toLowerCase().trim().contains(value)) event.setNewCurrent(0);
                    break;
                case "!equals":
                    if(minecart.getName().toLowerCase().trim().equals(value)) event.setNewCurrent(0);
                    break;
                case "!contains":
                    if(minecart.getName().toLowerCase().trim().contains(value)) event.setNewCurrent(0);
                    break;
                case "regex":
                    value = lines[2];
                    if(!minecart.getName().matches(value)) event.setNewCurrent(0);
                    break;
            }
        } else {
            boolean activated = false;
            for (Faces face : Faces.values()) {
                Block adjacent = sign.getBlock().getRelative(face.modX, face.modY, face.modZ);
                if(adjacent.getType() == Material.DETECTOR_RAIL) continue;
                if (adjacent.isBlockPowered() || adjacent.isBlockIndirectlyPowered()) {
                    activated = true;
                    break;
                }
            }
            if(!activated) return;

            if(lines[1].length() > 32) lines[1] = lines[1].substring(0, 32);
            switch (mode) {
                case "append":
                    minecart.setCustomName(minecart.getCustomName() + lines[1]);
                    break;
                case "set":
                    //logger.info("Setting minecart name to " + lines[1]);
                    minecart.setCustomName(lines[1]);
                    break;
            }
        }
    }
}
