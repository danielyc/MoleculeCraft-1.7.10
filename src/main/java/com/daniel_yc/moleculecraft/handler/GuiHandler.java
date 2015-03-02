package com.daniel_yc.moleculecraft.handler;

import com.daniel_yc.moleculecraft.blocks.tileentity.TileEntityCompressor;
import com.daniel_yc.moleculecraft.gui.GuiCompressor;
import com.daniel_yc.moleculecraft.inventory.ContainerCompressor;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler{
	
public GuiHandler (){
		
	}

@Override
public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
	if(ID == 0){
		TileEntityCompressor tileEntityCompressor = (TileEntityCompressor) world.getTileEntity(x, y, z);
		return new ContainerCompressor(player.inventory, tileEntityCompressor);
	}
	return null;
}

@Override
public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
	if(ID == 0){
		TileEntityCompressor tileEntityCompressor = (TileEntityCompressor) world.getTileEntity(x, y, z);
		return new GuiCompressor(player.inventory, tileEntityCompressor);
	}
	return null;
}
	
}