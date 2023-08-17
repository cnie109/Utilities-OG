// This is free and unencumbered software released into the public domain.
// Authors: christianniehaus, NotAlexNoyle.
package me.barny1094875.utilitiesog.modules;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import me.barny1094875.utilitiesog.Utilities_OG;
import net.kyori.adventure.text.Component;

// Initialize command manager class.
public class PingModule implements CommandExecutor
{

	// Command execution event handler extending bukkit's CommandManager.
	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args)
	{

		if (sender instanceof Player)
		{

			// Get the player object from the sender.
			Player player = (Player) sender;

			if (player.hasPermission("utilities.ping"))
			{

				// If the player did not provide any arguments, do this...
				if(args.length == 0) {

					// Send player their own ping using a formatted message with the MiniMessage API.
					Component parsed = Utilities_OG.getMM().deserialize("<#AAAAAA>[<#00AA00>Utilities<#AA0000>-OG<#AAAAAA>] <#00AA00>Your current ping is: <#FFAA00>" + player.getPing() + "<#00AA00>.");
					player.sendMessage(parsed);

				}
				// If the player provided one or more arguments, do this...
				else if(args.length == 1) {

					// Try to get the player that was specified.
					Player target = Bukkit.getPlayer(args[0]);

					// If the provided argument was a valid player, do this...
					if(target != null) {

						// Send player their specified player's ping using formatting with the MiniMessage API.
						Component parsed = Utilities_OG.getMM().deserialize("<#AAAAAA>[<#00AA00>Utilities<#AA0000>-OG<#AAAAAA>] <#00AA00>" + target.getName() + "'s current ping is: <#FFAA00>" + target.getPing() + "<#00AA00>.");
						player.sendMessage(parsed);

					}
					// If the provided argument was not a valid player, do this...
					else {

						// Send a "player not found" error message using formatting with the MiniMessage API.
						Component parsed = Utilities_OG.getMM().deserialize("<#AAAAAA>[<#00AA00>Utilities<#AA0000>-OG<#AAAAAA>] <#AA0000> ERROR: The player " + args[0] + " was not found!");
						player.sendMessage(parsed);

					}

				}
				// If there is more than one provided argument, do this...
				else {

					// Send a "too many arguments" error message using formatting with the MiniMessage API.
					Component parsed = Utilities_OG.getMM().deserialize("<#AAAAAA>[<#00AA00>Utilities<#AA0000>-OG<#AAAAAA>] <#AA0000> ERROR: Too many arguments provided. Syntax: <#FFFF55>/ping <player>");
					player.sendMessage(parsed);

				}

			}

		}

		// Healthy exit status.
		return true;

	}
}