package net.ocine.minefluence.blocks;

import com.google.common.collect.Lists;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.ocine.minefluence.MineFluence;
import net.ocine.minefluence.blocks.tileentities.*;
import net.ocine.minefluence.gui.GUIs;

import java.util.LinkedList;
import java.util.List;

public class MachineBlocks extends BlockContainer {

	public static final String UNLOCALIZED_NAME = "machineBlocks";

	public static final String[] names = { "core", "display", "input",
			"output", "worker", "hyperworker" };

	public static enum Machines implements IStringSerializable {
		CORE, DISPLAY, INPUT, OUTPUT, WORKER, HYPERWORKER;

		@Override public String getName() {
			return name();
		}

	}

	public static final PropertyEnum TYPE = PropertyEnum.create("type", Machines.class);

	private static final LinkedList<EnumFacing> faces = Lists.newLinkedList();

	public MachineBlocks(CreativeTabs tab) {
		super(Material.iron);
		setDefaultState(blockState.getBaseState().withProperty(TYPE, Machines.CORE));
		GameRegistry.registerBlock(this, MachineBlocksItemBlock.class,
				UNLOCALIZED_NAME);
		setCreativeTab(tab);
	}

	@Override public IBlockState onBlockPlaced(final World worldIn, final BlockPos pos, final EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		IBlockState iBlockState = super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer);
		if(iBlockState.getValue(TYPE).equals(Machines.DISPLAY) && facing.getAxis() != EnumFacing.Axis.Y && !worldIn.isRemote) {
			faces.add(facing.getOpposite());
		}
		return iBlockState;
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state){
		IMachinePart machineBlock = (IMachinePart) world.getTileEntity(pos);
		if (machineBlock == null) {
			return;
		}
		if (machineBlock.isPartOfMachine()) {
			machineBlock.getMachine().removePart(machineBlock);
		}
		if (world.getTileEntity(pos) instanceof InventoryTileEntity) {
			((InventoryTileEntity) world.getTileEntity(pos)).dropItems(
					world, pos.getX(), pos.getY(), pos.getZ());
		}
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState iBlockState,
			EntityPlayer player, EnumFacing side, float dx, float dy, float dz) {
		TileEntity tileEntity = world.getTileEntity(pos);
		if (tileEntity == null || player.isSneaking()) {
			return false;
		}
		if (tileEntity instanceof TileEntityInput) {
			player.openGui(MineFluence.instance, GUIs.INPUT.ordinal(), world,
					pos.getX(), pos.getY(), pos.getZ());
			return true;
		}
		if (tileEntity instanceof TileEntityOutput) {
			player.openGui(MineFluence.instance, GUIs.OUTPUT.ordinal(), world,
					pos.getX(), pos.getY(), pos.getZ());
			return true;
		}
		// TODO fix display gui
		if (false && tileEntity instanceof TileEntityDisplay) {
			player.openGui(MineFluence.instance, GUIs.DISPLAY.ordinal(), world,
					pos.getX(), pos.getY(), pos.getZ());
			return true;
		}
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		if (metadata == Machines.CORE.ordinal()) {
			return new TileEntityCore();
		}
		if (metadata == Machines.DISPLAY.ordinal()) {
			TileEntityDisplay tileEntityDisplay = new TileEntityDisplay();
			if(!faces.isEmpty())tileEntityDisplay.facing = faces.remove(0);
			return tileEntityDisplay;
		}
		if (metadata == Machines.INPUT.ordinal()) {
			return new TileEntityInput();
		}
		if (metadata == Machines.OUTPUT.ordinal()) {
			return new TileEntityOutput();
		}
		if (metadata == Machines.WORKER.ordinal()) {
			return new TileEntityWorker();
		}
		if (metadata == Machines.HYPERWORKER.ordinal()) {
			return new TileEntityHyperworker();
		}
		return null;
	}

	@Override public void getSubBlocks(Item itemIn, CreativeTabs tab, List subItems) {
		for (int i = 0; i < Machines.values().length; i++) {
			subItems.add(new ItemStack(this, 1, i));
		}
	}

	@Override public int damageDropped(IBlockState state) {
		return ((Machines)state.getValue(TYPE)).ordinal();
	}

	@Override public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(TYPE, Machines.values()[meta]);
	}

	@Override public int getMetaFromState(IBlockState state) {
		return ((Machines)state.getValue(TYPE)).ordinal();
	}

	@Override protected BlockState createBlockState() {
		return new BlockState(this, TYPE);
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	@Override public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		// we render it our selves
		return false;
	}

	@Override public boolean isOpaqueCube() {
		return false;
	}
}
