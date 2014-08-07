package com.nicba1010.techcubed.block;

import ibxm.Player;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IconFlipped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.nicba1010.techcubed.TechCubed;
import com.nicba1010.techcubed.init.ModItems;
import com.nicba1010.techcubed.tileentity.TileEntityCodeDoor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCodeDoor extends BlockContainerBase {
	@SideOnly(Side.CLIENT)
	private IIcon[] icons1;
	@SideOnly(Side.CLIENT)
	private IIcon[] icons2;

	public BlockCodeDoor(Material material) {
		super(material);
		this.setBlockName("codeDoor");
		this.setBlockTextureName("codeDoor");
		float f = 0.5F;
		float f1 = 1.0F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f1, 0.5F + f);
	}

	/**
	 * Gets the block's texture. Args: side, meta
	 */
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return this.icons2[0];
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
		if (side != 1 && side != 0) {
			int i1 = this.getDoorPart(world, x, y, z);
			int j1 = i1 & 3;
			boolean flag = (i1 & 4) != 0;
			boolean flag1 = false;
			boolean flag2 = (i1 & 8) != 0;

			if (flag) {
				if (j1 == 0 && side == 2) {
					flag1 = !flag1;
				} else if (j1 == 1 && side == 5) {
					flag1 = !flag1;
				} else if (j1 == 2 && side == 3) {
					flag1 = !flag1;
				} else if (j1 == 3 && side == 4) {
					flag1 = !flag1;
				}
			} else {
				if (j1 == 0 && side == 5) {
					flag1 = !flag1;
				} else if (j1 == 1 && side == 3) {
					flag1 = !flag1;
				} else if (j1 == 2 && side == 4) {
					flag1 = !flag1;
				} else if (j1 == 3 && side == 2) {
					flag1 = !flag1;
				}

				if ((i1 & 16) != 0) {
					flag1 = !flag1;
				}
			}

			return flag2 ? this.icons1[flag1 ? 1 : 0] : this.icons2[flag1 ? 1
					: 0];
		} else {
			return this.icons2[0];
		}
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister p_149651_1_) {
		this.icons1 = new IIcon[2];
		this.icons2 = new IIcon[2];
		this.icons1[0] = p_149651_1_.registerIcon(this.getTextureName()
				+ "_upper");
		this.icons2[0] = p_149651_1_.registerIcon(this.getTextureName()
				+ "_lower");
		this.icons1[1] = new IconFlipped(this.icons1[0], true, false);
		this.icons2[1] = new IconFlipped(this.icons2[0], true, false);
	}

	/**
	 * Is this block (a) opaque and (b) a full 1m cube? This determines whether
	 * or not to render the shared face of two adjacent blocks and also whether
	 * the player can attach torches, redstone wire, etc to this block.
	 */
	public boolean isOpaqueCube() {
		return false;
	}

	public boolean getBlocksMovement(IBlockAccess world, int x, int y, int z) {
		int l = this.getDoorPart(world, x, y, z);
		return (l & 4) != 0;
	}

	/**
	 * If this block doesn't render as an ordinary block it will return False
	 * (examples: signs, buttons, stairs, etc)
	 */
	public boolean renderAsNormalBlock() {
		return false;
	}

	/**
	 * The type of render function that is called for this block
	 */
	public int getRenderType() {
		return 7;
	}

	/**
	 * Returns the bounding box of the wired rectangular prism to render.
	 */
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x,
			int y, int z) {
		this.setBlockBoundsBasedOnState(world, x, y, z);
		return super.getSelectedBoundingBoxFromPool(world, x, y, z);
	}

	/**
	 * Returns a bounding box from the pool of bounding boxes (this means this
	 * box can change after the pool has been cleared to be reused)
	 */
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x,
			int y, int z) {
		this.setBlockBoundsBasedOnState(world, x, y, z);
		return super.getCollisionBoundingBoxFromPool(world, x, y, z);
	}

	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y,
	 * z
	 */
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y,
			int z) {
		this.func_150011_b(this.getDoorPart(world, x, y, z));
	}

	public int func_150013_e(IBlockAccess world, int x, int y, int z) {
		return this.getDoorPart(world, x, y, z) & 3;
	}

	public boolean func_150015_f(IBlockAccess world, int x, int y, int z) {
		return (this.getDoorPart(world, x, y, z) & 4) != 0;
	}

	private void func_150011_b(int p_150011_1_) {
		float f = 0.1875F;
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F);
		int j = p_150011_1_ & 3;
		boolean flag = (p_150011_1_ & 4) != 0;
		boolean flag1 = (p_150011_1_ & 16) != 0;

		if (j == 0) {
			if (flag) {
				if (!flag1) {
					this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
				} else {
					this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
				}
			} else {
				this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
			}
		} else if (j == 1) {
			if (flag) {
				if (!flag1) {
					this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
				} else {
					this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
				}
			} else {
				this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
			}
		} else if (j == 2) {
			if (flag) {
				if (!flag1) {
					this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
				} else {
					this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
				}
			} else {
				this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
			}
		} else if (j == 3) {
			if (flag) {
				if (!flag1) {
					this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
				} else {
					this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
				}
			} else {
				this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
			}
		}
	}

	/**
	 * Called when a player hits the block. Args: world, x, y, z, player
	 */
	public void onBlockClicked(World world, int x, int y, int z,
			EntityPlayer player) {
	}

	/**
	 * Called upon block activation (right click on the block.)
	 */
	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int p_149727_6_, float p_149727_7_,
			float p_149727_8_, float p_149727_9_) {
		if (player.isSneaking()) {
		} else {
			openGui(player, world, x, y, z);
		}
		return true;
	}

	public void openGui(EntityPlayer player, World world, int x, int y, int z) {
		if (isDoorUpperPart(world, x, y, z))
			player.openGui(TechCubed.instance, 1, world, x, y, z);
	}

	public void toggleCodeDoor(World world, int x, int y, int z,
			EntityPlayer player) {
		int i1 = this.getDoorPart(world, x, y, z);
		int j1 = i1 & 7;
		j1 ^= 4;
		System.out.println(j1);
		if ((i1 & 8) == 0) {
			world.setBlockMetadataWithNotify(x, y, z, j1, 2);
			world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
		} else {
			world.setBlockMetadataWithNotify(x, y - 1, z, j1, 2);
			world.markBlockRangeForRenderUpdate(x, y - 1, z, x, y, z);
		}

		world.playAuxSFXAtEntity(player, 1003, x, y, z, 0);
	}

	/**
	 * Lets the block know when one of its neighbor changes. Doesn't know which
	 * neighbor changed (coordinates passed are their own) Args: x, y, z,
	 * neighbor Block
	 */
	public void onNeighborBlockChange(World world, int x, int y, int z,
			Block neighbourBlock) {
		int l = world.getBlockMetadata(x, y, z);

		if ((l & 8) == 0) {
			boolean flag = false;

			if (world.getBlock(x, y + 1, z) != this) {
				world.setBlockToAir(x, y, z);
				flag = true;
			}

			if (!World.doesBlockHaveSolidTopSurface(world, x, y - 1, z)) {
				world.setBlockToAir(x, y, z);
				flag = true;

				if (world.getBlock(x, y + 1, z) == this) {
					world.setBlockToAir(x, y + 1, z);
				}
			}

			if (flag) {
				if (!world.isRemote) {
					this.dropBlockAsItem(world, x, y, z, l, 0);
				}
			} else {
			}
		} else {
			if (world.getBlock(x, y - 1, z) != this) {
				world.setBlockToAir(x, y, z);
			}

			if (neighbourBlock != this) {
				this.onNeighborBlockChange(world, x, y - 1, z, neighbourBlock);
			}
		}
	}

	public Item getItemDropped(int par1, Random rand, int par3) {
		return (par1 & 8) != 0 ? null : ModItems.codeDoorItem;
	}

	/**
	 * Ray traces through the blocks collision from start vector to end vector
	 * returning a ray trace hit. Args: world, x, y, z, startVec, endVec
	 */
	public MovingObjectPosition collisionRayTrace(World world, int x, int y,
			int z, Vec3 startVec, Vec3 endVec) {
		this.setBlockBoundsBasedOnState(world, x, y, z);
		return super.collisionRayTrace(world, x, y, z, startVec, endVec);
	}

	/**
	 * Checks to see if its valid to put this block at the specified
	 * coordinates. Args: world, x, y, z
	 */
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return y >= world.getHeight() - 1 ? false : World
				.doesBlockHaveSolidTopSurface(world, x, y - 1, z)
				&& super.canPlaceBlockAt(world, x, y, z)
				&& super.canPlaceBlockAt(world, x, y + 1, z);
	}

	/**
	 * Returns the mobility information of the block, 0 = free, 1 = can't push
	 * but can move over, 2 = total immobility and stop pistons
	 */
	public int getMobilityFlag() {
		return 2;
	}

	public boolean isDoorUpperPart(World world, int x, int y, int z) {
		int i1 = this.getDoorPart(world, x, y, z);
		int j1 = i1 & 7;
		j1 ^= 4;
		if ((i1 & 8) == 0) {
			return false;
		} else {
			return true;
		}
	}

	public int getDoorPart(IBlockAccess world, int x, int y, int z) {
		int l = world.getBlockMetadata(x, y, z);
		boolean flag = (l & 8) != 0;
		int i1;
		int j1;

		if (flag) {
			i1 = world.getBlockMetadata(x, y - 1, z);
			j1 = l;
		} else {
			i1 = l;
			j1 = world.getBlockMetadata(x, y + 1, z);
		}

		boolean flag1 = (j1 & 1) != 0;
		return i1 & 7 | (flag ? 8 : 0) | (flag1 ? 16 : 0);
	}

	/**
	 * Gets an item for the block being called on. Args: world, x, y, z
	 */
	@SideOnly(Side.CLIENT)
	public Item getItem(World world, int x, int y, int z) {
		return ModItems.codeDoorItem;
	}

	/**
	 * Called when the block is attempted to be harvested
	 */
	public void onBlockHarvested(World world, int x, int y, int z, int par5,
			EntityPlayer player) {
		if (player.capabilities.isCreativeMode && (par5 & 8) != 0
				&& world.getBlock(x, y - 1, z) == this) {
			world.setBlockToAir(x, y - 1, z);
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileEntityCodeDoor();
	}
}