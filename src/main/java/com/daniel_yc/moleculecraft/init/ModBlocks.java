package com.daniel_yc.moleculecraft.init;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;

import com.daniel_yc.moleculecraft.blocks.BlockMC;
import com.daniel_yc.moleculecraft.blocks.Compressor;
import com.daniel_yc.moleculecraft.blocks.Flag;
import com.daniel_yc.moleculecraft.creativetab.MCTab;
import com.daniel_yc.moleculecraft.reference.Reference;

import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks
{
    public static final BlockMC flag = new Flag();
    public static Block Compressor= new Compressor(false).setBlockName("Compressor").setCreativeTab(MCTab.MCTab);
    public static Block CompressorActive= new Compressor(true);

    public static void init()
    {
        GameRegistry.registerBlock(flag, "Flag");
        GameRegistry.registerBlock(Compressor, "Compressor");
        
    }
}
