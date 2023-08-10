package me.barny1094875.utilitiesog.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

// Initialize command manager class.
public class BuyCommand implements CommandExecutor {

	// Create instance of class for command runner.
	private static BuyCommand instance;

	// Constructor for main class to get instance from.
	public static BuyCommand getInstance() {

		return instance;

	}

	// Declare a new command.
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		// If the command matches our criteria, do this...
		if (cmd.getName().equalsIgnoreCase("buy")) {

			// If the command executor is a player, do this...
			if (sender instanceof Player) {

				// Cast the sender to a player object safely.
				Player player = (Player) sender;

				// If the player did not provide any arguments, do this...
				if (args.length == 0) {

					// Send the available ranks to the user.
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&5&l-=-=-=-=- &2&lTrue&4&lOG &B&lDonation Store: &5&l-=-=-=-=-"));
					player.sendMessage("");
					player.sendMessage("Each donor rank requires the rank before it.");
					player.sendMessage("");
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aRank Overview: &3&nhttps://shop.true-og.net"));
					player.sendMessage("");
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f[OG] &7(&2$10 Once&7) &3&nhttps://true-og.net/og"));
					sendClickableMessage(player, "/buy og");
					player.sendMessage("");
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c[OG Pro] &7(&2$50 Once&7) &3&nhttps://true-og.net/og-pro"));
					sendClickableMessage(player, "/buy og-pro");
					player.sendMessage("");
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&l[OG Master] &7(&6$10 Monthly&7) &3&nhttps://true-og.net/og-master"));					
					sendClickableMessage(player, "/buy og-master");

				}
				// If the player provided one or more arguments, do this...
				else {

					// If the OG text was clicked, do this...
					if (args[0].equalsIgnoreCase("og")) {

						// Share the OG benefits with the user.
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&5&l-=-=-=-=- &2&lTrue&4&lOG &f&lOG Rank: &5&l-=-=-=-=-"));
						player.sendMessage("- Use /afk for an unlimited amount of time.");
						player.sendMessage("- Prominent white chat prefix");
						player.sendMessage("- Create unions with /union create.");
						player.sendMessage("- Invite people to unions with /union invite.");
						player.sendMessage("- Access to #donor-chat on Discord.");
						player.sendMessage("- 6 homes (temporary benefit until the implementation of our in-game home earning plugin).");
						player.sendMessage("- More TBA.");

					}
					// If the OG Pro text was clicked, do this...
					else if (args[0].equalsIgnoreCase("og-pro")) {

						// Share the OG Pro benefits with the user.
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&5&l-=-=-=-=- &2&lTrue&4&lOG &cOG Pro Rank: &5&l-=-=-=-=-"));
						player.sendMessage("- All OG Benefits.");
						player.sendMessage("- Rent up to 2 shops at the player market.");
						player.sendMessage("- Special chat formatting (bold, italics, magic, etc).");
						player.sendMessage("- Use color codes in chat, mail, unions, signs, anvils, and book titles.");
						player.sendMessage("- Edit written books with /book.");
						player.sendMessage("- Edit signs with /editsign.");
						player.sendMessage("- Wear hats with /hat.");
						player.sendMessage("- Sit anywhere with /sit.");
						player.sendMessage("- Access your ender chest anywhere with /echest.");
						player.sendMessage("- Use 9 exclusive particle cosmetics with /cosmetics (up to 3 at once).");
						player.sendMessage("- More TBA.");

					}
					// If the OG Master text was clicked, do this...
					else if (args[0].equalsIgnoreCase("og-master")) {

						// Share the OG Master benefits to the user.
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&5&l-=-=-=-=- &2&lTrue&4&lOG &4&lOG Master Rank: &5&l-=-=-=-=-"));
						player.sendMessage("- All OG and OG Pro Benefits.");
						player.sendMessage("- Rent up to 3 shops at the player market.");
						player.sendMessage("- World inspection with /co inspect.");
						player.sendMessage("- Return to last teleport destination with /back.");
						player.sendMessage("- Toggle your death coordinates in chat with /tdc.");
						player.sendMessage("- Special mail, sign, and anvil formatting (strike-through, italics, magic, etc).");
						player.sendMessage("- More TBA.");

					}

				}

			}
			// If the command executor is not a player, do this...
			else {

				// Deny console executions.
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "ERROR: The console cannot execute this command!"));

			}

		}

		// Healthy exit status.
		return true;

	}

	// Turns chat text into a command button.
	public void sendClickableMessage(Player player, String command) {

		// Declare a TextComponent with contents.
		TextComponent clickableText = Component.text(ChatColor.translateAlternateColorCodes('&', "&B&lClick here to see the benefits!"));

		// Style the clickable text.
		clickableText.color(NamedTextColor.AQUA).decoration(TextDecoration.BOLD, TextDecoration.State.TRUE);

		// Create a click event on the chat text.
		clickableText = clickableText.clickEvent(ClickEvent.clickEvent(ClickEvent.Action.RUN_COMMAND, command));

		// Send the clickable text to the player.
		player.sendMessage(clickableText);

	}

}