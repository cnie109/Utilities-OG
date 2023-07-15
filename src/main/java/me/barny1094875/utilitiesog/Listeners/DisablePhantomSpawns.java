package me.barny1094875.utilitiesog.Listeners;

import me.barny1094875.utilitiesog.Utilities_OG;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class DisablePhantomSpawns implements Listener
{

    @EventHandler
    public void disablePhantomSpawns(CreatureSpawnEvent event)
    {
        if (event.getEntityType().equals(EntityType.PHANTOM))
        {
            YamlConfiguration phantomDisabledPlayers = Utilities_OG.getPhantomDisabledPlayers();
            for (Player player : Bukkit.getServer().getOnlinePlayers())
            {
                // check if the player has disabled phantom spawns
                if (phantomDisabledPlayers.getBoolean(player.getUniqueId().toString()))
                {
                    // check if the phantom is close enough to the player to count as their spawn
                    if (event.getEntity().getLocation().distance(player.getLocation()) < 48)
                    {
                        event.setCancelled(true);
                        break;
                    }
                }
            }
        }
    }

}
