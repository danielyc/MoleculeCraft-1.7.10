package com.daniel_yc.moleculecraft.init;

import com.daniel_yc.moleculecraft.item.CompressionModule;
import com.daniel_yc.moleculecraft.item.CondenserModule;
import com.daniel_yc.moleculecraft.item.ItemMC;
import com.daniel_yc.moleculecraft.item.Module;
import com.daniel_yc.moleculecraft.item.SmeltingModule;
import com.daniel_yc.moleculecraft.reference.Reference;

import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems
{
    public static final ItemMC module = new Module();
    public static final ItemMC smeltingmodule = new SmeltingModule();
    public static final ItemMC compressionmodule = new CompressionModule();
    public static final ItemMC condensermodule = new CondenserModule();
    
    public static void init()
    {
        GameRegistry.registerItem(module, "Module");
        GameRegistry.registerItem(smeltingmodule, "SmeltingModule");
        GameRegistry.registerItem(compressionmodule, "CompressionModule");
        GameRegistry.registerItem(condensermodule, "CondenserModule");
        
    }
}