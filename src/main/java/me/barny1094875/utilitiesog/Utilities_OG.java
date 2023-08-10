// This is free and unencumbered software released into the public domain.
// Authors: christianniehaus, NotAlexNoyle.
package me.barny1094875.utilitiesog;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.barny1094875.utilitiesog.Commands.BuyCommand;
// Import required libraries.
import me.barny1094875.utilitiesog.Commands.UtilitiesCommand;
import me.barny1094875.utilitiesog.Listeners.DisableEntityCramming;
import me.barny1094875.utilitiesog.Listeners.DisablePhantomSpawns;
import net.kyori.adventure.text.minimessage.MiniMessage;

public final class Utilities_OG extends JavaPlugin
{

	private File file;
	private static Utilities_OG plugin;
	private static final MiniMessage mm = MiniMessage.miniMessage();
	private static FileConfiguration config;
	private static File phantomDisabledPlayersFile;
	private static YamlConfiguration phantomDisabledPlayers;

	@Override
	public void onEnable()
	{

		plugin = this;

		this.file = new File(this.getDataFolder(), "config.yml");
		if (! this.file.exists())
		{
			this.saveDefaultConfig();
		}

		config = this.getConfig();

		phantomDisabledPlayersFile = new File(this.getDataFolder(), "phantomDisabledUsers.yml");
		try
		{
			if (! phantomDisabledPlayersFile.exists())
			{

				phantomDisabledPlayersFile.createNewFile();

			}
		} catch (IOException e)
		{

			this.getLogger().severe("Something went wrong when creating the phantom toggle cache file!");

		}

		phantomDisabledPlayers = YamlConfiguration.loadConfiguration(phantomDisabledPlayersFile);

		// Set up each feature here, using the config to enable only that which is desired,
		// be sure to label, using comments, what each block is enabling.

		// Enable the Phantom Toggle Module.
		if (this.getConfig().getBoolean("PhantomToggle"))
		{
			// Activate the Phantom Toggle Listener.
			getServer().getPluginManager().registerEvents(new DisablePhantomSpawns(), this);
		}
		// Enable the Disable Entity Cramming Module.
		if (this.getConfig().getBoolean("EntityCrammingDisable"))
		{
			// Activate the Entity Cramming Listener.
			getServer().getPluginManager().registerEvents(new DisableEntityCramming(), this);
		}
		// Enable the Buy Menu Module.
		if (this.getConfig().getBoolean("BuyMenu"))
		{
			// Register the /buy command.
			this.getCommand("buy").setExecutor(new BuyCommand());
		}

		// Register the primary /utilities command.
		this.getCommand("utilities").setExecutor(new UtilitiesCommand());

	}

	public static Utilities_OG getPlugin()
	{
		return plugin;
	}

	public static FileConfiguration config()
	{
		return config;
	}

	public static MiniMessage getMM()
	{
		return mm;
	}

	public static YamlConfiguration getPhantomDisabledPlayers()
	{
		return phantomDisabledPlayers;
	}

	public static File getPhantomDisabledPlayersFile()
	{
		return phantomDisabledPlayersFile;
	}

}