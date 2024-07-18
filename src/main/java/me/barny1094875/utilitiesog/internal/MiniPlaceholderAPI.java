// This is free and unencumbered software released into the public domain.
// Authors: christianniehaus, NotAlexNoyle.
package me.barny1094875.utilitiesog.internal;

// Import libraries.
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import io.github.miniplaceholders.api.Expansion;
import io.github.miniplaceholders.api.MiniPlaceholders;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.Tag;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

// Declare the TrueOG MiniPlaceholderAPI Module.
public class MiniPlaceholderAPI {

	// Easy to call function to run all the code in this Module.
	public void register() {

		// Construct the MiniPlaceholder Expansion "player_display_name".
		Expansion expansion = Expansion.builder("player_display").filter(Player.class)
				.audiencePlaceholder("name", (audience, ctx, queue) -> {

					// Get the player who is seeing the MiniPlaceholder expansion.
					Player player = (Player) audience;

					// Create a MiniMessage component.
					Component formattedName = MiniMessage.miniMessage().deserialize("<dark_purple>" + player.getName() + "</dark_purple>");

					// Convert the component back to a MiniMessage String.
					String miniMessageString = MiniMessage.miniMessage().serialize(formattedName);

					// Create a Tag from the MiniMessage String.
					Tag miniMessageTag = Tag.inserting(MiniMessage.miniMessage().deserialize(miniMessageString));

					// Pass off the formatted Tag.
					return miniMessageTag;

					// Construct the MiniPlaceholder expansion into a module.
				}).build();

		try {

			// Register the MiniPlaceholder expansion with MiniPlaceholders.
			expansion.register();

			// Print a success message to the console if the MiniPlaceholder expansion registration was successful.
			Bukkit.getLogger().info("MiniPlaceholdersAPI expansion 'player_name' registered successfully.");

		}
		// If the MiniPlaceholder expansion registration failed, do this...
		catch (Exception error) {

			// Print a detailed error message to the server console.
			Bukkit.getLogger().severe("Failed to register MiniPlaceholdersAPI expansion: " + error.getMessage());

		}

	}

	// Replace MiniPlaceholders in a given String with their content from MiniPlaceholderAPI.
	public static TextComponent expandPlayerMiniPlaceholders(Player player, String message) {

		try {

			// Get the MiniPlaceholder content that is relevant for the specific player who is seeing the message.
			TagResolver placeholders = MiniPlaceholders.getAudiencePlaceholders(player);

			// Replace the MiniPlaceholders with the audience placeholder content and the modern color codes with actual colors.
			Component expandedMessage = MiniMessage.miniMessage().deserialize(message, placeholders);

			// Put the message back into a TextComponent and send it on.
			return (TextComponent) expandedMessage;

		}
		// If any MiniPlaceholders could not be expanded, do this...
		catch (Exception error) {

			// Construct a detailed error message.
			TextComponent formattedErrorMessage = Utils.serializerAnyCase(player, ("\"Error formatting MiniPlaceholdersAPI message for player: \" + player.getName() + \"Error: \" + error.getMessage()"));

			// Pass on the formatted error message.
			return formattedErrorMessage;

		}

	}

}