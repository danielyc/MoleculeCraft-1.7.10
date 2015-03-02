package com.daniel_yc.moleculecraft.blocks.tileentity;

import com.daniel_yc.moleculecraft.blocks.Compressor;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityCompressor extends TileEntity implements ISidedInventory{
	
	private static final int[] slotsTop = new int[] { 0 };
	private static final int[] slotsBottom = new int[] { 2, 1 };
	private static final int[] slotsSides = new int[] { 1 };

	private ItemStack[] compressorItemStacks = new ItemStack[3];
	
	public int compressorBurnTime;
	public int currentBurnTime;
	public int compressorCookTime;
	
	private String compressorName;
	
	public void compressorName(String string){
		this.compressorName = string;
	}
	
	@Override
	public int getSizeInventory() {
		return this.compressorItemStacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return this.compressorItemStacks[slot];
	}

	@Override
	public ItemStack decrStackSize(int par1, int par2) {
		if(this.compressorItemStacks[par1] != null){
			ItemStack itemstack;
			if(this.compressorItemStacks[par1].stackSize <= par2){
				itemstack = this.compressorItemStacks[par1];
				this.compressorItemStacks[par1] = null;
				return itemstack;
			}else{
				itemstack = this.compressorItemStacks[par1].splitStack(par2);
				
				if(this.compressorItemStacks[par1].stackSize == 0){
					this.compressorItemStacks[par1] = null;
				}
			return itemstack;
			}
		}else{
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		if(this.compressorItemStacks[slot] != null){
			ItemStack itemstack = this.compressorItemStacks[slot];
			this.compressorItemStacks[slot] = null;
			return itemstack;
		}else{
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack itemstack) {
		this.compressorItemStacks[slot] = itemstack;
		
		if(itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()){
			itemstack.stackSize = this.getInventoryStackLimit();
		}
		
	}

	@Override
	public String getInventoryName() {
		return this.hasCustomInventoryName() ? this.compressorName : "Compressor";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return this.compressorName != null && this.compressorName.length() > 0;
	}

	@Override
	public int getInventoryStackLimit() {
		
		return 64;
	}
	
	public void readFromNBT(NBTTagCompound tagCompound){
		super.readFromNBT(tagCompound);
		NBTTagList tagList = tagCompound.getTagList("Items", 10);
		this.compressorItemStacks = new ItemStack[this.getSizeInventory()];
		
		for (int i = 0; i < tagList.tagCount(); ++i){
			NBTTagCompound tabCompound1 = tagList.getCompoundTagAt(i);
			byte byte0 = tabCompound1.getByte("slot");
			
			if(byte0 >= 0 && byte0 < this.compressorItemStacks.length){
				this.compressorItemStacks[byte0] = ItemStack.loadItemStackFromNBT(tabCompound1);
			}
		}
		
		this.compressorBurnTime = tagCompound.getShort("BurnTime");
		this.compressorCookTime = tagCompound.getShort("CookTime");
		this.currentBurnTime = getItemBurnTime(this.compressorItemStacks[1]);
		
		if(tagCompound.hasKey("CustumName", 8)){
			this.compressorName = tagCompound.getString("CustomName");
		}
	}
	
	public void writeToNBT(NBTTagCompound tagCompound){
		super.writeToNBT(tagCompound);
		
		tagCompound.setShort("BurnTime", (short) this.compressorBurnTime);
		tagCompound.setShort("CookTime", (short) this.compressorBurnTime);
		NBTTagList tagList = new NBTTagList();
		
		for(int i = 0; i < this.compressorItemStacks.length; ++i){
			if(this.compressorItemStacks[1] != null){
				NBTTagCompound tagCompound1 = new NBTTagCompound();
				tagCompound1.setByte("slot", (byte) i);
				this.compressorItemStacks[1].writeToNBT(tagCompound1);
				tagList.appendTag(tagCompound1);
			}
		}
		
		tagCompound.setTag("Items", tagList);
		
		if(this.hasCustomInventoryName()){
			tagCompound.setString("CustomName", this.compressorName);
		}
	}
	
	@SideOnly(Side.CLIENT)
	public int getCookProgressScaled(int par1){
		return this.compressorCookTime * par1 / 200;
	}
	
	@SideOnly(Side.CLIENT)
	public int getBurnTimeRemainingScaled(int par1){
		if(this.currentBurnTime == 0){
			this.currentBurnTime = 200;
		}
		
		return this.compressorBurnTime * par1 / this.currentBurnTime;
	}
	
	public boolean isWorking(){
		return this.compressorBurnTime > 0;
	}
	
	public void updateEntity(){
		boolean flag = this.compressorBurnTime > 0;
		boolean flag1 = false;
		
		if(this.compressorBurnTime > 0){
			--this.compressorBurnTime;
		}
		
		if(!this.worldObj.isRemote){
			if(this.compressorBurnTime == 0 && this.canSmelt()){
				this.currentBurnTime = this.compressorBurnTime = getItemBurnTime(this.compressorItemStacks[1]);
				
				if(this.compressorBurnTime > 0){
					flag1 = true;
					if(this.compressorItemStacks[1] != null){
						--this.compressorItemStacks[1].stackSize;
						
						if(this.compressorItemStacks[1].stackSize == 0){
							this.compressorItemStacks[1] = compressorItemStacks[1].getItem().getContainerItem(this.compressorItemStacks[1]);
						}
					}
				}
			}
			
			if(this.isWorking() && this.canSmelt()){
				++this.compressorCookTime;
				if(this.compressorCookTime == 200){
					this.compressorCookTime = 0;
					this.smeltItem();
					flag1 = true;
				}
			}else{
				this.compressorCookTime = 0;
			}
		}
		
		if(flag1 != this.compressorBurnTime > 0){
			flag1 = true;
			Compressor.updateBlockState(this.compressorBurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
		}
		
		if (flag1){
			this.markDirty();
		}
	}
	
	private boolean canSmelt(){
		if(this.compressorItemStacks[0] == null){
			return false;
		}else{
			ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.compressorItemStacks[0]);
			if(itemstack == null) return false;
			if(this.compressorItemStacks[2] == null) return true;
			if(!this.compressorItemStacks[2].isItemEqual(itemstack)) return false;
			int result = compressorItemStacks[2].stackSize + itemstack.stackSize;
			return result <= getInventoryStackLimit() && result <= this.compressorItemStacks[2].getMaxStackSize();
		}
	}
	
	public void smeltItem(){
		if(this.canSmelt()){
			ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.compressorItemStacks[0]);
			
			if(this.compressorItemStacks[2] == null){
				this.compressorItemStacks[2].copy();
			}else if(this.compressorItemStacks[2].getItem() == itemstack.getItem()){
				this.compressorItemStacks[2].stackSize += itemstack.stackSize;
			}
		}
	}
	
	public static int getItemBurnTime(ItemStack itemstack){
		if(itemstack == null){
			return 0;
		}else{
			Item item = itemstack.getItem();
			
			if(item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air){
				Block block = Block.getBlockFromItem(item);
				
				if(block == Blocks.coal_block){
					return 1600;
				}
				
				if(block.getMaterial() == Material.wood){
					return 300;
				}
			}
			
			if(item == Items.coal) return 600;
			if(item instanceof ItemTool && ((ItemTool) item).getToolMaterialName().equals("WOOD")) return 300;
			return GameRegistry.getFuelValue(itemstack);
		}
	}

	public static boolean isItemFuel(ItemStack itemstack){
		return getItemBurnTime(itemstack) > 0;
	}
	
	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {

		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : player.getDistanceSq((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D) <= 64.0D; 
	}

	@Override
	public void openInventory() {
		
	}

	@Override
	public void closeInventory() {
		
	}

	@Override
	public boolean isItemValidForSlot(int par1, ItemStack itemstack) {
		return par1 == 2 ? false : (par1 == 1 ? isItemFuel(itemstack) : true);
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int par1) {
		return par1 == 0 ? slotsBottom : (par1 == 1 ? slotsTop : slotsSides);
	}

	@Override
	public boolean canInsertItem(int par1, ItemStack itemstack, int par3) {
		return this.isItemValidForSlot(par1, itemstack);
	}

	@Override
	public boolean canExtractItem(int par1, ItemStack itemstack, int par3) {
		return par3 != 0 || par1 != 1 || itemstack.getItem() == Items.bucket;
	}

}
