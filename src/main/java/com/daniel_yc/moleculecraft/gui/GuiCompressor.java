package com.daniel_yc.moleculecraft.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import com.daniel_yc.moleculecraft.blocks.tileentity.TileEntityCompressor;
import com.daniel_yc.moleculecraft.inventory.ContainerCompressor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiCompressor extends GuiContainer{
	
	public static final ResourceLocation CompressorGuiTextures = new ResourceLocation("textures/gui/container");
	private TileEntityCompressor tileCompressor;

	public GuiCompressor(InventoryPlayer invPlayer, TileEntityCompressor tile) {
		super(new ContainerCompressor(invPlayer, tile));
		this.tileCompressor = tile;
		
	}
	
	protected void drawGuiContainerForegroundLayer(int par1, int par2){
		String string = this.tileCompressor.hasCustomInventoryName() ? this.tileCompressor.getInventoryName() : I18n.format(this.tileCompressor.getInventoryName(), new Object[0]);
		this.fontRendererObj.drawString(string, this.xSize / 2 - this.fontRendererObj.getStringWidth(string), 6, 4210725);
		this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 94, 4210725);
		
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int car3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(CompressorGuiTextures);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
		int i1;
		
		if(this.tileCompressor.isWorking()){
			i1 = this.tileCompressor.getBurnTimeRemainingScaled(12);
			this.drawTexturedModalRect(k + 56, l + 36 + 12 - 11, 176, 12 - i1, 14, i1 + 2);
		}
		
		i1 = this.tileCompressor.getCookProgressScaled(24);
		this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
	}

}
