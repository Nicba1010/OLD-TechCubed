package com.nicba1010.techcubed.handler;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import com.nicba1010.techcubed.reference.Reference;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ConfigurationHandler {
	public static Configuration configuration;
	public static boolean testValue = false;

	public static void init(File configFile) {
		// Create the configuration object from the given configuration file
		if (configuration == null) {
			configuration = new Configuration(configFile);
			loadConfiguration();
		}
	}

	private static void loadConfiguration() {
		testValue = configuration.getBoolean("configValue",
				Configuration.CATEGORY_GENERAL, false,
				"This is an example configuration value");

		if (configuration.hasChanged()) {
			configuration.save();
		}
	}

	@SubscribeEvent
	public void onConfigurationChangedEvent(
			ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.modID.equalsIgnoreCase(Reference.MOD_ID)) {
			loadConfiguration();
		}
	}
}