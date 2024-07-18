// This is free and unencumbered software released into the public domain.
// Authors: christianniehaus, NotAlexNoyle.
package me.barny1094875.utilitiesog.commands;

// Import libraries.
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.barny1094875.utilitiesog.internal.Utils;

// Declare the /bing command in the Ping Module with Bukkit Commands.
public class BingCommand implements CommandExecutor {

	// Command execution event handler using Bukkit's CommandManager.
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		// Store the required permission for the /ping command for later reference.
		String pingPermission = "utilities.ping";

		// If the command was sent by an in-game player, do this...
		if (sender instanceof Player) {

			// Get the player object from the sender.
			Player player = (Player) sender;

			// If the player has permission to use the Ping Module, do this...
			if (player.hasPermission(pingPermission)) {

				// Send player a connection confirmation message with formatting using the TrueOG Message API.
				Utils.trueogMessage(player, "<#FFFFFF> Bong!");

			}
			// If the player does not have permission to use the Ping Module, do this...
			else {

				// Send a detailed, formatted permissions error message to the player.
				Utils.permissionsErrorMessage(player, cmd.getName(), pingPermission);

			}

		}
		// If the command was sent by the server console, do this...
		else {

			// Send a detailed error message to the server console.
			Utils.consoleUseErrorMessage(sender, cmd.getName(), pingPermission);

		}

		// Healthy exit status.
		return true;

	}

}