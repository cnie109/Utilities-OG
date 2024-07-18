# Utilities-OG

A collection of utilities and APIs used by [TrueOG Network](https://true-og.net/). Developed by christianniehaus & NotAlexNoyle.

Current Target: Purpur 1.19.4

## Building:

./gradlew build

The resulting .jar file will be in build/libs

At the moment, any config change requires a server restart to take effect.

## Features:

* **Mock Bamboo:** Enables crafting mock bamboo planks from 2x2 bamboo and mock bamboo wood from 2x2 mock bamboo planks.
* **Chain Armor:** Enables crafting chain armor from chains.
* **Color Codes:** Displays information to players about Bukkit's chat color codes and their syntax.
* **MiniPlaceholderAPI:** Enables a custom, easy-to-use MiniPlaceholders API (**Note:** May require additional configuration for specific placeholders).
* **NoFlippy:** Prevents trapdoors from being flipped in WorldGuard regions where the "can-flippy" flag is set to DENY (Requires WorldGuard plugin).
* **TogglePhantoms:** Allows players to use the `/togglephantoms` command to turn phantom spawning on or off for themselves.
* **Ping:** Provides two commands: `/ping` (displays real player ping) and `/bing` (replicates vanilla /ping functionality).
* **Ranks Menu:** Displays information to players about available ranks at the [TrueOG Network Store](https://store.true-og.net).

**Warning:**

* **DisableEntityCramming (Default: false):** Disables entity cramming damage server-wide. This can cause lag if no other plugin manages entity limits. Enable this Module only if you have a separate plugin managing entity limitations.

## Permissions:

* **`utilities.about`**
    * **Description:** Allows players to use the base `/utilities` command to get information about the plugin.
    * **Default:** `op` (Server Operator)
* **`utilities.colorcodes`**
    * **Description:** Grants permission to use the `/colorcodes` command to view Bukkit chat color code information.
    * **Default:** `op` (Server Operator)
* **`utilities.togglephantoms`**
    * **Description:** Allows players to use the `/togglephantoms` command to enable or disable phantom spawning for themselves.
    * **Default:** `op` (Server Operator)
* **`utilities.togglecramming`**
    * **Description:** Grants permission to use the `/togglecramming` command (if enabled) to manage entity cramming damage.
    * **Default:** `op` (Server Operator)
* **`utilities.ranks`**
    * **Description:** Allows players to use the `/ranks` command to view information about available ranks on the TrueOG Network Store.
    * **Default:** `op` (Server Operator)
* **`utilities.ping`**
    * **Description:** Grants permission to use the `/ping` (real ping) and `/bing` (mimics vanilla ping) commands.
    * **Default:** `op` (Server Operator)

**Please note:** These permissions can be modified in the Utilities-OG configuration file config.yml.

### Features

The plugin offers various functionalities that can be enabled/disabled through the configuration file (`config.yml`).

* **Chain Armor:** Craft chain armor using chains.
* **Color Codes:** Displays information about Bukkit color codes.
* **Disable Entity Cramming:** Server-wide toggle for entity cramming. (**WARNING:** Requires another plugin for entity limits)
* **MiniPlaceholdersAPI:** A custom, lightweight MiniPlaceholders API.
* **Mock Bamboo:** Makes bamboo craft into Oak Planks called "Bamboo Wood" (pre-1.20)
* **NoFlippy:** Prevents trap doors from flipping in WorldGuard regions with `can-flippy` flag set to `DENY`. (Requires WorldGuard)
* **Ping/Bing:** Commands to display player ping.
* **Ranks Menu:** Displays information about ranks available at TrueOG Network Store.
* **Toggle Phantoms:** Allows players to toggle phantom spawning with `/togglephantoms`.
* **About:** Displays plugin information using `/utilities`.

### API Documentation:

**1. trueogSendMessage(Player player, String message)**

Send a message to the player with TrueOG formatting (supports legacy and modern formatting).

```java
Player targetPlayer = Bukkit.getPlayer("USERNAME");
UtilitiesOG.trueogSendMessage(targetPlayer, "&6This is a &*message with <green>True&4OG <bold>formatting!");
```

**2. trueogExpandMiniPlaceholders(Player player, String input)**

Expands MiniPlaceholders within a string for the given player. (**Note:** Requires MiniPlaceholdersAPI to be enabled in the config).

```java
Player targetPlayer = Bukkit.getPlayer("SomePlayer");
String message = "Welcome back, <player_display_name>!";
TextComponent expandedMessage = UtilitiesOG.trueogExpandMiniPlaceholders(targetPlayer, message);
UtilitiesOG.trueogSendMessage(targetPlayer, expandedMessage);
```

**3. ranksCommand():**

Returns a new instance of the RanksCommand class, allowing you to potentially customize its behavior in the future.

```java
RanksCommand ranksCommand = UtilitiesOG.ranksCommand();
// Future configuration here.
```

**4. pingCommand():**

Returns a new instance of the PingCommand class, allowing you to potentially customize its behavior in the future.

```java
PingCommand pingCommand = UtilitiesOG.pingCommand();
// Future configuration here.
```

## TODO:

- LuckPerms colors for <player_display_name>.

- trueOGCreateMiniPlaceholderAPI.

- Customization options for the Ranks and Ping APIs.
