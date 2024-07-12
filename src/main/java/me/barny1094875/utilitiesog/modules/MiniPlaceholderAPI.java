package me.barny1094875.utilitiesog.modules;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import io.github.miniplaceholders.api.Expansion;
import io.github.miniplaceholders.api.MiniPlaceholders;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.Tag;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

public class MiniPlaceholderAPI {

	public static void register() {

		Expansion expansion = Expansion.builder("player_displayname")
				.filter(Player.class)
				.audiencePlaceholder("name", (audience, ctx, queue) -> {
					Player player = (Player) audience;
					Bukkit.getLogger().info("Placeholder expansion triggered for " + player.getName());

					// Create a MiniMessage component
					Component formattedName = MiniMessage.miniMessage().deserialize("<gold>" + player.getName() + "</gold>");

					// Convert the component back to a MiniMessage string 
					String miniMessageString = MiniMessage.miniMessage().serialize(formattedName);

					// Create a Tag from the MiniMessage string
					Tag miniMessageTag = Tag.inserting(MiniMessage.miniMessage().deserialize(miniMessageString));
					return miniMessageTag;
				})
				.build();

		try {
			expansion.register();
			Bukkit.getLogger().info("Expansion 'utilitiesog_player_displayname' registered successfully");
		} catch (Exception e) {
			Bukkit.getLogger().severe("Failed to register expansion: " + e.getMessage());
		}

	}

	public static void sendMessageWithPlaceholders(Player player, String message) {

		Bukkit.getLogger().info("Sending message to " + player.getName() + " with placeholders: " + message);

		try {
			TagResolver placeholders = MiniPlaceholders.getAudiencePlaceholders(player);
			Component formattedMessage = MiniMessage.miniMessage().deserialize(message, placeholders); 
			player.sendMessage(formattedMessage);
		} catch (Exception e) {
			Bukkit.getLogger().warning("Error formatting message for " + player.getName() + ": " + e.getMessage());
		}

	}

}
