package net.ocine.minefluence.blocks;

import java.util.List;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.ocine.minefluence.MineFluence;
import net.ocine.minefluence.blocks.tileentities.IMachinePart;
import net.ocine.minefluence.blocks.tileentities.InventoryTileEntity;
import net.ocine.minefluence.blocks.tileentities.TileEntityCore;
import net.ocine.minefluence.blocks.tileentities.TileEntityDisplay;
import net.ocine.minefluence.blocks.tileentities.TileEntityInput;
import net.ocine.minefluence.blocks.tileentities.TileEntityOutput;
import net.ocine.minefluence.blocks.tileentities.TileEntityWorker;
import net.ocine.minefluence.gui.GUIs;
import cpw.mods.fml.common.registry.GameRegistry;

public class MachineBlocks extends BlockContainer {

	public static final String UNLOCALIZED_NAME = "machineBlocks";

	public static final String[] names = { "core", "display", "input", "output", "worker" };

	public static enum Machines {
		CORE, DISPLAY, INPUT, OUTPUT, WORKER;
	}

	private IIcon coreTop, coreSide, coreBottom, coreFront, coreBack;
	private IIcon displayTop, displaySide, displayBottom, displayFront, displayBack;
	private IIcon inputTop, inputSide, inputBottom, inputFront, inputBack;
	private IIcon outputTop, outputSide, outputBottom, outputFront, outputBack;
	private IIcon workerTop, workerSide, workerBottom, workerFront, workerBack;

	public MachineBlocks(CreativeTabs tab) {
		super(Material.iron);
		GameRegistry.registerBlock(this, MachineBlocksItemBlock.class, UNLOCALIZED_NAME);
		setCreativeTab(tab);
	}

	@Override
	public void onBlockPreDestroy(World world, int x, int y, int z, int metadata) {
		IMachinePart machineBlock = (IMachinePart) world.getTileEntity(x, y, z);
		if (machineBlock == null) {
			return;
		}
		machineBlock.removeFromMachine();
		if (world.getTileEntity(x, y, z) instanceof InventoryTileEntity) {
			((InventoryTileEntity) world.getTileEntity(x, y, z)).dropItems(world, x, y, z);
		}
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float dx, float dy, float dz) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if (tileEntity == null || player.isSneaking()) {
			return false;
		}
		if (tileEntity instanceof TileEntityInput) {
			player.openGui(MineFluence.instance, GUIs.INPUT.ordinal(), world, x, y, z);
			return true;
		}
		if (tileEntity instanceof TileEntityOutput) {
			player.openGui(MineFluence.instance, GUIs.OUTPUT.ordinal(), world, x, y, z);
			return true;
		}
		if (tileEntity instanceof TileEntityDisplay) {
			player.openGui(MineFluence.instance, GUIs.DISPLAY.ordinal(), world, x, y, z);
			return true;
		}
		return false;
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		if (meta == Machines.CORE.ordinal()) {
			if (side == 0) {
				return coreBottom;
			}
			if (side == 1) {
				return coreTop;
			}
			if (side == 2) {
				return coreFront;
			}
			if (side == 3) {
				return coreBack;
			}
			return coreSide;
		}
		if (meta == Machines.DISPLAY.ordinal()) {
			if (side == 0) {
				return displayBottom;
			}
			if (side == 1) {
				return displayTop;
			}
			if (side == 2) {
				return displayFront;
			}
			if (side == 3) {
				return displayBack;
			}
			return displaySide;
		}
		if (meta == Machines.INPUT.ordinal()) {
			if (side == 0) {
				return inputBottom;
			}
			if (side == 1) {
				return inputTop;
			}
			if (side == 2) {
				return inputFront;
			}
			if (side == 3) {
				return inputBack;
			}
			return inputSide;
		}
		if (meta == Machines.OUTPUT.ordinal()) {
			if (side == 0) {
				return outputBottom;
			}
			if (side == 1) {
				return outputTop;
			}
			if (side == 2) {
				return outputFront;
			}
			if (side == 3) {
				return outputBack;
			}
			return outputSide;
		}
		if (side == 0) {
			return workerBottom;
		}
		if (side == 1) {
			return workerTop;
		}
		if (side == 2) {
			return workerFront;
		}
		if (side == 3) {
			return workerBack;
		}
		return workerSide;
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		coreTop = iconRegister.registerIcon("minefluence:mblock_core_top");
		coreBottom = iconRegister.registerIcon("minefluence:mblock_core_bottom");
		coreSide = iconRegister.registerIcon("minefluence:mblock_core_side");
		coreFront = iconRegister.registerIcon("minefluence:mblock_core_front");
		coreBack = iconRegister.registerIcon("minefluence:mblock_core_back");

		displayTop = iconRegister.registerIcon("minefluence:mblock_display_top");
		displayBottom = iconRegister.registerIcon("minefluence:mblock_display_bottom");
		displaySide = iconRegister.registerIcon("minefluence:mblock_display_side");
		displayFront = iconRegister.registerIcon("minefluence:mblock_display_front");
		displayBack = iconRegister.registerIcon("minefluence:mblock_display_back");

		inputTop = iconRegister.registerIcon("minefluence:mblock_input_top");
		inputBottom = iconRegister.registerIcon("minefluence:mblock_input_bottom");
		inputSide = iconRegister.registerIcon("minefluence:mblock_input_side");
		inputFront = iconRegister.registerIcon("minefluence:mblock_input_front");
		inputBack = iconRegister.registerIcon("minefluence:mblock_input_back");

		outputTop = iconRegister.registerIcon("minefluence:mblock_output_top");
		outputBottom = iconRegister.registerIcon("minefluence:mblock_output_bottom");
		outputSide = iconRegister.registerIcon("minefluence:mblock_output_side");
		outputFront = iconRegister.registerIcon("minefluence:mblock_output_front");
		outputBack = iconRegister.registerIcon("minefluence:mblock_output_back");

		workerTop = iconRegister.registerIcon("minefluence:mblock_worker_top");
		workerBottom = iconRegister.registerIcon("minefluence:mblock_worker_bottom");
		workerSide = iconRegister.registerIcon("minefluence:mblock_worker_side");
		workerFront = iconRegister.registerIcon("minefluence:mblock_worker_front");
		workerBack = iconRegister.registerIcon("minefluence:mblock_worker_back");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		if (metadata == Machines.CORE.ordinal()) {
			return new TileEntityCore();
		}
		if (metadata == Machines.DISPLAY.ordinal()) {
			return new TileEntityDisplay();
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
		return null;
	}

	public int damagedDropped(int metadata) {
		return metadata;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getSubBlocks(int par1, CreativeTabs tab, List subItems) {
		for (int i = 0; i < Machines.values().length; i++) {
			subItems.add(new ItemStack(this, 1, i));
		}
	}

}
