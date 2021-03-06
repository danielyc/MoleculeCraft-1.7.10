package com.daniel_yc.moleculecraft.client.gui;

import com.daniel_yc.moleculecraft.handler.ConfigurationHandler;
import com.daniel_yc.moleculecraft.reference.Reference;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.client.config.GuiConfig;

public class ModGuiConfig extends GuiConfig{
	
	 public ModGuiConfig(GuiScreen guiScreen)
	    {
	        super(guiScreen,
	                new ConfigElement(ConfigurationHandler.configuration.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(),
	                Reference.MOD_ID,
	                false,
	                false,
	                GuiConfig.getAbridgedConfigPath(ConfigurationHandler.configuration.toString()));
	    }
}
