package com.beanfeed.regexRails.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class RenameCartCommand implements CommandExecutor, TabCompleter {
    public RenameCartCommand() {}
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player player && player.getVehicle() instanceof Minecart cart) {
            if(args[0] != null) {
                cart.setCustomName(args[0]);

            }
        } else {
            sender.sendMessage(ChatColor.RED + "You must be riding a minecart to use this command.\n/renamecart <name>");
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return List.of();

    }
}
