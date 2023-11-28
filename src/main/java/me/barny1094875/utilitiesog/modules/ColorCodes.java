package me.barny1094875.utilitiesog.modules;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ColorCodes implements CommandExecutor {

    // Create instance of class for command runner.
    private static ColorCodes instance;

    // Constructor for main class to get instance from.
    public static ColorCodes getInstance() {

        return instance;

    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        // If the command matches our criteria, do this...
        if (cmd.getName().equalsIgnoreCase("colorcodes")) {

            // If the command executor is a player, do this...
            if (sender instanceof Player) {

                // Cast the sender to a player object safely.
                Player player = (Player) sender;

                // Send the color code cheat sheet to the player.
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&l-=-=-=-=- &5&lC&3&lo&a&ll&e&lo&c&lr &B&lC&d&lo&f&ld&d&le&B&ls: &b&l-=-=-=-=-"));
                player.sendMessage("&0 " + ChatColor.translateAlternateColorCodes('&', "&f= &0Black               &r") + "&1 " + ChatColor.translateAlternateColorCodes('&', "&f= &1Dark Blue"));
                player.sendMessage("&2 " + ChatColor.translateAlternateColorCodes('&', "&f= &2Dark Green       &r") + "&3 " + ChatColor.translateAlternateColorCodes('&', "&f= &3Dark Aqua"));
                player.sendMessage("&4 " + ChatColor.translateAlternateColorCodes('&', "&f= &4Dark Red          &r") + "&5 " + ChatColor.translateAlternateColorCodes('&', "&f= &5Dark Purple"));
                player.sendMessage("&6 " + ChatColor.translateAlternateColorCodes('&', "&f= &6Gold                &r") + "&7 " + ChatColor.translateAlternateColorCodes('&', "&f= &7Grey"));
                player.sendMessage("&8 " + ChatColor.translateAlternateColorCodes('&', "&f= &8Dark Grey         &r") + "&9 " + ChatColor.translateAlternateColorCodes('&', "&f= &9Blue"));
                player.sendMessage("&a " + ChatColor.translateAlternateColorCodes('&', "&f= &aGreen              &r") + "&b " + ChatColor.translateAlternateColorCodes('&', "&f= &bAqua"));
                player.sendMessage("&c " + ChatColor.translateAlternateColorCodes('&', "&f= &cRed                 &r") + "&d " + ChatColor.translateAlternateColorCodes('&', "&f= &dLight Purple"));
                player.sendMessage("&e " + ChatColor.translateAlternateColorCodes('&', "&f= &eYellow              &r") + "&f " + ChatColor.translateAlternateColorCodes('&', "&f= &fWhite"));
                player.sendMessage("&k " + ChatColor.translateAlternateColorCodes('&', "&f= &kMagic               &r") + "&l " + ChatColor.translateAlternateColorCodes('&', " &f= &lBold"));
                player.sendMessage("&m " + ChatColor.translateAlternateColorCodes('&', "&f= &mStrikethrough&r    ") + "&n " + ChatColor.translateAlternateColorCodes('&', "&f= &nUnderline"));
                player.sendMessage("&o " + ChatColor.translateAlternateColorCodes('&', "&f= &oItalic               &r") + "&r " + ChatColor.translateAlternateColorCodes('&', "&f= &rReset"));

            }
            // If the command executor is not a player, do this...
            else {

                // Deny console executions.
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "ERROR: The console cannot execute this command!"));

            }

        }

        // Healthy exit status.
        return true;

    }

}
