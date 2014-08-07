package com.nicba1010.techcubed.item;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.nicba1010.techcubed.TechCubed;
import com.nicba1010.techcubed.init.ModBlocks;

public class ItemCodeDoor extends ItemBase {
	private Material doorMaterial;
	private static final String __OBFID = "CL_00000020";

	public ItemCodeDoor(Material material) {
		this.doorMaterial = material;
		this.maxStackSize = 1;
		this.setUnlocalizedName("codeDoorItem");
	}

	/**
	 * Callback for item usage. If the item does something special on right
	 * clicking, he will have one of those. Return True if something happen and
	 * false if it don't. This is for ITEMS, not BLOCKS
	 */
	public boolean onItemUse(ItemStack itemstack, EntityPlayer player,
			World world, int x, int y, int z, int p_77648_7_, float p_77648_8_,
			float p_77648_9_, float p_77648_10_) {
		if (p_77648_7_ != 1) {
			return false;
		} else {
			++y;
			Block block;

			if (this.doorMaterial == Material.wood) {
				block = null;
			} else {
				block = ModBlocks.codeDoor;
			}

			if (player.canPlayerEdit(x, y, z, p_77648_7_, itemstack)
					&& player.canPlayerEdit(x, y + 1, z, p_77648_7_, itemstack)) {
				if (!block.canPlaceBlockAt(world, x, y, z)) {
					return false;
				} else {
					int i1 = MathHelper
							.floor_double((double) ((player.rotationYaw + 180.0F) * 4.0F / 360.0F) - 0.5D) & 3;
					placeDoorBlock(world, x, y, z, i1, block, player);
					--itemstack.stackSize;
					return true;
				}
			} else {
				return false;
			}
		}
	}

	public static void placeDoorBlock(World world, int x, int y, int z,
			int metadata, Block block, EntityPlayer player) {
		byte b0 = 0;
		byte b1 = 0;

		if (metadata == 0) {
			b1 = 1;
		}

		if (metadata == 1) {
			b0 = -1;
		}

		if (metadata == 2) {
			b1 = -1;
		}

		if (metadata == 3) {
			b0 = 1;
		}

		int i1 = (world.getBlock(x - b0, y, z - b1).isNormalCube() ? 1 : 0)
				+ (world.getBlock(x - b0, y + 1, z - b1).isNormalCube() ? 1 : 0);
		int j1 = (world.getBlock(x + b0, y, z + b1).isNormalCube() ? 1 : 0)
				+ (world.getBlock(x + b0, y + 1, z + b1).isNormalCube() ? 1 : 0);
		boolean flag = world.getBlock(x - b0, y, z - b1) == block
				|| world.getBlock(x - b0, y + 1, z - b1) == block;
		boolean flag1 = world.getBlock(x + b0, y, z + b1) == block
				|| world.getBlock(x + b0, y + 1, z + b1) == block;
		boolean flag2 = false;

		if (flag && !flag1) {
			flag2 = true;
		} else if (j1 > i1) {
			flag2 = true;
		}

		world.setBlock(x, y, z, block, metadata, 2);
		world.setBlock(x, y + 1, z, block, 8 | (flag2 ? 1 : 0), 2);
		world.notifyBlocksOfNeighborChange(x, y, z, block);
		world.notifyBlocksOfNeighborChange(x, y + 1, z, block);
		player.openGui(TechCubed.instance, 1, world, x, y + 1, z);
	}
}