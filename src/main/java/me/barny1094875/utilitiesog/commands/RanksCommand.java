// This is free and unencumbered software released into the public domain.
// Authors: christianniehaus, NotAlexNoyle.
package me.barny1094875.utilitiesog.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.barny1094875.utilitiesog.internal.Utils;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.minimessage.MiniMessage;

// Declare the Ranks Module with Bukkit Commands.
public class RanksCommand implements CommandExecutor {

	// Command execution event handler using Bukkit's CommandManager.
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		// Store the required permission for the /ranks command for later reference.
		String ranksPermission = "utilities.ranks";

		// If the command was sent by an in-game player, do this...
		if (sender instanceof Player) {

			// Get the player object from the sender.
			Player player = (Player) sender;

			// If the player has permission to use /ranks, do this...
			if (player.hasPermission(ranksPermission)) {

				// If the player did not provide any arguments, do this...
				if (args.length == 0) {

					// Send the available ranks to the player, with clickable menus to get more information about each rank.
					Utils.trueogMessage(player, "<bold><dark_purple>-=-=-=-=- <dark_green>True<dark_red>OG <aqua>Donation Store: <dark_purple>-=-=-=-=-");
					Utils.trueogMessage(player, "");
					Utils.trueogMessage(player, "Each donor rank requires the rank before it.");
					Utils.trueogMessage(player, "");
					Utils.trueogMessage(player, "<green>Rank Overview: <dark_aqua><underlined>https://shop.true-og.net");
					Utils.trueogMessage(player, "");
					Utils.trueogMessage(player, "<white>[OG] <gray>(<dark_green>$10 Once<gray>) <dark_aqua><underlined>https://true-og.net/og");
					sendClickableMessage(player, "/ranks og");
					Utils.trueogMessage(player, "");
					Utils.trueogMessage(player,"<red>[OG Pro] <gray>(<dark_green>$50 Once<gray>) <dark_aqua><underlined>https://true-og.net/og-pro");
					sendClickableMessage(player, "/ranks og-pro");
					Utils.trueogMessage(player, "");
					Utils.trueogMessage(player, "<dark_red><bold>[OG Master] <gray>(<gold>$10 Monthly<gray>) <dark_aqua><underlined>https://true-og.net/og-master");
					sendClickableMessage(player, "/ranks og-master");

				}
				// If the player provided one or more arguments, do this...
				else if (args.length == 1) {

					// If the "OG" message was clicked, do this...
					if (args[0].equalsIgnoreCase("og")) {

						// Share "OG" benefits with the player with formatting using the TrueOG Message API.
						Utils.trueogMessage(player, "<dark_purple><bold>-=-=-=-=- <dark_green>True<dark_red>OG <white>OG Rank: <dark_purple>-=-=-=-=-");
						Utils.trueogMessage(player, "- Use /afk for an unlimited amount of time.");
						Utils.trueogMessage(player, "- Prominent white chat prefix");
						Utils.trueogMessage(player, "- Create unions with /union create.");
						Utils.trueogMessage(player, "- Invite people to unions with /union invite.");
						Utils.trueogMessage(player, "- Access to #donor-chat on Discord.");
						Utils.trueogMessage(player, "- 6 homes (temporary benefit until the implementation of our in-game home earning plugin).");
						Utils.trueogMessage(player, "- &lMore TBA&r.");

					}
					// If the "OG Pro" message was clicked, do this...
					else if (args[0].equalsIgnoreCase("og-pro")) {

						// Share "OG Pro" benefits with the player with formatting using the TrueOG Message API.
						Utils.trueogMessage(player, "<dark_purple><bold>-=-=-=-=- <dark_green>True&<dark_red>OG <red>OG Pro Rank: <dark_purple>-=-=-=-=-");
						Utils.trueogMessage(player, "- &lAll OG Benefits&r.");
						Utils.trueogMessage(player, "- Rent up to 2 shops at the player market.");
						Utils.trueogMessage(player, "- Special chat formatting (bold, italics, magic, etc).");
						Utils.trueogMessage(player, "- Use color codes in chat, mail, unions, signs, anvils, and book titles.");
						Utils.trueogMessage(player, "- Edit written books with /book.");
						Utils.trueogMessage(player, "- Edit signs with /editsign.");
						Utils.trueogMessage(player, "- Wear hats with /hat.");
						Utils.trueogMessage(player, "- Sit anywhere with /sit.");
						Utils.trueogMessage(player, "- Access your ender chest anywhere with /echest.");
						Utils.trueogMessage(player, "- Use 9 exclusive particle cosmetics with /cosmetics (up to 3 at once).");
						Utils.trueogMessage(player, "- &lMore TBA&r.");

					}
					// If the "OG Master" message was clicked, do this...
					else if (args[0].equalsIgnoreCase("og-master")) {

						// Share "OG Master" benefits with the player with formatting using the TrueOG Message API.
						Utils.trueogMessage(player, "<dark_purple><bold>-=-=-=-=- <dark_green>True<dark_red>OG <dark_red>OG Master Rank: <dark_purple>-=-=-=-=-");
						Utils.trueogMessage(player, "- &lAll OG and OG Pro Benefits&r.");
						Utils.trueogMessage(player, "- Rent up to 3 shops at the player market.");
						Utils.trueogMessage(player, "- &lWorld inspection &rwith /co inspect.");
						Utils.trueogMessage(player, "- Return to last teleport destination with /back.");
						Utils.trueogMessage(player, "- Toggle your death coordinates in chat with /tdc.");
						Utils.trueogMessage(player, "- Special mail, sign, and anvil formatting (strike-through, italics, magic, etc).");
						Utils.trueogMessage(player, "- &lMore TBA&r.");

					}

				}
				// If the player specified too many command arguments, do this...
				else {

					// Send the player a formatted error message using the TrueOG Message API.
					Utils.trueogMessage(player, "<gray>[<dark_green>True<dark_red>OG<gray>] <red>ERROR: Too many arguments! <gold>Syntax: <yellow>/ranks");

				}

			}
			// If the player does not have permission to use /ranks, do this...
			else {

				// Send a detailed, formatted permissions error message to the player.
				Utils.permissionsErrorMessage(player, cmd.getName(), ranksPermission);

			}

		}
		// If the command was sent by the server console, do this...
		else {

			// Send a detailed error message to the server console.
			Utils.consoleUseErrorMessage(sender, cmd.getName(), ranksPermission);

		}

		// Healthy exit status.
		return true;

	}

	// Turns chat text into a command button.
	public static void sendClickableMessage(Player player, String command) {

		// Declare a TextComponent with pre-formatted contents using standard MiniMessage APi.
		TextComponent clickableText = (TextComponent) MiniMessage.miniMessage().deserialize("<aqua><bold>Click here to see the benefits!");

		// Create a click event on the chat text.
		clickableText = clickableText.clickEvent(ClickEvent.clickEvent(ClickEvent.Action.RUN_COMMAND, command));

		// Send the clickable text to the player.
		player.sendMessage(clickableText);

	}

}