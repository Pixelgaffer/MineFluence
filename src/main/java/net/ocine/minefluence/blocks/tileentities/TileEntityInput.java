package net.ocine.minefluence.blocks.tileentities;

import net.ocine.minefluence.blocks.MachineBlocks.Machines;

public class TileEntityInput extends InventoryTileEntity implements IMachineBlock {

	private Structure structure;
	
	public TileEntityInput() {
		super(1);
	}

	@Override
	public Machines getType() {
		return Machines.INPUT;
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
	
}
