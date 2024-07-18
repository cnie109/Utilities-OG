// This is free and unencumbered software released into the public domain.
// Authors: christianniehaus, NotAlexNoyle.
package me.barny1094875.utilitiesog.Listeners;

// Import libraries.
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

// Import required libraries.
import me.barny1094875.utilitiesog.UtilitiesOG;

// Declare the EntityCrammingToggle Module with Bukkit Listeners.
public class DisableEntityCrammingListener implements Listener {

	// Listen for entities being damaged.
	@EventHandler
	public void disableEntityCramming(EntityDamageEvent event) {

		// If the damage is caused by entity cramming, do this...
		if(event.getCause().equals(DamageCause.CRAMMING)) {

			// If the Disable Entity Cramming Module is enabled in config.yml, do this...
			if(UtilitiesOG.config().getBoolean("EntityCrammingDisable") == true) {

				// Cancel damage from entity cramming.
				event.setCancelled(true);

			}

		}

	}

}