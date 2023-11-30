// This is free and unencumbered software released into the public domain.
// Authors: christianniehaus, NotAlexNoyle.
package me.barny1094875.utilitiesog.modules;

import me.barny1094875.utilitiesog.Utilities_OG;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

// Initialize command manager class.
public class RanksModule implements CommandExecutor {

    // Command execution event handler extending bukkit's CommandManager.
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        // Get the player object from the sender.
        Player player = (Player) sender;
        if (sender instanceof Player) {
            if (player.hasPermission("utilities.ranks")) {
                // If the player did not provide any arguments, do this...
                if (args.length == 0) {
                    // Send the available ranks to the user.
                    player.sendMessage(Utilities_OG.getMM().deserialize("<bold><dark_purple>-=-=-=-=- <dark_green>True<dark_red>OG <aqua>Donation Store: <dark_purple>-=-=-=-=-"));
                    player.sendMessage("");
                    player.sendMessage("Each donor rank requires the rank before it.");
                    player.sendMessage("");
                    player.sendMessage(Utilities_OG.getMM().deserialize("<green>Rank Overview: <dark_aqua><underlined>https://shop.true-og.net"));
                    player.sendMessage("");
                    player.sendMessage(Utilities_OG.getMM().deserialize("<white>[OG] <gray>(<dark_green>$10 Once<gray>) <dark_aqua><underlined>https://true-og.net/og"));
                    sendClickableMessage(player, "/ranks og");
                    player.sendMessage("");
                    player.sendMessage(Utilities_OG.getMM().deserialize("<red>[OG Pro] <gray>(<dark_green>$50 Once<gray>) <dark_aqua><underlined>https://true-og.net/og-pro"));
                    sendClickableMessage(player, "/ranks og-pro");
                    player.sendMessage("");
                    player.sendMessage(Utilities_OG.getMM().deserialize("<dark_red><bold>[OG Master] <gray>(<gold>$10 Monthly<gray>) <dark_aqua><underlined>https://true-og.net/og-master"));
                    sendClickableMessage(player, "/ranks og-master");
                }
                // If the player provided one or more arguments, do this...
                else if (args.length == 1) {
                    // If the OG text was clicked, do this...
                    if (args[0].equalsIgnoreCase("og")) {
                        // Share the OG benefits with the user.
                        player.sendMessage(Utilities_OG.getMM().deserialize("<dark_purple><bold>-=-=-=-=- <dark_green>True<dark_red>OG <white>OG Rank: <dark_purple>-=-=-=-=-"));
                        player.sendMessage("- Use /afk for an unlimited amount of time.");
                        player.sendMessage("- Prominent white chat prefix");
                        player.sendMessage("- Create unions with /union create.");
                        player.sendMessage("- Invite people to unions with /union invite.");
                        player.sendMessage("- Access to #donor-chat on Discord.");
                        player.sendMessage("- 6 homes (temporary benefit until the implementation of our in-game home earning plugin).");
                        player.sendMessage("- More TBA.");
                    }
                    // If the OG Pro text was clicked, do this...
                    else if (args[0].equalsIgnoreCase("og-pro")) {
                        // Share the OG Pro benefits with the user.
                        player.sendMessage(Utilities_OG.getMM().deserialize("<dark_purple><bold>-=-=-=-=- <dark_green>True&<dark_red>OG <red>OG Pro Rank: <dark_purple>-=-=-=-=-"));
                        player.sendMessage("- All OG Benefits.");
                        player.sendMessage("- Rent up to 2 shops at the player market.");
                        player.sendMessage("- Special chat formatting (bold, italics, magic, etc).");
                        player.sendMessage("- Use color codes in chat, mail, unions, signs, anvils, and book titles.");
                        player.sendMessage("- Edit written books with /book.");
                        player.sendMessage("- Edit signs with /editsign.");
                        player.sendMessage("- Wear hats with /hat.");
                        player.sendMessage("- Sit anywhere with /sit.");
                        player.sendMessage("- Access your ender chest anywhere with /echest.");
                        player.sendMessage("- Use 9 exclusive particle cosmetics with /cosmetics (up to 3 at once).");
                        player.sendMessage("- More TBA.");
                    }
                    // If the OG Master text was clicked, do this...
                    else if (args[0].equalsIgnoreCase("og-master")) {
                        // Share the OG Master benefits to the user.
                        player.sendMessage(Utilities_OG.getMM().deserialize("<dark_purple><bold>-=-=-=-=- <dark_green>True<dark_red>OG <dark_red>OG Master Rank: <dark_purple>-=-=-=-=-"));
                        player.sendMessage("- All OG and OG Pro Benefits.");
                        player.sendMessage("- Rent up to 3 shops at the player market.");
                        player.sendMessage("- World inspection with /co inspect.");
                        player.sendMessage("- Return to last teleport destination with /back.");
                        player.sendMessage("- Toggle your death coordinates in chat with /tdc.");
                        player.sendMessage("- Special mail, sign, and anvil formatting (strike-through, italics, magic, etc).");
                        player.sendMessage("- More TBA.");
                    }
                } else {

                    // Send the player an error message if they typed too many command arguments.
                    player.sendMessage(Utilities_OG.getMM().deserialize("<gray>[<dark_green>True<dark_red>OG<gray>] <red>ERROR: Too many arguments! <gold>Syntax: <yellow>/ranks"));

                }
            }
        }
        // Healthy exit status.
        return true;
    }

    // Turns chat text into a command button.
    public static void sendClickableMessage(Player player, String command) {

        // Declare a TextComponent with contents.
        TextComponent clickableText = (TextComponent) Utilities_OG.getMM().deserialize("<aqua><bold>Click here to see the benefits!");

        // Create a click event on the chat text.
        clickableText = clickableText.clickEvent(ClickEvent.clickEvent(ClickEvent.Action.RUN_COMMAND, command));

        // Send the clickable text to the player.
        player.sendMessage(clickableText);

    }

}