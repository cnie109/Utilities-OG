// This is free and unencumbered software released into the public domain.
// Authors: christianniehaus, NotAlexNoyle.
package me.barny1094875.utilitiesog.commands;

// Import libraries.
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.barny1094875.utilitiesog.internal.Utils;
import net.kyori.adventure.text.minimessage.MiniMessage;

// Declare the ColorCodes Module with Bukkit Commands.
public class ColorCodesCommand implements CommandExecutor {

	// Create an instance of the class for command runner.
	private static ColorCodesCommand instance;

	// Constructor for main class that returns itself.
	public static ColorCodesCommand getInstance() {

		// Forwards the class instance to the caller.
		return instance;

	}

	// Command execution event handler using Bukkit's CommandManager.
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		// Store the required permission for the /colorcodes command for later reference.
		String colorCodesPermission = "utilities.colorcodes";

		// If the command was sent by an in-game player, do this...
		if (sender instanceof Player) {

			// Cast the sender to a player object safely.
			Player player = (Player) sender;

			// If the player has permission to use /colorcodes, do this...
			if (player.hasPermission(colorCodesPermission)) {

				// Send the color code cheat sheet to the player.
				// Uses MiniMessage directly so that the legacy color codes in the key are not processed.
				player.sendMessage(MiniMessage.miniMessage().deserialize("<dark_aqua><bold>-=-=-=-=- <dark_purple>C<dark_aqua>o<green>l<yellow>o<red>r <aqua>C<light_purple>o<white>d<light_purple>e<aqua>s: -=-=-=-=-"));
				player.sendMessage(MiniMessage.miniMessage().deserialize("&0 = <black>Black               <reset> &1 = <dark_blue>Dark Blue"));
				player.sendMessage(MiniMessage.miniMessage().deserialize("&2 = <dark_green>Dark Green       <reset> &3 = <dark_aqua>Dark Aqua"));
				player.sendMessage(MiniMessage.miniMessage().deserialize("&4 = <dark_red>Dark Red          <reset> &5 = <dark_purple>Dark Purple"));
				player.sendMessage(MiniMessage.miniMessage().deserialize("&6 = <gold>Gold                <reset> &7 = <gray>Grey"));
				player.sendMessage(MiniMessage.miniMessage().deserialize("&8 = <dark_gray>Dark Grey         <reset> &9 = <blue>Blue"));
				player.sendMessage(MiniMessage.miniMessage().deserialize("&a = <green>Green              <reset> &b = <aqua>Aqua"));
				player.sendMessage(MiniMessage.miniMessage().deserialize("&c = <red>Red                 <reset> &d = <light_purple>Light Purple"));
				player.sendMessage(MiniMessage.miniMessage().deserialize("&e = <yellow>Yellow              <reset> &f = <white>White"));
				player.sendMessage(MiniMessage.miniMessage().deserialize("&k = <obfuscated>Magic               <reset> &l = <bold>Bold"));
				player.sendMessage(MiniMessage.miniMessage().deserialize("&m = <strikethrough>Strikethrough <reset>    &n = <underlined>Underline"));
				player.sendMessage(MiniMessage.miniMessage().deserialize("&o = <italic>Italic               <reset> &r = Reset"));

			}
			// If the player does not have permission to use /colorcodes, do this...
			else {

				// Send a detailed, formatted permissions error message to the player.
				Utils.permissionsErrorMessage(player, cmd.getName(), colorCodesPermission);

			}

		}
		// If the command was sent by the server console, do this...
		else {

			// Send a detailed error message to the server console.
			Utils.consoleUseErrorMessage(sender, cmd.getName(), colorCodesPermission);

		}

		// Healthy exit status.
		return true;

	}

}