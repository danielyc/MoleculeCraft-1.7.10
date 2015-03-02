package com.daniel_yc.moleculecraft.item;


import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class Module extends ItemMC
{
	public Module() 
	{
		super();
		this.setUnlocalizedName("Module");

	}
	
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{
		par3List.add("This item doesn't do anything");
	}
}
