package me.barny1094875.utilitiesog.modules;

import me.barny1094875.utilitiesog.Utilities_OG;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class ChainArmour {

    public static void Enable() {
        ShapedRecipe ChainHelmet = new ShapedRecipe(new NamespacedKey(Utilities_OG.getPlugin(), "Chainmail_Helmet1"),
                new ItemStack(Material.CHAINMAIL_HELMET))
                .shape("ccc", "c c")
                .setIngredient('c', Material.CHAIN);
        Bukkit.addRecipe(ChainHelmet);

        // recipe for chain chestplate
        ShapedRecipe ChainChestPlate = new ShapedRecipe(new NamespacedKey(Utilities_OG.getPlugin(), "Chainmail_Chestplate"),
                new ItemStack(Material.CHAINMAIL_CHESTPLATE))
                .shape("c c", "ccc", "ccc")
                .setIngredient('c', Material.CHAIN);
        Bukkit.addRecipe(ChainChestPlate);

        // recipe for chain leggings
        ShapedRecipe ChainLeggings = new ShapedRecipe(new NamespacedKey(Utilities_OG.getPlugin(), "Chainmail_Leggings"),
                new ItemStack(Material.CHAINMAIL_LEGGINGS))
                .shape("ccc", "c c", "c c")
                .setIngredient('c', Material.CHAIN);
        Bukkit.addRecipe(ChainLeggings);

        // recipe for chain boots
        ShapedRecipe ChainBoots = new ShapedRecipe(new NamespacedKey(Utilities_OG.getPlugin(), "Chainmail_Boots1"),
                new ItemStack(Material.CHAINMAIL_BOOTS))
                .shape("c c", "c c")
                .setIngredient('c', Material.CHAIN);
        Bukkit.addRecipe(ChainBoots);
    }

}
