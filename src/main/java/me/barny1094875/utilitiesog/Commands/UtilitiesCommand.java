// This is free and unencumbered software released into the public domain.
// Authors: christianniehaus, NotAlexNoyle.
package me.barny1094875.utilitiesog.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

// Import required libraries.
import me.barny1094875.utilitiesog.Utilities_OG;
import net.kyori.adventure.text.Component;

public class UtilitiesCommand implements CommandExecutor
{

	// Command execution event handler extending bukkit's CommandManager.
	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args)
	{

		String commandContent = command.getName();

		if (commandContent.equalsIgnoreCase("utilities"))
		{
			// Prevent running from console and avoid NullPointerException if no arguments are provided.
			if(sender instanceof Player && args.length > 0)
			{
				if (args[0].equalsIgnoreCase("togglephantoms"))
				{
					if (Utilities_OG.config().getBoolean("PhantomToggle"))
					{
						PhantomToggleCommand.run(sender);
					}
				}
				else if (args[0].equalsIgnoreCase("togglecramming"))
				{
					if (Utilities_OG.config().getBoolean("EntityCrammingDisable"))
					{
						EntityCrammingToggleCommand.run(sender);
					}
				}
			}
			else
			{
				// Send formatted about message using MiniMessage API.
				Component parsed = Utilities_OG.getMM().deserialize("<#AAAAAA>[<#00AA00>Utilities<#AA0000>-OG<#AAAAAA>] <#FFAA00>A collection of small utilities used by <#00AA00>True<#AA0000>OG<#FFAA00> <#FFFF55>Network<#FFAA00>. Developed by <#FFFF55>christianniehaus <#FFAA00>& <#FFFF55>NotAlexNoyle<#FFAA00>.");
				sender.sendMessage(parsed);
			}
		}

		return true;
	}

}