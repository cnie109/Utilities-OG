// This is free and unencumbered software released into the public domain.
// Authors: christianniehaus, NotAlexNoyle.
package me.barny1094875.utilitiesog.Listeners;

// Import libraries.
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;

import me.barny1094875.utilitiesog.UtilitiesOG;
import me.barny1094875.utilitiesog.internal.FlagRegistrationException;

// Declare the NoFlippy Module with Bukkit Listeners.
public class NoFlippyListener implements Listener {

	// Declare instance of class as static so multiple players can use it.
	private static NoFlippyListener instance;

	// Return instance of class as static so multiple players can use it.
	public static NoFlippyListener getInstance() {

		// Pass back Listeners to main.
		return instance;

	}

	// Listen for a player interacting with a trap-door.
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {

		// Get the nature of the interaction and store it.
		Action action = event.getAction();
		// Get the block that was interacted with and store it.
		Block blockClicked = event.getClickedBlock();
		// Get the player who interacted and store them.
		Player player = event.getPlayer();

		// Declare a container for the block type for null checking.
		String blockContainerAsString = null;
		try {

			// Try storing the block type as a string. Throws NullPointerException if not applicable.
			blockContainerAsString = blockClicked.getType().toString();

		}
		// If the block type was null, do this...
		catch (NullPointerException error) {

			// Do nothing.
			return;

		}

		// If the interaction was with a trap door, do this...
		if (blockContainerAsString.contains("TRAPDOOR")) {

			// If the interaction was a right click, do this...
			if (action.isRightClick()) {

				// If the player does not have permission to flip trap doors, do this...
				if (! player.hasPermission("noflippy.bypass")) {

					// Get the location of the block the player tried to flip.
					Location blockLocation = BukkitAdapter.adapt(blockClicked.getLocation());

					// Instantiate a WorldGuard Region Container.
					RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();

					// Instantiate a WorldGuard Region Query.
					RegionQuery query = container.createQuery();

					// Get all regions at the location of the block.
					ApplicableRegionSet set = query.getApplicableRegions(blockLocation);

					// If the can-flippy WorldGuard Flag is set to DENY, for any region in the set, do this...
					if (! set.testState(null, UtilitiesOG.getFlippyFlag())) {

						// Cancel the trapdoor flip event.
						event.setCancelled(true);

					}

				}

			}

		}

	}

	// Registers the WorldGuard Flag "can-flippy". Throws a custom exception in case of error.
	public static StateFlag registerNoFlippyWorldGuardFlag(StateFlag FlippyFlags) throws FlagRegistrationException {

		// Add the WorldGuard flag for an area where NoFlippy is active.
		FlagRegistry registry = WorldGuard.getInstance().getFlagRegistry();

		try {

			// Create a flag with the name "my-custom-flag", defaulting to true.
			StateFlag flag = new StateFlag("can-flippy", true);

			// Register the new flag with WorldGuard.
			registry.register(flag);

			// Only return the flag if there was no error.
			return flag;

		}
		// If there was a WorldGuard Flag conflict, do this...
		catch (FlagConflictException error) {

			// Log the WorldGuard Flag conflict to the server console.
			UtilitiesOG.getPlugin().getLogger().severe("ERROR: can-flippy of the NoFlippy Module has a WorldGuard Flag conflict! " + error.getMessage());

			// Pulls the WorldGuard Flag from the registry that some other plugin registered by the same name.
			// Developer Note: You CAN use the existing flag, but this may cause conflicts - be sure to check type.
			Flag<?> existing = registry.get("can-flippy");
			// If the existing WorldGuard Flag is a StateFlag, do this...
			if (existing instanceof StateFlag) {

				// Return the existing WorldGuard Flag.
				return (StateFlag) existing;

			}
			// If the existing WorldGuard Flag is not a StateFlag, do this...
			else {

				// Throw a custom exception that displays details about the issues with flag registration in the server console.
				throw new FlagRegistrationException("Failed to register can-flippy flag due to conflict.", error);

			}

		}

	}

}