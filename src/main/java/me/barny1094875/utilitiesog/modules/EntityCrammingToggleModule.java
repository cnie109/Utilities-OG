// This is free and unencumbered software released into the public domain.
// Authors: christianniehaus, NotAlexNoyle.
package me.barny1094875.utilitiesog.modules;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

//Import required libraries.
import me.barny1094875.utilitiesog.Utilities_OG;
import net.kyori.adventure.text.Component;

public class EntityCrammingToggleModule implements CommandExecutor
{
	// Command execution event handler extending bukkit's CommandManager.
	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args)
	{
		if (sender instanceof Player)
		{
			// Get the player object from the sender.
			Player player = (Player) sender;
			// If the command runner has permission to toggle entity cramming, do this...
			if (player.hasPermission("utilities.togglecramming"))
			{
				// Fetch config file from data folder.  
				FileConfiguration config = Utilities_OG.getPlugin().getConfig();

				// Flip the true/false value to the opposite of what it currently is.
				config.set("doEntityCramming", ! config.getBoolean("doEntityCramming"));

				// Send formatted confirmation message using MiniMessage API.
				Component parsed = Utilities_OG.getMM().deserialize("<#AAAAAA>[<#00AA00>Utilities<#AA0000>-OG<#AAAAAA>] <#FFAA00>Entity cramming has been set to <#FFFF55>" + config.getBoolean("doEntityCramming") + "<#FFAA00>.");
				player.sendMessage(parsed);
			}
		}
		return true;
	}
}