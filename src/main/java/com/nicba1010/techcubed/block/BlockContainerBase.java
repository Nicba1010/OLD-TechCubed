package com.nicba1010.techcubed.block;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class BlockContainerBase extends BlockBase implements
		ITileEntityProvider {

	protected BlockContainerBase(Material material) {
		super(material);
		this.isBlockContainer = true;
	}

	/**
	 * Called whenever the block is added into the world. Args: world, x, y, z
	 */
	public void onBlockAdded(World world, int x,
			int y, int z) {
		super.onBlockAdded(world, x, y, z);
	}

	public void breakBlock(World world, int x, int y,
			int z, Block block, int p_149749_6_) {
		super.breakBlock(world, x, y, z,
				block, p_149749_6_);
		world.removeTileEntity(x, y, z);
	}

	public boolean onBlockEventReceived(World world, int x,
			int y, int z, int p_149696_5_, int p_149696_6_) {
		super.onBlockEventReceived(world, x, y,
				z, p_149696_5_, p_149696_6_);
		TileEntity tileentity = world.getTileEntity(x,
				y, z);
		return tileentity != null ? tileentity.receiveClientEvent(p_149696_5_,
				p_149696_6_) : false;
	}
}