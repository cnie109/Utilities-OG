package me.barny1094875.utilitiesog.modules;

import me.barny1094875.utilitiesog.Utilities_OG;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;


public class BambooWood {

    public static void Enable() {
        ItemStack bambooWood = new ItemStack(Material.OAK_PLANKS);
        ItemMeta bambooWoodMeta = bambooWood.getItemMeta();
        bambooWoodMeta.displayName(Utilities_OG.getMM().deserialize("Bamboo Wood").decoration(TextDecoration.ITALIC, false));
        bambooWood.setItemMeta(bambooWoodMeta);
        ShapedRecipe BambooWood = new ShapedRecipe(new NamespacedKey(Utilities_OG.getPlugin(), "Bamboo_Wood"),
                bambooWood)
                .shape("bb", "bb")
                .setIngredient('b', Material.BAMBOO);
        Bukkit.addRecipe(BambooWood);
    }

}
