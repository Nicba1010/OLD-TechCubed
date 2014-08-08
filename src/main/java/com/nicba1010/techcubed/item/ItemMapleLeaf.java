package com.nicba1010.techcubed.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMapleLeaf extends ItemBase {
	public ItemMapleLeaf() {
		super();
		this.setUnlocalizedName("mapleLeaf");
	}

	@SideOnly(Side.CLIENT)
	public boolean isFull3D() {
		return true;
	}
}