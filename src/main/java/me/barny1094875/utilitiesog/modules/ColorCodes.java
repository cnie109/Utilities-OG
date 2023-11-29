package me.barny1094875.utilitiesog.modules;

import me.barny1094875.utilitiesog.Utilities_OG;
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
//                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&l-=-=-=-=- &5&lC&3&lo&a&ll&e&lo&c&lr &B&lC&d&lo&f&ld&d&le&B&ls: &b&l-=-=-=-=-"));
//                player.sendMessage("&0 " + ChatColor.translateAlternateColorCodes('&', "&f= &0Black               &r") + "&1 " + ChatColor.translateAlternateColorCodes('&', "&f= &1Dark Blue"));
//                player.sendMessage("&2 " + ChatColor.translateAlternateColorCodes('&', "&f= &2Dark Green       &r") + "&3 " + ChatColor.translateAlternateColorCodes('&', "&f= &3Dark Aqua"));
//                player.sendMessage("&4 " + ChatColor.translateAlternateColorCodes('&', "&f= &4Dark Red          &r") + "&5 " + ChatColor.translateAlternateColorCodes('&', "&f= &5Dark Purple"));
//                player.sendMessage("&6 " + ChatColor.translateAlternateColorCodes('&', "&f= &6Gold                &r") + "&7 " + ChatColor.translateAlternateColorCodes('&', "&f= &7Grey"));
//                player.sendMessage("&8 " + ChatColor.translateAlternateColorCodes('&', "&f= &8Dark Grey         &r") + "&9 " + ChatColor.translateAlternateColorCodes('&', "&f= &9Blue"));
//                player.sendMessage("&a " + ChatColor.translateAlternateColorCodes('&', "&f= &aGreen              &r") + "&b " + ChatColor.translateAlternateColorCodes('&', "&f= &bAqua"));
//                player.sendMessage("&c " + ChatColor.translateAlternateColorCodes('&', "&f= &cRed                 &r") + "&d " + ChatColor.translateAlternateColorCodes('&', "&f= &dLight Purple"));
//                player.sendMessage("&e " + ChatColor.translateAlternateColorCodes('&', "&f= &eYellow              &r") + "&f " + ChatColor.translateAlternateColorCodes('&', "&f= &fWhite"));
//                player.sendMessage("&k " + ChatColor.translateAlternateColorCodes('&', "&f= &kMagic               &r") + "&l " + ChatColor.translateAlternateColorCodes('&', " &f= &lBold"));
//                player.sendMessage("&m " + ChatColor.translateAlternateColorCodes('&', "&f= &mStrikethrough&r    ") + "&n " + ChatColor.translateAlternateColorCodes('&', "&f= &nUnderline"));
//                player.sendMessage("&o " + ChatColor.translateAlternateColorCodes('&', "&f= &oItalic               &r") + "&r " + ChatColor.translateAlternateColorCodes('&', "&f= &rReset"));
                player.sendMessage(Utilities_OG.getMM().deserialize("<dark_aqua><bold>-=-=-=-=- <dark_purple>C<dark_aqua>o<green>l<yellow>o<red>r <aqua>C<light_purple>o<white>d<light_purple>e<aqua>s: -=-=-=-=-"));
                player.sendMessage(Utilities_OG.getMM().deserialize("&0 = <black>Black               <reset> &1 = <dark_blue>Dark Blue"));
                player.sendMessage(Utilities_OG.getMM().deserialize("&2 = <dark_green>Dark Green       <reset> &3 = <dark_aqua>Dark Aqua"));
                player.sendMessage(Utilities_OG.getMM().deserialize("&4 = <dark_red>Dark Red          <reset> &5 = <dark_purple>Dark Purple"));
                player.sendMessage(Utilities_OG.getMM().deserialize("&6 = <gold>Gold                <reset> &7 = <gray>Grey"));
                player.sendMessage(Utilities_OG.getMM().deserialize("&8 = <dark_gray>Dark Grey         <reset> &9 = <blue>Blue"));
                player.sendMessage(Utilities_OG.getMM().deserialize("&a = <green>Green              <reset> &b = <aqua>Aqua"));
                player.sendMessage(Utilities_OG.getMM().deserialize("&c = <red>Red                 <reset> &d = <light_purple>Light Purple"));
                player.sendMessage(Utilities_OG.getMM().deserialize("&e = <yellow>Yellow              <reset> &f = <white>White"));
                player.sendMessage(Utilities_OG.getMM().deserialize("&k = <obfuscated>Magic               <reset> &l = <bold>Bold"));
                player.sendMessage(Utilities_OG.getMM().deserialize("&m = <strikethrough>Strikethrough <reset>    &n = <underlined>Underline"));
                player.sendMessage(Utilities_OG.getMM().deserialize("&o = <italic>Italic               <reset> &r = Reset"));
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
