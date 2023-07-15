package me.barny1094875.utilitiesog.Commands;

import me.barny1094875.utilitiesog.Utilities_OG;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class PhantomToggleCommand
{

    public static boolean run(@NotNull CommandSender sender)
    {

        YamlConfiguration phantomDisabledPlayers = Utilities_OG.getPhantomDisabledPlayers();
        boolean playerDisabled = phantomDisabledPlayers.getBoolean(((Player) sender).getUniqueId().toString());

        phantomDisabledPlayers.set(((Player) sender).getUniqueId().toString(), !playerDisabled);
        Component parsed = Utilities_OG.getMM().deserialize("<#00ff00>[Utilities-OG]<#ffff00> Phantom spawning has been set to " + !playerDisabled + " for you");
        sender.sendMessage(parsed);
        try
        {
            phantomDisabledPlayers.save(Utilities_OG.getPhantomDisabledPlayersFile());
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        return true;
    }

}
