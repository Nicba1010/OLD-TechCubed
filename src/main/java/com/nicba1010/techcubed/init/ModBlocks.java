package com.nicba1010.techcubed.init;

import sun.font.SunFontManager.FamilyDescription;
import net.minecraft.block.material.Material;

import com.nicba1010.techcubed.block.BlockBase;
import com.nicba1010.techcubed.block.BlockCodeDoor;
import com.nicba1010.techcubed.block.BlockFlag;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks {
	public static final BlockBase flag = new BlockFlag();
	public static final BlockBase codeDoor = new BlockCodeDoor(Material.iron);

	public static void init() {
		GameRegistry.registerBlock(flag, "flag");
		GameRegistry.registerBlock(codeDoor, "codeDoor");
	}
}
