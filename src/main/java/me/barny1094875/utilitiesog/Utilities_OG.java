package me.barny1094875.utilitiesog;

import me.barny1094875.utilitiesog.Commands.PhantomToggleCommand;
import me.barny1094875.utilitiesog.Listeners.DisableEntityCramming;
import me.barny1094875.utilitiesog.Listeners.DisablePhantomSpawns;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class Utilities_OG extends JavaPlugin {

    private File file;
    private static Utilities_OG plugin;
    private static MiniMessage mm = MiniMessage.miniMessage();
    private static FileConfiguration config;

    @Override
    public void onEnable() {

        plugin = this;

        this.file = new File(this.getDataFolder(), "config.yml");

        try{
            if(!this.file.exists()){
                this.file.createNewFile();
            }
        } catch (IOException e) {
            this.getLogger().severe("Something went wrong when creating the config file!");
        }
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();


        config = this.getConfig();

        // set up listeners here
        getServer().getPluginManager().registerEvents(new DisablePhantomSpawns(), this);
        getServer().getPluginManager().registerEvents(new DisableEntityCramming(), this);


        // set up commands here
        this.getCommand("togglephantoms").setExecutor(new PhantomToggleCommand());

    }


    public static Utilities_OG getPlugin(){
        return plugin;
    }

    public static FileConfiguration config(){
        return config;
    }

    public static MiniMessage getMM(){
        return mm;
    }
}
