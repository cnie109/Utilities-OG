// This is free and unencumbered software released into the public domain.
// Authors: christianniehaus, NotAlexNoyle.
package me.barny1094875.utilitiesog;

// Import libraries.
import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import com.sk89q.worldguard.protection.flags.StateFlag;

import me.barny1094875.utilitiesog.Listeners.DisableEntityCrammingListener;
import me.barny1094875.utilitiesog.Listeners.NoFlippyListener;
import me.barny1094875.utilitiesog.Listeners.TogglePhantomsListener;
import me.barny1094875.utilitiesog.commands.AboutCommand;
import me.barny1094875.utilitiesog.commands.BingCommand;
import me.barny1094875.utilitiesog.commands.ColorCodesCommand;
import me.barny1094875.utilitiesog.commands.PingCommand;
import me.barny1094875.utilitiesog.commands.RanksCommand;
import me.barny1094875.utilitiesog.commands.ToggleCrammingCommand;
import me.barny1094875.utilitiesog.commands.TogglePhantomsCommand;
import me.barny1094875.utilitiesog.internal.FlagRegistrationException;
import me.barny1094875.utilitiesog.internal.MiniPlaceholderAPI;
import me.barny1094875.utilitiesog.internal.Utils;
import me.barny1094875.utilitiesog.modules.ChainArmorModule;
import me.barny1094875.utilitiesog.modules.MockBambooModule;
import net.kyori.adventure.text.TextComponent;

// Declare main plugin class.
public final class UtilitiesOG extends JavaPlugin {

	// Declare empty fields for use later.
	private File file;
	private static UtilitiesOG plugin;
	private static FileConfiguration config;
	private static File phantomPreferencesFile;
	private static YamlConfiguration phantomPreferences;
	private static StateFlag FlippyFlag;

	// When the plugin is enabled, do this...
	@Override
	public void onEnable() {

		// Populate the main class getter getPlugin().
		plugin = this;

		// Attempt to populate the file object with the config file.
		this.file = new File(this.getDataFolder(), "config.yml");

		// If the config file was not found, do this...
		if (! this.file.exists()) {

			// Create the config file in the filesystem using the one in the assembled plugin .jar.
			this.saveDefaultConfig();

		}

		// Cast the config file from a File to a FileConfiguration.
		config = this.getConfig();

		// Attempt to populate the file object with the phantom toggle cache file.
		phantomPreferencesFile = new File(this.getDataFolder(), "phantomDisabledUsers.yml");

		try {

			// If the phantom toggle cache file was not found, do this...
			if (! phantomPreferencesFile.exists()) {

				// Create the phantom toggle cache file in the filesystem using the one in the assembled plugin .jar.
				phantomPreferencesFile.createNewFile();

			}

		}
		// If there is a file IO error, do this...
		catch (IOException error) {

			// Log the problem with creating the phantom toggle cache file to the server console.
			this.getLogger().severe("ERROR: Failed to create the phantom toggle cache file! " + error.getMessage());

		}

		// Cast the phantom toggle cache file from a File to a YamlConfiguration.
		phantomPreferences = YamlConfiguration.loadConfiguration(phantomPreferencesFile);

		// TrueOG Network Contributors: set up each feature below, using the config file to enable only that which is desirable by default.
		// Be sure to label, using comments, which feature(s) each block is enabling.

		// FEATURE: Enable crafting chain armor using chains.
		// If the Chain Armor Module is enabled in the config file, do this...
		if (this.getConfig().getBoolean("ChainArmor")) {

			// Enable the Chain Armor Module.
			ChainArmorModule.Enable();

		}

		// FEATURE: Display information to the player about the syntax for bukkit's color codes.
		// If the Color Codes Module is enabled in the config file, do this...
		if (this.getConfig().getBoolean("ColorCodes")) {

			// If the Color Codes Module is enabled in the config file, do this...
			this.getCommand("colorcodes").setExecutor(new ColorCodesCommand());

		}

		// FEATURE: Disable entity cramming server-wide. Entity cramming is when mobs pile up and cause damage to each other.
		// WARNING: Make sure there is another plugin that establishes entity limits if this module is enabled. Otherwise the server will lag.
		// If the Entity Cramming Toggle Module is enabled in the config file, do this...
		if (this.getConfig().getBoolean("DisableEntityCramming")) {

			// Activate the Entity Cramming Toggle Listener.
			getServer().getPluginManager().registerEvents(new DisableEntityCrammingListener(), this);

			// Activate the Entity Cramming Toggle Command.
			this.getCommand("togglecramming").setExecutor(new ToggleCrammingCommand());

		}

		// FEATURE: A custom, easy to use MiniPlaceholders API.
		// If the MiniPlaceholdersAPI Module is enabled in the config file, do this...
		if (this.getConfig().getBoolean("MiniPlaceholderAPI")) {

			// Enable the TrueOG MiniPlaceholdersAPI Module.
			MiniPlaceholderAPI miniPlaceholders = new MiniPlaceholderAPI();

			// Register our custom MiniPlaceholdersAPI Module with MiniPlaceholders.
			miniPlaceholders.register();

		}

		// FEATURE: Pre-1.20 Mock Bamboo Wood. Makes Bamboo craft into Oak Planks called "Bamboo Wood".
		// If the Bamboo Wood Module is enabled in the config file, do this...
		if (this.getConfig().getBoolean("MockBamboo")) {

			// Enable the Bamboo Wood Module.
			MockBambooModule.Enable();

		}

		// FEATURE: Prevent trap doors from being flipped in any WorldGuard regions with the "can-flippy" flag set to DENY.
		// If the NoFlippy Module is enabled in the config file, do this...
		if (this.getConfig().getBoolean("NoFlippy")) {

			// If WorldGuard is installed, do this...
			if (getServer().getPluginManager().getPlugin("WorldGuard") != null) {

				// Activate the NoFlippy Listener.
				getServer().getPluginManager().registerEvents(new NoFlippyListener(), this);

			}
			// If WorldGuard is not installed, do this...
			else {

				// Log WorldGuard not being found to the server console.
				this.getLogger().severe("WorldGuard is not installed! Disabling NoFlippy...");

			}

		}

		// FEATURE: A /ping command that returns a player's real ping.
		// FEATURE: A /bing command that replicates the functionality of the default /ping command.
		// If the Ping Module is enabled in the config file, do this...
		if (this.getConfig().getBoolean("Ping")) {

			// Enable the Ping command.
			this.getCommand("ping").setExecutor(new PingCommand());

			// Enable the Bing command.
			this.getCommand("bing").setExecutor(new BingCommand());

		}

		// FEATURE: Display information to the player about the ranks available at the TrueOG Network Store (https://store.true-og.net/).
		// If the Ranks Menu Module is enabled in the config file, do this...
		if (this.getConfig().getBoolean("RanksMenu")) {

			// Activate the Ranks Command.
			this.getCommand("ranks").setExecutor(new RanksCommand());

		}

		// FEATURE: Enable individual players to toggle phantom spawning on or off with the command /togglephantoms.
		// If the Phantom Toggle Module is enabled in the config file, do this...
		if (this.getConfig().getBoolean("TogglePhantoms")) {

			// Activate the Phantom Toggle Listener.
			getServer().getPluginManager().registerEvents(new TogglePhantomsListener(), this);

			// Activate the Phantom Toggle Command.
			this.getCommand("togglephantoms").setExecutor(new TogglePhantomsCommand());

		}

		// FEATURE: Display meta-information about Utilities-OG. Always enabled.
		// Enable the root /utilities command to display information about the plugin to the player.
		this.getCommand("utilities").setExecutor(new AboutCommand());

	}

