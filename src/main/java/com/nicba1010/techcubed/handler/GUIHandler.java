package com.nicba1010.techcubed.handler;

import com.nicba1010.techcubed.client.gui.GuiCodeDoor;
import com.nicba1010.techcubed.tileentity.TileEntityCodeDoor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GUIHandler implements IGuiHandler {

	/**
	 * Gets the server element. This means, do something server side, when this
	 * ID gets called.
	 */
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		switch (ID) {
		case 0:
			return null;
		default:
			break;
		}
		return null;
	}

	/**
	 * Gets the client element. This means, do something client side, when this
	 * ID gets called.
	 */
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		switch (ID) {
		case 1:
			return new GuiCodeDoor((TileEntityCodeDoor) world.getTileEntity(x,
					y, z));
		default:
			break;
		}
		return null;
	}

}
