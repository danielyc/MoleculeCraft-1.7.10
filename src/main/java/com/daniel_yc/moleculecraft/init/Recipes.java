package com.daniel_yc.moleculecraft.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class Recipes 
{

	public static void init()
	{
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.module), "rir", "idi", "rir", 'r', new ItemStack(Items.redstone), 'd', new ItemStack(Items.diamond), 'i', new ItemStack(Items.iron_ingot)));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.flag), new ItemStack(Blocks.wool), new ItemStack(Items.stick)));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.condensermodule), "rfr", "bmb", "rfr", 'r', new ItemStack(Items.redstone), 'f', new ItemStack(Blocks.fire), 'b', new ItemStack(Items.bucket), 'm', new ItemStack(ModItems.module)));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Blocks.fire), new ItemStack(Items.flint_and_steel)));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.compressionmodule), "rpr", "pmp", "rpr" , 'r', new ItemStack(Items.redstone), 'p', new ItemStack(Blocks.piston), 'm', new ItemStack(ModItems.module)));
		GameRegistry.addSmelting(ModItems.module , new ItemStack(ModItems.smeltingmodule), 500F);
	}
}
