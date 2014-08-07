package com.nicba1010.techcubed.init;

import net.minecraft.block.material.Material;

import com.nicba1010.techcubed.item.ItemBase;
import com.nicba1010.techcubed.item.ItemCodeDoor;
import com.nicba1010.techcubed.item.ItemMapleLeaf;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems {
	public static final ItemBase mapleLeaf = new ItemMapleLeaf();
	public static final ItemBase codeDoorItem = new ItemCodeDoor(Material.iron);

	public static void init() {
		GameRegistry.registerItem(mapleLeaf, "mapleLeaf");
		GameRegistry.registerItem(codeDoorItem, "codeDoorItem");
	}
}
