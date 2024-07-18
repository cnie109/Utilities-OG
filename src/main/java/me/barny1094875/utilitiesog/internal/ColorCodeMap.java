// This is free and unencumbered software released into the public domain.
// Authors: christianniehaus, NotAlexNoyle.
package me.barny1094875.utilitiesog.internal;

// Import libraries.
import java.util.HashMap;
import java.util.Map;

// Declare a Class to help convert back and forth between legacy Bukkit color / formatting codes and modern MiniMessage color / formatting codes.
public class ColorCodeMap {

	// Declare a HashMap to store legacy codes as keys and modern codes as values.
	private static final Map<Character, String> CODE_MAP = new HashMap<>();

	// Make this entire section static.
	static {

		// Pass through the ampersand itself.
		CODE_MAP.put('&', "&");

		// Black text.
		CODE_MAP.put('0', "<color:black>");

		// Dark Blue text.
		CODE_MAP.put('1', "<color:dark_blue>");

		// Dark Green text.
		CODE_MAP.put('2', "<color:dark_green>");

		// Dark Aqua text.
		CODE_MAP.put('3', "<color:dark_aqua>");

		// Dark Red text.
		CODE_MAP.put('4', "<color:dark_red>");

		// Dark Purple text.
		CODE_MAP.put('5', "<color:dark_purple>");

		// Gold text.
		CODE_MAP.put('6', "<color:gold>");

		// Gray text.
		CODE_MAP.put('7', "<color:gray>");

		// Dark Gray text.
		CODE_MAP.put('8', "<color:dark_gray>");

		// Blue text.
		CODE_MAP.put('9', "<color:blue>");

		// Green text.
		CODE_MAP.put('a', "<color:green>");

		// Aqua text.
		CODE_MAP.put('b', "<color:aqua>");

		// Red text.
		CODE_MAP.put('c', "<color:red>");

		// Purple text.
		CODE_MAP.put('d', "<color:light_purple>");

		// Yellow text.
		CODE_MAP.put('e', "<color:yellow>");

		// White text.
		CODE_MAP.put('f', "<color:white>");

		// Obfuscated text.
		CODE_MAP.put('k', "<obf>");

		// Bold text.
		CODE_MAP.put('l', "<b>");

		// Strikethrough text.
		CODE_MAP.put('m', "<st>");

		// Underlined text.
		CODE_MAP.put('n', "<u>");

		// Italic text.
		CODE_MAP.put('o', "<i>");

		// Rainbow text.
		CODE_MAP.put('*', "<rainbow>");

		// Reset all formatting.
		CODE_MAP.put('r', "<reset>");

	}

	// Convert any lowercase Bukkit color / formatting code to a modern MiniMessage color / formatting code.
	public static String toMiniMessage(char legacyCode) {

		// Use the Bukkit color / formatting code as a key to get the modern MiniMessage color / formatting code from the Hash Map.
		String miniMessageCode = CODE_MAP.get(legacyCode);

		// If the Bukkit color code is not in the Hash Map, do this...
		if(miniMessageCode == null) {

			// Cause a NullPointerExceptionError for the caller to handle.
			throw new NullPointerException();

		}

		// If the Bukkit color code is in the Hash Map, do this...
		return miniMessageCode;

	}

}