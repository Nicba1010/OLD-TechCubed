package com.nicba1010.techcubed.proxy;

import java.io.IOException;

import net.minecraft.tileentity.TileEntity;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;
import com.nicba1010.techcubed.tileentity.TileEntityCodeDoor;
import com.nicba1010.techcubed.util.LogHelper;

import cpw.mods.fml.common.registry.GameRegistry;

public abstract class CommonProxy implements IProxy {
	public void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityCodeDoor.class,
				"TileEntityCodeDoor");
	}
}