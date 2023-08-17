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

// Initialize command manager class.
public class BingModule implements CommandExecutor
{

	// Command execution event handler extending bukkit's CommandManager.
	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args)
	{
		if (sender instanceof Player)
		{
			// Get the player object from the sender.
			Player player = (Player) sender;

			// Send player a connection confirmation message.
			Component parsed = Utilities_OG.getMM().deserialize("<#FFFFFF> Bong!");
			player.sendMessage(parsed);
		}
		// Healthy exit status.
		return true;
	}
}