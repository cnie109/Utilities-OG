// This is free and unencumbered software released into the public domain.
// Authors: christianniehaus, NotAlexNoyle.
package me.barny1094875.utilitiesog.Commands;

//Import required libraries.
import me.barny1094875.utilitiesog.Utilities_OG;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

public class EntityCrammingToggleCommand
{

	public static boolean run(@NotNull CommandSender sender)
	{

		// If the command runner has permission to toggle entity cramming, do this...
		if (sender.hasPermission("util.candisablecramming"))
		{

			// Fetch config file from data folder.
			FileConfiguration config = Utilities_OG.getPlugin().getConfig();
			
			// Flip the true/false value to the opposite of what it currently is.
			config.set("doEntityCramming", ! config.getBoolean("doEntityCramming"));

			// Send formatted confirmation message using MiniMessage API.
			Component parsed = Utilities_OG.getMM().deserialize("<#AAAAAA>[<#00AA00>Utilities<#AA0000>-OG<#AAAAAA>] <#FFAA00>Entity cramming has been set to <#FFFF55>" + config.getBoolean("doEntityCramming") + "<#FFAA00>.");
			sender.sendMessage(parsed);

		}

		return true;

	}

}