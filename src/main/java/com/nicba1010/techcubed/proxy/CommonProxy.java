package com.nicba1010.techcubed.proxy;

import com.nicba1010.techcubed.tileentity.TileEntityCodeDoor;

import cpw.mods.fml.common.registry.GameRegistry;

public abstract class CommonProxy implements IProxy {
	public void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityCodeDoor.class,
				"TileEntityCodeDoor");
	}
}