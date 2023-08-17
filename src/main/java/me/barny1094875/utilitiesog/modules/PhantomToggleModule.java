// This is free and unencumbered software released into the public domain.
// Authors: christianniehaus, NotAlexNoyle.
package me.barny1094875.utilitiesog.modules;

import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

// Import required libraries.
import me.barny1094875.utilitiesog.Utilities_OG;
import net.kyori.adventure.text.Component;

public class PhantomToggleModule implements CommandExecutor
{
	// Command execution event handler extending bukkit's CommandManager.
	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args)
	{
		if (sender instanceof Player)
		{
			// Get the player object from the sender.
			Player player = (Player) sender;

			if (player.hasPermission("utilities.togglephantoms"))
			{
				// Store the contents of phantomDisabledUsers.yml file as a YAML object.
				YamlConfiguration phantomDisabledPlayers = Utilities_OG.getPhantomDisabledPlayers();

				// Cherry pick the command runner's phantom status from the YAML data set.
				boolean playerDisabled = phantomDisabledPlayers.getBoolean(((Player) player).getUniqueId().toString());

				// Flip the true/false value to the opposite of what it currently is.
				phantomDisabledPlayers.set(((Player) player).getUniqueId().toString(), ! playerDisabled);

				// If the command sender's phantom is not disabled, do this...
				if (! playerDisabled)
				{
					// Send formatted "true" message using MiniMessage API.
					Component parsed = Utilities_OG.getMM().deserialize("<#AAAAAA>[<#00AA00>Utilities<#AA0000>-OG<#AAAAAA>] <#55FF55>Phantom spawning turned <#00AA00>ON<#55FF55>.");
					player.sendMessage(parsed);
				}
				// If the command sender's phantom is disabled, do this...
				else
				{
					// Send formatted "false" message using MiniMessage API.
					Component parsed = Utilities_OG.getMM().deserialize("<#AAAAAA>[<#00AA00>Utilities<#AA0000>-OG<#AAAAAA>] <#FFAA00>Phantom spawning turned <#FF5555>OFF<#FFAA00>.");
					player.sendMessage(parsed);	
				}

				try
				{
					// Save changes to YAML data to disk.
					phantomDisabledPlayers.save(Utilities_OG.getPhantomDisabledPlayersFile());
				} catch (IOException error)
				{
					// Throw a runtime error if the YAML data could not be saved to disk.
					throw new RuntimeException(error);
				}
			}
		}
		else {
			sender.sendMessage("ERROR: The console cannot execute this command! Join the server in-game and try again.");
		}
		return true;
	}
}