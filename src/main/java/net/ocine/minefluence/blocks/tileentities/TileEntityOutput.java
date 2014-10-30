package net.ocine.minefluence.blocks.tileentities;

import net.minecraft.item.ItemStack;
import net.ocine.minefluence.blocks.MachineBlocks.Machines;

public class TileEntityOutput extends InventoryTileEntity implements IMachineBlock {

	private Structure structure;
	
	public TileEntityOutput() {
		super(1);
	}

	@Override
	public Machines getType() {
		return Machines.OUTPUT;
	}

	@Override
	public void addToStructure(Structure structure) {
		this.structure = structure;
	}

	@Override
	public void destroyStructure(boolean callStructure) {
		if(callStructure && structure != null) {
			structure.destroyStructure();
			return;
		}
		addToStructure(null);
	}

	@Override
	public Structure getStructure() {
		return structure;
	}
	
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return false;
	}
	
}
