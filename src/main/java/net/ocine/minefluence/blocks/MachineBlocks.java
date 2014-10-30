package net.ocine.minefluence.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.ocine.minefluence.blocks.tileentities.IMachinePart;
import net.ocine.minefluence.blocks.tileentities.InventoryTileEntity;
import net.ocine.minefluence.blocks.tileentities.TileEntityCore;
import net.ocine.minefluence.blocks.tileentities.TileEntityDisplay;
import net.ocine.minefluence.blocks.tileentities.TileEntityInput;
import net.ocine.minefluence.blocks.tileentities.TileEntityOutput;
import net.ocine.minefluence.blocks.tileentities.TileEntityWorker;

public class MachineBlocks extends BlockContainer {
	
	public enum Machines {
		CORE, DISPLAY, INPUT, OUTPUT, WORKER;
	}
	
	public MachineBlocks() {
		super(Material.iron);
	}

	@Override
	public void onBlockPreDestroy(World world, int x, int y, int z, int metadata) {
		IMachinePart machineBlock = (IMachinePart)world.getTileEntity(x, y, z);
		if(machineBlock == null) {
			return;
		}
		machineBlock.destroyStructure(true);
		if(world.getTileEntity(x, y, z) instanceof InventoryTileEntity) {
			((InventoryTileEntity)world.getTileEntity(x, y, z)).dropItems(world, x, y, z);
		}
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
	
}
