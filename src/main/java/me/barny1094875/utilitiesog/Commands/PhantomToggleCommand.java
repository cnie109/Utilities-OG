// This is free and unencumbered software released into the public domain.
// Authors: christianniehaus, NotAlexNoyle.
package me.barny1094875.utilitiesog.Commands;

// Import required libraries.
import me.barny1094875.utilitiesog.Utilities_OG;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class PhantomToggleCommand
{

	public static boolean run(@NotNull CommandSender sender)
	{

		// Store the contents of phantomDisabledUsers.yml file as a YAML object.
		YamlConfiguration phantomDisabledPlayers = Utilities_OG.getPhantomDisabledPlayers();

		// Cherry pick the command runner's phantom status from the YAML data set.
		boolean playerDisabled = phantomDisabledPlayers.getBoolean(((Player) sender).getUniqueId().toString());

		// Flip the true/false value to the opposite of what it currently is.
		phantomDisabledPlayers.set(((Player) sender).getUniqueId().toString(), ! playerDisabled);

		// If the command sender's phantom
		if(! playerDisabled)
		{

			// Send formatted "true" message using MiniMessage API.
			Component parsed = Utilities_OG.getMM().deserialize("<#AAAAAA>[<#00AA00>Utilities<#AA0000>-OG<#AAAAAA>] <#55FF55>Phantom spawning turned <#00AA00>ON<#55FF55>.");
			sender.sendMessage(parsed);

		}
		else
		{
			
			// Send formatted "false" message using MiniMessage API.
			Component parsed = Utilities_OG.getMM().deserialize("<#AAAAAA>[<#00AA00>Utilities<#AA0000>-OG<#AAAAAA>] <#FFAA00>Phantom spawning turned <#FF5555>OFF<#FFAA00>.");
			sender.sendMessage(parsed);
			
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

		return true;

	}

}