// This is free and unencumbered software released into the public domain.
// Authors: christianniehaus, NotAlexNoyle.
package me.barny1094875.utilitiesog.internal;

import org.bukkit.command.CommandSender;
// Import libraries.
import org.bukkit.entity.Player;

import net.kyori.adventure.text.TextComponent;

// Declare a Class to hold miscellaneous utilities for internal use by Utilities-OG.
public class Utils {

	// Sends a formatted message to the player.
	public static void trueogMessage(Player player, String message) {

		// Sends a message using legacy Bukkit color codes in any case with built-in MiniPlaceholder expansion using the TrueOG MiniPlaceholderAPI.
		player.sendMessage(serializerAnyCase(player, message));

	}

	// Converts all Bukkit color codes in the message to lowercase so the case-sensitive MiniMessage legacy color code translator works.
	public static TextComponent serializerAnyCase(Player player, String subject) {

		// Keeps track of the ampersand count in the loop.
		int count = 0;
		// Count the number of ampersand characters to determine how many there are for lowercase conversion.
		for (char c : subject.toCharArray()) {

			// If the character is an ampersand, do this...
			if (c == '&') {

				// Increase the ampersand count by 1.
				count++;

			}

		}

		// Keeps track of the positions of '&' characters in the subject String.
		int[] positions = new int[count];
		// Keep track of the position of the metaphorical cursor in the subject String.
		int index = 0;
		// Iterates through the subject String to find the positions of '&' characters for lowercase conversion.
		for (int i = 0; i < subject.length(); i++) {

			// If the character is an ampersand, do this...
			if (subject.charAt(i) == '&') {

				// If the character directly after the ampersand is a valid uppercase Bukkit color code, do this...
				if (isUpperBukkitCode(subject.charAt(i + 1))) {

					// Replace the valid uppercase Bukkit color code with the equivalent lowercase one.
					subject = replaceAtIndex(subject, (i + 1), Character.toString(Character.toLowerCase(subject.charAt(i + 1))));

				}

				try {

					// Convert the newly uniform legacy Bukkit color codes into modern MiniMessage color / formatting codes.
					subject = replaceAtIndex(subject, (i + 1), ColorCodeMap.toMiniMessage(subject.charAt(i + 1)));

					// Delete the leftover ampersand.
					subject = replaceAtIndex(subject, i, "");

				}
				// If the character after an ampersand is not a Bukkit color / formatting code, do this...
				catch(NullPointerException error) {

					// Do nothing, ampersand is intended to be in the message.

				}

				// Move the metaphorical cursor forward by one.
				positions[index++] = i;

			}

		}

		// Process the newly uniform modern color codes via MiniMessage, including MiniPlaceholder expansion.
		TextComponent finalComponent = MiniPlaceholderAPI.expandPlayerMiniPlaceholders(player, subject);

		// Pass on the final message.
		return finalComponent;

	}

	// Detects if a Bukkit color code is uppercase.
	private static boolean isUpperBukkitCode(char input) {

		// An array of all potential uppercase Bukkit color code characters.
		char[] bukkitColorCodes = {'A', 'B', 'C', 'D', 'E', 'F', 'K', 'L', 'M', 'N', 'O', 'R'};
		// Keeps track of found uppercase Bukkit color codes.
		boolean match = false;
		// Loop through each character in the array.
		for (char c : bukkitColorCodes) {

			// Check if the current character in the array is equal to the input character.
			if (c == input) {

				// A match was found.
				match = true;

			}

		}

		// Return whether or not the character is an uppercase Bukkit color code.
		return match;

	}

	// Replaces a character in a String at a given index with a new String.
	private static String replaceAtIndex(String original, int index, String newString) {

		// Check if the index is valid.
		if (index >= 0 && index < original.length()) {

			// Create a new string with the replacement.
			return original.substring(0, index) + newString + original.substring(index + 1);

		}

		// If the index is invalid, return the original string. Soft error.
		return original;

	}

	// Sends an error message to the player if they don't have permission to run a given command.
	public static void permissionsErrorMessage(Player player, String command, String permission) {

		// Send a formatted error message using the TrueOG Message API.
		trueogMessage(player, ("&cERROR: You do not have permission to run the /" + command + " command! &6Required permission: &e" + permission + "&6."));

	}

	// Sends an error message to the console if the command which was run is only applicable to players.
	public static void consoleUseErrorMessage(CommandSender sender, String command, String permission) {

		// Send an unformatted error message directly to the console.
		sender.sendMessage(("ERROR: The server console cannot execute the command: " + command + "! To run it in-game, ensure you have the permission: " + permission + "."));		

	}

}