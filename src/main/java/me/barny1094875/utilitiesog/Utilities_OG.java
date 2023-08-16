// This is free and unencumbered software released into the public domain.
// Authors: christianniehaus, NotAlexNoyle.
package me.barny1094875.utilitiesog;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import me.barny1094875.utilitiesog.Listeners.DisableEntityCramming;
import me.barny1094875.utilitiesog.Listeners.DisablePhantomSpawns;
import me.barny1094875.utilitiesog.modules.AboutModule;
import me.barny1094875.utilitiesog.modules.BingModule;
import me.barny1094875.utilitiesog.modules.EntityCrammingToggleModule;
import me.barny1094875.utilitiesog.modules.PhantomToggleModule;
import me.barny1094875.utilitiesog.modules.PingModule;
import me.barny1094875.utilitiesog.modules.RanksModule;
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
		if (!this.file.exists())
		{
			this.saveDefaultConfig();
		}

		config = this.getConfig();

		phantomDisabledPlayersFile = new File(this.getDataFolder(), "phantomDisabledUsers.yml");
		try
		{
			if (!phantomDisabledPlayersFile.exists())
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
			// Activate the Phantom Toggle Command.
			this.getCommand("togglephantoms").setExecutor(new PhantomToggleModule());
		}
		// Enable the Entity Cramming Toggle Module.
		if (this.getConfig().getBoolean("EntityCrammingDisable"))
		{
			// Activate the Entity Cramming Toggle Listener.
			getServer().getPluginManager().registerEvents(new DisableEntityCramming(), this);
			// Activate the Entity Cramming Toggle Command.
			this.getCommand("togglecramming").setExecutor(new EntityCrammingToggleModule());
		}
		// Enable the Ranks Module.
		if (this.getConfig().getBoolean("RanksMenu"))
		{
			// Activate the Ranks Command.
			this.getCommand("ranks").setExecutor(new RanksModule());
		}
		// Enable the Ping Module.
		if (this.getConfig().getBoolean("Ping"))
		{
			// Enable the Ping command.
			this.getCommand("ping").setExecutor(new PingModule());
			// Enable the Bing command (replicates old /ping functionality).
			this.getCommand("bing").setExecutor(new BingModule());
		}
		// Enable bamboo wood module
		if (this.getConfig().getBoolean("BambooWood"))
		{
			ShapedRecipe BambooWood = new ShapedRecipe(new NamespacedKey(this, "Bamboo_Wood"),
					new ItemStack(Material.OAK_PLANKS))
					.shape("bb", "bb")
					.setIngredient('b', Material.BAMBOO);
			Bukkit.addRecipe(BambooWood);
		}

		// Register the primary /utilities command.
		this.getCommand("utilities").setExecutor(new AboutModule());

	}

	// Rank menu API.
	public RanksModule ranksCommand()
	{
		return new RanksModule();
	}

	// Ping API.
	public PingModule pingCommand()
	{
		return new PingModule();
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
