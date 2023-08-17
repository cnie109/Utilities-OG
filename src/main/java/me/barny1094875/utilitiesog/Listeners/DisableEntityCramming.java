// This is free and unencumbered software released into the public domain.
// Authors: christianniehaus, NotAlexNoyle.
package me.barny1094875.utilitiesog.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

// Import required libraries.
import me.barny1094875.utilitiesog.Utilities_OG;

public class DisableEntityCramming implements Listener {

	// Listen for entities being damaged.
	@EventHandler
	public void disableEntityCramming(EntityDamageEvent event) {

		// If the damage is caused by entity cramming, do this...
		if(event.getCause().equals(DamageCause.CRAMMING)) {

			// If disabling entity cramming is set to "true" in config.yml, do this...
			if(Utilities_OG.config().getBoolean("disableEntityCramming")) {

				event.setCancelled(true);

			}

		}

	}

}