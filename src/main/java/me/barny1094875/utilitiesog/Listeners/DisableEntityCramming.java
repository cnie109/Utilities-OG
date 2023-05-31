package me.barny1094875.utilitiesog.Listeners;

import me.barny1094875.utilitiesog.Utilities_OG;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class DisableEntityCramming implements Listener {

    @EventHandler
    public void disableEntityCramming(EntityDamageEvent event){
        if(event.getCause().equals(DamageCause.CRAMMING)){
            if(Utilities_OG.config().getBoolean("disableEntityCramming")) {
                event.setCancelled(true);
            }
        }
    }

}
