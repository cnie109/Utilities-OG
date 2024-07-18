// This is free and unencumbered software released into the public domain.
// Authors: christianniehaus, NotAlexNoyle.
package me.barny1094875.utilitiesog.modules;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

// Import libraries.
import me.barny1094875.utilitiesog.UtilitiesOG;

// Declare the Chain Armor Module.
public class ChainArmorModule {

	// Easy to call function to run all the code in this Module.
	public static void Enable() {

		// Defines a crafting recipe for Chainmail Helmet from Chains.
		ShapedRecipe ChainHelmet = new ShapedRecipe(new NamespacedKey(UtilitiesOG.getPlugin(), "Chainmail_Helmet1"),
				new ItemStack(Material.CHAINMAIL_HELMET))
				.shape("ccc", "c c")
				.setIngredient('c', Material.CHAIN);

		// Defines a crafting recipe for Chainmail Chestplate from Chains.
		ShapedRecipe ChainChestPlate = new ShapedRecipe(new NamespacedKey(UtilitiesOG.getPlugin(), "Chainmail_Chestplate"),
				new ItemStack(Material.CHAINMAIL_CHESTPLATE))
				.shape("c c", "ccc", "ccc")
				.setIngredient('c', Material.CHAIN);

		// Defines a crafting recipe for Chainmail Leggings from Chains.
		ShapedRecipe ChainLeggings = new ShapedRecipe(new NamespacedKey(UtilitiesOG.getPlugin(), "Chainmail_Leggings"),
				new ItemStack(Material.CHAINMAIL_LEGGINGS))
				.shape("ccc", "c c", "c c")
				.setIngredient('c', Material.CHAIN);

		// Defines a crafting recipe for Chainmail Boots from Chains.
		ShapedRecipe ChainBoots = new ShapedRecipe(new NamespacedKey(UtilitiesOG.getPlugin(), "Chainmail_Boots1"),
				new ItemStack(Material.CHAINMAIL_BOOTS))
				.shape("c c", "c c")
				.setIngredient('c', Material.CHAIN);

		// Add the Chainmail Armor crafting recipes to the server.
		Bukkit.addRecipe(ChainHelmet);
		Bukkit.addRecipe(ChainChestPlate);
		Bukkit.addRecipe(ChainLeggings);
		Bukkit.addRecipe(ChainBoots);

	}

}