package net.ocine.minefluence.blocks;

import java.util.List;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.ocine.minefluence.blocks.tileentities.IMachinePart;
import net.ocine.minefluence.blocks.tileentities.InventoryTileEntity;
import net.ocine.minefluence.blocks.tileentities.TileEntityCore;
import net.ocine.minefluence.blocks.tileentities.TileEntityDisplay;
import net.ocine.minefluence.blocks.tileentities.TileEntityInput;
import net.ocine.minefluence.blocks.tileentities.TileEntityOutput;
import net.ocine.minefluence.blocks.tileentities.TileEntityWorker;
import cpw.mods.fml.common.registry.GameRegistry;

public class MachineBlocks extends BlockContainer {
	
	public static final String UNLOCALIZED_NAME  = "machineBlocks";
	
	public static final String[] names = {"core", "display", "input", "output", "worker"};
	
	public enum Machines {
		CORE, DISPLAY, INPUT, OUTPUT, WORKER;
	}
	
	private IIcon coreTop, coreSide, coreBottom;
	private IIcon displayTop, displaySide, displayBottom;
	private IIcon inputTop, inputSide, inputBottom;
	private IIcon outputTop, outputSide, outputBottom;
	private IIcon workerTop, workerSide, workerBottom;
	
	public MachineBlocks(CreativeTabs tab) {
		super(Material.iron);
		GameRegistry.registerBlock(this, MachineBlocksItemBlock.class, UNLOCALIZED_NAME);
		setCreativeTab(tab);
	}
	
	@Override
	public void onBlockPreDestroy(World world, int x, int y, int z, int metadata) {
		IMachinePart machineBlock = (IMachinePart)world.getTileEntity(x, y, z);
		if(machineBlock == null) {
			return;
		}
		machineBlock.removeFromMachine();
		if(world.getTileEntity(x, y, z) instanceof InventoryTileEntity) {
			((InventoryTileEntity)world.getTileEntity(x, y, z)).dropItems(world, x, y, z);
		}
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		if(meta == Machines.CORE.ordinal()) {
			if(side == 0) {
				return coreBottom;
			}
			if(side == 1) {
				return coreTop;
			}
			return coreSide;
		}
		if(meta == Machines.DISPLAY.ordinal()) {
			if(side == 0) {
				return displayBottom;
			}
			if(side == 1) {
				return displayTop;
			}
			return displaySide;
		}
		if(meta == Machines.INPUT.ordinal()) {
			if(side == 0) {
				return inputBottom;
			}
			if(side == 1) {
				return inputTop;
			}
			return inputSide;
		}
		if(meta == Machines.OUTPUT.ordinal()) {
			if(side == 0) {
				return outputBottom;
			}
			if(side == 1) {
				return outputTop;
			}
			return outputSide;
		}
		if(side == 0) {
			return workerBottom;
		}
		if(side == 1) {
			return workerTop;
		}
		return workerSide;
	}
	
	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		coreTop = iconRegister.registerIcon("minelfuence:mblock_core_top");
		coreBottom = iconRegister.registerIcon("minelfuence:mblock_core_bottom");
		coreSide = iconRegister.registerIcon("minelfuence:mblock_core_side");
		
		displayTop = iconRegister.registerIcon("minelfuence:mblock_display_top");
		displayBottom = iconRegister.registerIcon("minelfuence:mblock_display_bottom");
		displaySide = iconRegister.registerIcon("minelfuence:mblock_display_side");
		
		inputTop = iconRegister.registerIcon("minelfuence:mblock_input_top");
		inputBottom = iconRegister.registerIcon("minelfuence:mblock_input_bottom");
		inputSide = iconRegister.registerIcon("minelfuence:mblock_input_side");
		
		outputTop = iconRegister.registerIcon("minelfuence:mblock_output_top");
		outputBottom = iconRegister.registerIcon("minelfuence:mblock_output_bottom");
		outputSide = iconRegister.registerIcon("minelfuence:mblock_output_side");
		
		workerTop = iconRegister.registerIcon("minelfuence:mblock_worker_top");
		workerBottom = iconRegister.registerIcon("minelfuence:mblock_worker_bottom");
		workerSide = iconRegister.registerIcon("minelfuence:mblock_worker_side");
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		if(metadata == Machines.CORE.ordinal()) {
			return new TileEntityCore();
		}
		if(metadata == Machines.DISPLAY.ordinal()) {
			return new TileEntityDisplay();
		}
		if(metadata == Machines.INPUT.ordinal()) {
			return new TileEntityInput();
		}
		if(metadata == Machines.OUTPUT.ordinal()) {
			return new TileEntityOutput();
		}
		if(metadata == Machines.WORKER.ordinal()) {
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
