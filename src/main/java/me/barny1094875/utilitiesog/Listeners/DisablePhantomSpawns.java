// This is free and unencumbered software released into the public domain.
// Authors: christianniehaus, NotAlexNoyle.
package me.barny1094875.utilitiesog.Listeners;

import me.barny1094875.utilitiesog.Utilities_OG;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class DisablePhantomSpawns implements Listener {

    // Listen for mob spawning events.
    @EventHandler
    public void disablePhantomSpawns(CreatureSpawnEvent event) {
        // If the mob that spawned is a phantom, do this...
        if (event.getEntityType().equals(EntityType.PHANTOM)) {
            // Store the contents of phantomDisabledUsers.yml file as a YAML object.
            YamlConfiguration phantomsAreDisabled = Utilities_OG.getPhantomPreferences();
            // get every player currently on the server in the world that the phantom spawned
            for (Player player : Bukkit.getServer().getOnlinePlayers()
                    .stream()
                    .filter(p -> p.getWorld().equals(event.getEntity().getWorld()))
                    .filter(p -> phantomsAreDisabled.getBoolean(p.getUniqueId().toString()))
                    .toArray(Player[]::new)) {
                // Check if the phantom is close enough to the player to count as their spawn.
                if (event.getEntity().getLocation().distance(player.getLocation()) < 48) {
                    event.setCancelled(true);
                }
            }
        }
    }
}
