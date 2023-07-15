package me.barny1094875.utilitiesog;

import me.barny1094875.utilitiesog.Commands.UtilitiesCommand;
import me.barny1094875.utilitiesog.Listeners.DisableEntityCramming;
import me.barny1094875.utilitiesog.Listeners.DisablePhantomSpawns;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

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
        try
        {
            if (!this.file.exists())
            {
                this.file.createNewFile();
                this.getConfig().options().copyDefaults(true);
                this.saveConfig();
            }
        } catch (IOException e)
        {
            this.getLogger().severe("Something went wrong when creating the config file!");
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
            this.getLogger().severe("Something went wrong when creating the config file!");
        }
        phantomDisabledPlayers = YamlConfiguration.loadConfiguration(phantomDisabledPlayersFile);

        // set up each feature here, using the config to enable only that which is desired
        // be sure to label, using comments, what each block is enabling

        // enable PhantomToggle
        if (this.getConfig().getBoolean("PhantomToggle"))
        {
            // listener
            getServer().getPluginManager().registerEvents(new DisablePhantomSpawns(), this);
        }
        // enable EntityCrammingDisable
        if (this.getConfig().getBoolean("EntityCrammingDisable"))
        {
            // listener
            getServer().getPluginManager().registerEvents(new DisableEntityCramming(), this);
        }

        // set up the command
        this.getCommand("utilites").setExecutor(new UtilitiesCommand());

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
