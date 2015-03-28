package net.ocine.minefluence.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.ocine.minefluence.MineFluence;
import net.ocine.minefluence.blocks.tileentities.IMachinePart;
import net.ocine.minefluence.blocks.tileentities.InventoryTileEntity;
import net.ocine.minefluence.blocks.tileentities.TileEntityInput;
import net.ocine.minefluence.gui.GUIs;

public class MachineBlockInput extends BlockContainer {

	public static final String NAME = "machineBlockInput";

	public MachineBlockInput(CreativeTabs tab) {
		super(Material.iron);
		setUnlocalizedName(NAME);
		GameRegistry.registerBlock(this, NAME);
		setCreativeTab(tab);
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		IMachinePart machineBlock = (IMachinePart) world.getTileEntity(pos);
		if (machineBlock == null) {
			return;
		}
		if (machineBlock.getMachine() != null) {
			machineBlock.getMachine().removePart(machineBlock);
		}
		((InventoryTileEntity) world.getTileEntity(pos)).dropItems(
				world, pos.getX(), pos.getY(), pos.getZ());
		super.breakBlock(world, pos, state);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState iBlockState,
			EntityPlayer player, EnumFacing side, float dx, float dy, float dz) {
		TileEntity tileEntity = world.getTileEntity(pos);
		if (tileEntity == null || player.isSneaking()) {
			return false;
		}
		player.openGui(MineFluence.instance, GUIs.INPUT.ordinal(), world,
				pos.getX(), pos.getY(), pos.getZ());
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileEntityInput();
	}

	@Override
	public int getRenderType() {
		return 3;
	}
}
