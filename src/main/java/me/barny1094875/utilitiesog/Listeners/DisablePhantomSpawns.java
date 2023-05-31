package me.barny1094875.utilitiesog.Listeners;

import me.barny1094875.utilitiesog.Utilities_OG;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class DisablePhantomSpawns implements Listener {

    @EventHandler
    public void disablePhantomSpawns(CreatureSpawnEvent event){
        if(event.getEntityType().equals(EntityType.PHANTOM)){
            if(!Utilities_OG.config().getBoolean("doPhantomSpawning")){
                event.setCancelled(true);
            }
        }
    }

}
