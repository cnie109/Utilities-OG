// This is free and unencumbered software released into the public domain.
// Authors: christianniehaus, NotAlexNoyle.
package me.barny1094875.utilitiesog.modules;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import me.barny1094875.utilitiesog.Utilities_OG;
import net.kyori.adventure.text.Component;

public class AboutModule implements CommandExecutor
{
	// Command execution event handler extending bukkit's CommandManager.
	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args)
	{
		if (sender instanceof Player)
		{
			// Get the player object from the sender.
			Player player = (Player) sender;

			// Send formatted about message using MiniMessage API.
			Component parsed = Utilities_OG.getMM().deserialize("<#AAAAAA>[<#00AA00>Utilities<#AA0000>-OG<#AAAAAA>] <#FFAA00>A collection of small utilities used by <#00AA00>True<#AA0000>OG<#FFAA00> <#FFFF55>Network<#FFAA00>. Developed by <#FFFF55>christianniehaus <#FFAA00>& <#FFFF55>NotAlexNoyle<#FFAA00>.");
			player.sendMessage(parsed);
		}
		else
		{
			sender.sendMessage("ERROR: The console cannot execute this command! Join the server in-game and try again.");
		}
		// Healthy exit status.
		return true;
	}
}