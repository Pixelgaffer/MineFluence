package net.ocine.minefluence.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.ocine.minefluence.MineFluence;
import net.ocine.minefluence.blocks.tileentities.IMachinePart;
import net.ocine.minefluence.blocks.tileentities.TileEntityDisplay;
import net.ocine.minefluence.gui.GUIs;

public class MachineBlockDisplay extends BlockContainer {

	public static final String NAME = "machineBlockDisplay";

	public static final PropertyDirection PROP_FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyEnum PROP_BORDER = PropertyEnum.create("border", IMachinePart.BorderType.class);

	public MachineBlockDisplay(CreativeTabs tab) {
		super(Material.iron);
		setDefaultState(blockState.getBaseState().withProperty(PROP_FACING, EnumFacing.EAST)
				.withProperty(PROP_BORDER, IMachinePart.BorderType.DEFAULT));
		setUnlocalizedName(NAME);
		GameRegistry.registerBlock(this, NAME);
		setCreativeTab(tab);
	}

	@Override public IBlockState onBlockPlaced(final World worldIn, final BlockPos pos, final EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return this.getDefaultState().withProperty(PROP_FACING, placer.func_174811_aO().getOpposite());
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
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState iBlockState,
			EntityPlayer player, EnumFacing side, float dx, float dy, float dz) {
		TileEntity tileEntity = world.getTileEntity(pos);
		if (tileEntity == null || player.isSneaking()) {
			return false;
		}
		// TODO fix display gui
		if (false) {
			player.openGui(MineFluence.instance, GUIs.DISPLAY.ordinal(), world,
					pos.getX(), pos.getY(), pos.getZ());
			return true;
		}
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileEntityDisplay();
	}

	@Override protected BlockState createBlockState() {
		return new BlockState(this, PROP_FACING, PROP_BORDER);
	}

	@Override public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		IBlockState actualState = super.getActualState(state, worldIn, pos);
		TileEntityDisplay tileEntityDisplay = (TileEntityDisplay) worldIn.getTileEntity(pos);
		if(tileEntityDisplay != null){
			actualState = actualState.withProperty(PROP_BORDER, tileEntityDisplay.getBorderType());
		}
		return actualState;
	}

	/**
	 * Convert the given metadata into a BlockState for this Block
	 */
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(PROP_FACING, EnumFacing.getHorizontal(meta));
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	public int getMetaFromState(IBlockState state)
	{
		return ((EnumFacing)state.getValue(PROP_FACING)).getHorizontalIndex();
	}

	@Override
	public int getRenderType() {
		return 3;
	}
}
