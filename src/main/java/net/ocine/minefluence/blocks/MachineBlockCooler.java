package net.ocine.minefluence.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.ocine.minefluence.blocks.tileentities.IMachinePart;
import net.ocine.minefluence.blocks.tileentities.TileEntityCooler;

import java.util.List;

public class MachineBlockCooler extends BlockContainer {

	public static final String NAME = "machineBlockCooler";

	public enum Variant implements IStringSerializable {
		FAN, WATERCOOLING;

		@Override public String getName() {
			return name().toLowerCase();
		}
	}

	public static final PropertyEnum PROP_VARIANT = PropertyEnum.create("variant", Variant.class);

	public MachineBlockCooler(CreativeTabs tab) {
		super(Material.iron);
		setDefaultState(blockState.getBaseState().withProperty(PROP_VARIANT, Variant.FAN));
		GameRegistry.registerBlock(this, MachineBlockCoolerItem.class,NAME);
		setCreativeTab(tab);
	}

	@Override public IBlockState onBlockPlaced(final World worldIn, final BlockPos pos, final EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer);
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
		return new TileEntityCooler();
	}

	@Override public void getSubBlocks(Item itemIn, CreativeTabs tab, List subItems) {
		for (int i = 0; i < Variant.values().length; i++) {
			subItems.add(new ItemStack(this, 1, i));
		}
	}

	@Override public int damageDropped(IBlockState state) {
		return ((Variant)state.getValue(PROP_VARIANT)).ordinal();
	}

	@Override public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(PROP_VARIANT, Variant.values()[meta]);
	}

	@Override public int getMetaFromState(IBlockState state) {
		return ((Variant)state.getValue(PROP_VARIANT)).ordinal();
	}

	@Override protected BlockState createBlockState() {
		return new BlockState(this, PROP_VARIANT);
	}

	@Override
	public int getRenderType() {
		return 3;
	}
}
