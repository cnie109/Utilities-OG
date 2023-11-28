package me.barny1094875.utilitiesog.modules;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import me.barny1094875.utilitiesog.Utilities_OG;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class NoFlippy implements Listener {

    // Declare instance of class as static so multiple players can use it.
    private static NoFlippy instance;

    // Return instance of class as static so multiple players can use it.
    public static NoFlippy getInstance() {

        // Pass back Listeners to main.
        return instance;

    }

    // Listen for a player interacting with a trapdoor.
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

        } catch (NullPointerException error) {

            // Do nothing if block type is null.
            return;

        }

        // If the interaction was with a trap door, do this...
        if (blockContainerAsString.contains("TRAPDOOR")) {

            // If the interaction was a right click, do this...
            if (action.isRightClick()) ;
            {

                // If the player does not have permission to flip trap doors, do this...
                if (!player.hasPermission("noflippy.bypass")) {

                    Location blockLocation = BukkitAdapter.adapt(blockClicked.getLocation());
                    RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
                    RegionQuery query = container.createQuery();
                    ApplicableRegionSet set = query.getApplicableRegions(blockLocation);

                    // If the Flippy Flag is set to DENY, cancel the event.
                    if (!set.testState(null, Utilities_OG.getFlippyFlag())) {

                        // Cancel the trapdoor flip.
                        event.setCancelled(true);

                    }

                }

            }

        }

    }

}
