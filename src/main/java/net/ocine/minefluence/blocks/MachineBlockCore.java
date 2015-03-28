package net.ocine.minefluence.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.ocine.minefluence.blocks.tileentities.IMachinePart;
import net.ocine.minefluence.blocks.tileentities.TileEntityCore;

public class MachineBlockCore extends BlockContainer {

	public static final String NAME = "machineBlockCore";

	public static final PropertyEnum PROP_BORDER = PropertyEnum.create("border", IMachinePart.BorderType.class);

	public MachineBlockCore(CreativeTabs tab) {
		super(Material.iron);
		setDefaultState(blockState.getBaseState().withProperty(PROP_BORDER, IMachinePart.BorderType.DEFAULT));
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

	@Override public int getMetaFromState(IBlockState state) {
		return 0;
	}

	@Override protected BlockState createBlockState() {
		return new BlockState(this, PROP_BORDER);
	}

	@Override public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		IBlockState actualState = super.getActualState(state, worldIn, pos);
		TileEntityCore tileEntityCore = (TileEntityCore) worldIn.getTileEntity(pos);
		if(tileEntityCore != null){
			actualState = actualState.withProperty(PROP_BORDER, tileEntityCore.getBorderType());
		}
		return actualState;
	}

	@Override public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
		super.onNeighborBlockChange(worldIn, pos, state, neighborBlock);
		worldIn.markBlockRangeForRenderUpdate(pos, pos);
	}

	@Override
	public int getRenderType() {
		return 3;
	}
}
