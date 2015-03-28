package net.ocine.minefluence.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.ocine.minefluence.blocks.tileentities.IMachinePart;
import net.ocine.minefluence.blocks.tileentities.TileEntityCore;

public class MachineBlockCore extends BlockContainer {

	public static final String NAME = "machineBlockCore";

	public MachineBlockCore(CreativeTabs tab) {
		super(Material.iron);
		setUnlocalizedName(NAME);
		GameRegistry.registerBlock(this, NAME);
		setCreativeTab(tab);
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state){
		IMachinePart machineBlock = (IMachinePart) world.getTileEntity(pos);
		if (machineBlock == null) {
			return;
		}
		if (machineBlock.getMachine() != null) {
			machineBlock.getMachine().removePart(machineBlock);
		}
		super.breakBlock(world, pos, state);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileEntityCore();
	}

	@Override
	public int getRenderType() {
		return 3;
	}
}
