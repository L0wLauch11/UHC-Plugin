package me.lowlauch.uhc;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class CustomRecipes
{
    public static void initRecipes()
    {
        ItemStack strengthBottle = new ItemStack(Material.POTION, 1, (short) 8201);
        ItemStack goldenApple = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemStack activeDiamond = new ItemStack(Material.BARRIER, 1);
        ItemStack enchantedDiamondSword = new ItemStack(Material.DIAMOND_SWORD, 1);
        ItemStack enchantedDiamondPickaxe = new ItemStack(Material.DIAMOND_PICKAXE, 1);

        ItemStack stonePickaxe = new ItemStack(Material.STONE_PICKAXE, 1);
        ItemStack stoneAxe = new ItemStack(Material.STONE_AXE, 1);
        ItemStack stoneSword = new ItemStack(Material.STONE_SWORD, 1);
        ItemStack stoneShovel = new ItemStack(Material.STONE_SPADE, 1);
        ItemStack stoneHoe = new ItemStack(Material.STONE_HOE, 1);

        enchantedDiamondSword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
        enchantedDiamondPickaxe.addEnchantment(Enchantment.DIG_SPEED, 1);
        stonePickaxe.addEnchantment(Enchantment.DIG_SPEED, 1);
        stoneAxe.addEnchantment(Enchantment.DIG_SPEED, 1);
        stoneShovel.addEnchantment(Enchantment.DIG_SPEED, 1);

        ShapedRecipe goldenAppleRecipe = new ShapedRecipe(goldenApple);
        ShapedRecipe strengthBottleRecipe = new ShapedRecipe(strengthBottle);
        ShapedRecipe activeDiamondRecipe = new ShapedRecipe(activeDiamond);
        ShapedRecipe enchantedDiamondSwordRecipe = new ShapedRecipe(enchantedDiamondSword);
        ShapedRecipe enchantedDiamondPickaxeRecipe = new ShapedRecipe(enchantedDiamondPickaxe);

        ShapedRecipe stonePickaxeRecipe = new ShapedRecipe(stonePickaxe);
        ShapedRecipe stoneAxeRecipe = new ShapedRecipe(stoneAxe);
        ShapedRecipe stoneSwordRecipe = new ShapedRecipe(stoneSword);
        ShapedRecipe stoneShovelRecipe = new ShapedRecipe(stoneShovel);
        ShapedRecipe stoneHoeRecipe = new ShapedRecipe(stoneHoe);

        strengthBottleRecipe.shape(" F ", "EEE", " B ");
        goldenAppleRecipe.shape("UWU", "WOW", "UWU");
        activeDiamondRecipe.shape("BBB", "BBB", "ZBB");
        enchantedDiamondSwordRecipe.shape("HGH", "HGH", "XYX");
        enchantedDiamondPickaxeRecipe.shape("GGG", "UWU", "UWU");

        stonePickaxeRecipe.shape("   ", " - ", "   ");
        stoneAxeRecipe.shape("   ", " L ", "   ");
        stoneSwordRecipe.shape("   ", " 2 ", "   ");
        stoneShovelRecipe.shape("   ", " 7 ", "   ");
        stoneHoeRecipe.shape("   ", " 4 ", "   ");

        stonePickaxeRecipe.setIngredient('-', Material.WOOD_PICKAXE);
        stoneAxeRecipe.setIngredient('L', Material.WOOD_AXE);
        stoneSwordRecipe.setIngredient('2', Material.WOOD_SWORD);
        stoneShovelRecipe.setIngredient('7', Material.WOOD_SPADE);
        stoneHoeRecipe.setIngredient('4', Material.WOOD_HOE);

        strengthBottleRecipe.setIngredient('F', Material.REDSTONE);
        strengthBottleRecipe.setIngredient('E', Material.DIAMOND);
        strengthBottleRecipe.setIngredient('B', Material.GLASS_BOTTLE);

        goldenAppleRecipe.setIngredient('W', Material.GOLD_INGOT);
        goldenAppleRecipe.setIngredient('O', Material.APPLE);

        activeDiamondRecipe.setIngredient('Z', Material.DIAMOND);

        enchantedDiamondSwordRecipe.setIngredient('G', Material.BARRIER);
        enchantedDiamondSwordRecipe.setIngredient('Y', Material.STICK);

        enchantedDiamondPickaxeRecipe.setIngredient('G', Material.BARRIER);
        enchantedDiamondPickaxeRecipe.setIngredient('W', Material.STICK);

        Bukkit.getServer().addRecipe(strengthBottleRecipe);
        Bukkit.getServer().addRecipe(goldenAppleRecipe);
        Bukkit.getServer().addRecipe(activeDiamondRecipe);
        Bukkit.getServer().addRecipe(enchantedDiamondSwordRecipe);
        Bukkit.getServer().addRecipe(enchantedDiamondPickaxeRecipe);

        Bukkit.getServer().addRecipe(stonePickaxeRecipe);
        Bukkit.getServer().addRecipe(stoneAxeRecipe);
        Bukkit.getServer().addRecipe(stoneSwordRecipe);
        Bukkit.getServer().addRecipe(stoneShovelRecipe);
        Bukkit.getServer().addRecipe(stoneHoeRecipe);
    }
}
