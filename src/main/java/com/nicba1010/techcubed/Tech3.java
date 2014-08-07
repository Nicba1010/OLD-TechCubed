package com.nicba1010.techcubed;

import com.nicba1010.techcubed.handler.ConfigurationHandler;
import com.nicba1010.techcubed.handler.GUIHandler;
import com.nicba1010.techcubed.init.ModBlocks;
import com.nicba1010.techcubed.init.ModItems;
import com.nicba1010.techcubed.packet.PacketHandler;
import com.nicba1010.techcubed.proxy.IProxy;
import com.nicba1010.techcubed.reference.Reference;
import com.nicba1010.techcubed.util.LogHelper;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)
public class Tech3 {
	@Mod.Instance(Reference.MOD_ID)
	public static Tech3 instance;

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static IProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ConfigurationHandler.init(event.getSuggestedConfigurationFile());
		FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
		LogHelper.info("Pre Initialization Complete!");

		ModItems.init();

		ModBlocks.init();

		proxy.registerTileEntities();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GUIHandler());
		{
			PacketHandler.init();
		}
		LogHelper.info("Initialization Complete!");
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		LogHelper.info("Post Initialization Complete!");
	}
}