	// After the server loads, do this...
	@Override
	public void onLoad() {

		try {

			// Register the Flag "can-flippy" with WorldGuard, and make the it retrievable.
			FlippyFlag = NoFlippyListener.registerNoFlippyWorldGuardFlag(FlippyFlag);

		}
		// If the WorldGuard Flag registration failed, do this...
		catch (FlagRegistrationException error) {

			// Log the WorldGuard Flag registration error to the server console.
			this.getLogger().severe("ERROR: Failed to register the can-flippy Flag with WorldGuard. " + error.getMessage());

		}

	}

	// Getter/API for TrueOG message formatting. Supports legacy Bukkit color codes. Case insensitive.
	public static void trueogSendMessage(Player player, String message) {

		// Forward the message to the server.
		Utils.trueogMessage(player, message);

	}

	// API for TrueOG MiniPlaceholderAPI creation.
	public static void trueogCreateMiniPlaceholder() {

		// TODO: Implement the trueogCreateMiniPlaceholder API.

	}
	
	// Getter/API for TrueOG MiniPlaceholderAPI expansion.
	public static TextComponent trueogExpandMiniPlaceholders(Player player, String input) {

		// Expand all the MiniPlaceholders in a given String.
		return MiniPlaceholderAPI.expandPlayerMiniPlaceholders(player, input);

	}

	// Getter/API for the Ranks Module.
	public static RanksCommand ranksCommand() {

		// Return a fresh Ranks Module.
		return new RanksCommand();

	}

	// Getter/API for the Ping Module.
	public static PingCommand pingCommand() {

		// Return a fresh Ping Module.
		return new PingCommand();

	}

	// Getter/API for Utilities-OG.
	public static UtilitiesOG getPlugin() {

		// Return the current Utilities-OG instance.
		return plugin;

	}

	// Getter for the config file.
	public static FileConfiguration config() {

		// Return the config file in FileConfiguration form.
		return config;

	}

	// Getter/API for the phantom toggle cache file.
	public static File getPhantomDisabledPlayersFile() {

		// Return the phantom toggle cache file.
		return phantomPreferencesFile;

	}

	// Getter/API for the phantom toggle cache file in YAML form.
	public static YamlConfiguration getPhantomPreferences() {

		// Return the phantom toggle cache file in YAML form.
		return phantomPreferences;

	}

	// Getter for the FlippyFlag state.
	public static StateFlag getFlippyFlag() {

		// Return the FlippyFlag for WorldGuard.
		return FlippyFlag;

	}

	// Runs plugin asynchronously so multiple players can use it at once efficiently.
	public BukkitTask runTaskAsynchronously(final Runnable run) {

		// Schedule processes.
		return this.getServer().getScheduler().runTaskAsynchronously(this, run);

	}

}