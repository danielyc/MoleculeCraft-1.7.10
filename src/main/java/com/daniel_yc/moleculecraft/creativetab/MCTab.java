package com.daniel_yc.moleculecraft.creativetab;

import com.daniel_yc.moleculecraft.init.ModBlocks;
import com.daniel_yc.moleculecraft.init.ModItems;
import com.daniel_yc.moleculecraft.reference.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class MCTab 
{

	public static final CreativeTabs MCTab = new CreativeTabs(Reference.MOD_ID.toLowerCase())
	{
		
		@Override
		public Item getTabIconItem()
		{
			//return ModItems.module;
			return Item.getItemFromBlock(ModBlocks.flag);
		}
		
	};
}
