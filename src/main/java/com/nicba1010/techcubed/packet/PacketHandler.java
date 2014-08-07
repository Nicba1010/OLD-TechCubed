package com.nicba1010.techcubed.packet;

import com.nicba1010.techcubed.reference.Reference;
import com.nicba1010.techcubed.util.LogHelper;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler {
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE
			.newSimpleChannel(Reference.MOD_ID.toLowerCase());

	public static void init() {
		LogHelper.debug("registerig messages");
		INSTANCE.registerMessage(PacketDoorCodeEntered.Handler.class,
				PacketDoorCodeEntered.class, 0, Side.SERVER);
	}
}