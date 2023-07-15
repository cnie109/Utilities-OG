package me.barny1094875.utilitiesog.Commands;

import me.barny1094875.utilitiesog.Utilities_OG;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class UtilitiesCommand implements CommandExecutor
{

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args)
    {

        if (command.getName().equalsIgnoreCase("utilities"))
        {
            if (args[0].equalsIgnoreCase("togglephantoms"))
            {
                if (Utilities_OG.config().getBoolean("PhantomToggle"))
                {
                    PhantomToggleCommand.run(sender);
                }
            }

            else if (args[0].equalsIgnoreCase("togglecramming"))
            {
                if (Utilities_OG.config().getBoolean("EntityCrammingDisable"))
                {
                    EntityCrammingToggleCommand.run(sender);
                }
            }
        }

        return true;
    }

}