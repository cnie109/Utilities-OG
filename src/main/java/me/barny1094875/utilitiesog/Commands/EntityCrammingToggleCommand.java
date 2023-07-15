package me.barny1094875.utilitiesog.Commands;

import me.barny1094875.utilitiesog.Utilities_OG;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

public class EntityCrammingToggleCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args){

        if(command.getName().equalsIgnoreCase("togglecramming")){
            FileConfiguration config = Utilities_OG.getPlugin().getConfig();
            config.set("doEntityCramming", !config.getBoolean("doEntityCramming"));

            Component parsed = Utilities_OG.getMM().deserialize("<#00ff00>[Utilities-OG]<#ffff00> Entity cramming has been set to " + config.getBoolean("doEntityCramming"));
            sender.sendMessage(parsed);
        }

        return true;
    }

}
