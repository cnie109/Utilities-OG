// This is free and unencumbered software released into the public domain.
// Authors: christianniehaus, NotAlexNoyle.
package me.barny1094875.utilitiesog.modules;

// Import libraries.
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import me.barny1094875.utilitiesog.UtilitiesOG;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;

// Declare the Mock Bamboo Wood Module.
public class MockBambooModule {

	// Easy to call function to run all the code in this Module.
	public static void Enable() {

		// Create a novel item, Bamboo Planks, based on Oak Planks.
		ItemStack bambooPlanks = new ItemStack(Material.OAK_PLANKS);

		// Fetch the meta of the mock Bamboo Planks for editing.
		ItemMeta bambooPlanksMeta = bambooPlanks.getItemMeta();

		// Gives the mock Bamboo Planks a formatted title.
		bambooPlanksMeta.displayName(MiniMessage.miniMessage().deserialize("<green>Bamboo Planks").decoration(TextDecoration.ITALIC, false));

		// Applies the custom display name to the item.
		bambooPlanks.setItemMeta(bambooPlanksMeta);

		// Defines a crafting recipe for Bamboo Planks.
		ShapedRecipe BambooPlanks = new ShapedRecipe(new NamespacedKey(UtilitiesOG.getPlugin(), "Bamboo_Planks"),
				bambooPlanks).shape("bb", "bb")
				// Use Bamboo as the base ingredient.
				.setIngredient('b', Material.BAMBOO);

		// Adds the crafting recipe for Bamboo Planks to the server.
		Bukkit.addRecipe(BambooPlanks);

		// Create a novel item, Bamboo Wood, based on Birch Wood.
		ItemStack imitationBambooWood = new ItemStack(Material.BIRCH_WOOD); 

		// Fetch the meta of the mock Bamboo Wood for editing.
		ItemMeta imitationBambooWoodMeta = imitationBambooWood.getItemMeta();

		// Gives the mock Bamboo Wood a formatted title.
		imitationBambooWoodMeta.displayName(MiniMessage.miniMessage().deserialize("<green>Bamboo Wood").decoration(TextDecoration.ITALIC, false));

		// Applies the display name to the mock Bamboo Wood.
		imitationBambooWood.setItemMeta(imitationBambooWoodMeta);

		// Define a crafting recipe for mock Bamboo Wood.
		ShapedRecipe imitationBambooWoodRecipe = new ShapedRecipe(new NamespacedKey(UtilitiesOG.getPlugin(), "Imitation_Bamboo_Wood"),
				imitationBambooWood).shape("bb", "bb")
				// Use the previously created bamboo planks as the base ingredient.
				.setIngredient('b', bambooPlanks);

		// Add the crafting recipe for mock Bamboo Wood to the server.
		Bukkit.addRecipe(imitationBambooWoodRecipe);

	}

}