package com.daniel_yc.moleculecraft;

import com.daniel_yc.moleculecraft.handler.ConfigurationHandler;
import com.daniel_yc.moleculecraft.init.ModBlocks;
import com.daniel_yc.moleculecraft.init.ModItems;
import com.daniel_yc.moleculecraft.init.Recipes;
import com.daniel_yc.moleculecraft.proxy.ServerProxy;
import com.daniel_yc.moleculecraft.reference.Reference;
import com.daniel_yc.moleculecraft.utility.Loghelper.LogHelper;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME ,version = Reference.VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)
public class MoleculeCraft
{   

	public static final String Mixer = null;

	@Instance(Reference.MOD_ID)
	public static MoleculeCraft instance;
	
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static ServerProxy proxy;
	
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
		ConfigurationHandler.init(event.getSuggestedConfigurationFile());
		FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
		ModItems.init();
		ModBlocks.init();
		proxy.registerRenderThings();
		proxy.registerTileEntities();
		
		LogHelper.info("MoleculeCraft Pre Initialization Complete");
    }
    
    @EventHandler
    public void Init(FMLInitializationEvent event)
    {
    	Recipes.init();
    	LogHelper.info("MoleculeCraft Initialization Complete");
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    	LogHelper.info("MoleculeCraft Post Initialization Complete");
    }
}
