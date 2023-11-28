// This is free and unencumbered software released into the public domain.
// Authors: christianniehaus, NotAlexNoyle.
package me.barny1094875.utilitiesog;

import me.barny1094875.utilitiesog.Listeners.DisableEntityCramming;
import me.barny1094875.utilitiesog.Listeners.DisablePhantomSpawns;
import me.barny1094875.utilitiesog.modules.*;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class Utilities_OG extends JavaPlugin {

    private File file;
    private static Utilities_OG plugin;
    private static final MiniMessage mm = MiniMessage.miniMessage();
    private static FileConfiguration config;
    private static File phantomPreferencesFile;
    private static YamlConfiguration phantomPreferences;

    @Override
    public void onEnable() {

        plugin = this;

        this.file = new File(this.getDataFolder(), "config.yml");
        if (!this.file.exists()) {
            this.saveDefaultConfig();
        }

        config = this.getConfig();

        phantomPreferencesFile = new File(this.getDataFolder(), "phantomDisabledUsers.yml");
        try {
            if (!phantomPreferencesFile.exists()) {

                phantomPreferencesFile.createNewFile();

            }
        } catch (IOException e) {

            this.getLogger().severe("Something went wrong when creating the phantom toggle cache file!");

        }

        phantomPreferences = YamlConfiguration.loadConfiguration(phantomPreferencesFile);

        // Set up each feature here, using the config to enable only that which is desired,
        // be sure to label, using comments, what each block is enabling.

        // Enable the Phantom Toggle Module.
        if (this.getConfig().getBoolean("PhantomToggle")) {
            // Activate the Phantom Toggle Listener.
            getServer().getPluginManager().registerEvents(new DisablePhantomSpawns(), this);
            // Activate the Phantom Toggle Command.
            this.getCommand("togglephantoms").setExecutor(new PhantomToggleModule());
        }
        // Enable the Entity Cramming Toggle Module.
        if (this.getConfig().getBoolean("EntityCrammingDisable")) {
            // Activate the Entity Cramming Toggle Listener.
            getServer().getPluginManager().registerEvents(new DisableEntityCramming(), this);
            // Activate the Entity Cramming Toggle Command.
            this.getCommand("togglecramming").setExecutor(new EntityCrammingToggleModule());
        }
        // Enable the Ranks Module.
        if (this.getConfig().getBoolean("RanksMenu")) {
            // Activate the Ranks Command.
            this.getCommand("ranks").setExecutor(new RanksModule());
        }
        // Enable the Ping Module.
        if (this.getConfig().getBoolean("Ping")) {
            // Enable the Ping command.
            this.getCommand("ping").setExecutor(new PingModule());
            // Enable the Bing command (replicates old /ping functionality).
            this.getCommand("bing").setExecutor(new BingModule());
        }
        // Enable bamboo wood module
        if (this.getConfig().getBoolean("BambooWood")) {
            BambooWood.Enable();
        }
        // Enable chain armour module
        if (this.getConfig().getBoolean("ChainArmour")) {
            ChainArmour.Enable();
        }

        // Register the primary /utilities command.
        this.getCommand("utilities").setExecutor(new AboutModule());

    }

    // Rank menu API.
    public RanksModule ranksCommand() {
        return new RanksModule();
    }

    // Ping API.
    public PingModule pingCommand() {
        return new PingModule();
    }

    public static Utilities_OG getPlugin() {
        return plugin;
    }

    public static FileConfiguration config() {
        return config;
    }

    public static MiniMessage getMM() {
        return mm;
    }

    public static YamlConfiguration getPhantomPreferences() {
        return phantomPreferences;
    }

    public static File getPhantomDisabledPlayersFile() {
        return phantomPreferencesFile;
    }

}
