// This is free and unencumbered software released into the public domain.
// Authors: christianniehaus, NotAlexNoyle.
package me.barny1094875.utilitiesog;

import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import me.barny1094875.utilitiesog.Listeners.DisableEntityCramming;
import me.barny1094875.utilitiesog.Listeners.DisablePhantomSpawns;
import me.barny1094875.utilitiesog.modules.*;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.io.File;
import java.io.IOException;

public final class Utilities_OG extends JavaPlugin {

    private File file;
    private static Utilities_OG plugin;
    private static final MiniMessage mm = MiniMessage.miniMessage();
    private static FileConfiguration config;
    private static File phantomPreferencesFile;
    private static YamlConfiguration phantomPreferences;
    private static StateFlag FlippyFlag;

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
        // Enable the ColorCodes module
        if (this.getConfig().getBoolean("ColorCodes")) {
            this.getCommand("colorcodes").setExecutor(new ColorCodes());
        }
        // Enable the NoFlippy module
        if (this.getConfig().getBoolean("NoFlippy")) {
            // check that WorldGuard is installed
            if (getServer().getPluginManager().getPlugin("WorldGuard") == null) {
                this.getLogger().severe("WorldGuard is not installed! Disabling NoFlippy...");
            } else {
                // Activate the NoFlippy Listener.
                getServer().getPluginManager().registerEvents(new NoFlippy(), this);
            }
        }

        // Register the primary /utilities command.
        this.getCommand("utilities").setExecutor(new AboutModule());

    }

    @Override
    public void onLoad() {

        // Add the WorldGuard flag for an area where NoFlippy is active.
        FlagRegistry registry = WorldGuard.getInstance().getFlagRegistry();
        try {

            // Create a flag with the name "my-custom-flag", defaulting to true.
            StateFlag flag = new StateFlag("can-flippy", true);

            // Register the new flag with WorldGuard.
            registry.register(flag);

            // Only set the field if there was no error.
            FlippyFlag = flag;

        } catch (FlagConflictException e) {

            // Some other plugin registered a flag by the same name already.
            // You can use the existing flag, but this may cause conflicts - be sure to check type.
            Flag<?> existing = registry.get("can-flippy");
            if (existing instanceof StateFlag) {

                FlippyFlag = (StateFlag) existing;

            }

        }

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

    // Share FlippyFlag with Listeners.
    public static StateFlag getFlippyFlag() {
        return FlippyFlag;
    }

    // Runs plugin asynchronously so multiple players can use it at once efficiently.
    public BukkitTask runTaskAsynchronously(final Runnable run) {

        // Schedule Processes.
        return this.getServer().getScheduler().runTaskAsynchronously(this, run);

    }

}
