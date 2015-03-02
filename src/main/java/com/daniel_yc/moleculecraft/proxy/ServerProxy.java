package com.daniel_yc.moleculecraft.proxy;

import com.daniel_yc.moleculecraft.MoleculeCraft;
import com.daniel_yc.moleculecraft.blocks.tileentity.TileEntityCompressor;
import com.daniel_yc.moleculecraft.reference.Reference;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class ServerProxy {
	
	public void registerRenderThings(){
		
		
	}
	
	public void registerNetworkStuff(){
	//	NetworkRegistry.INSTANCE.registerGuiHandler(MoleculeCraft.instance, new GuiHandler);
	}
	
	public void registerTileEntities(){
		GameRegistry.registerTileEntity(TileEntityCompressor.class, Reference.MOD_ID + "TileEntityCompressor");
	}

}
