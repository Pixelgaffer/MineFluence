package net.ocine.minefluence.blocks.tileentities;

import net.minecraft.tileentity.TileEntity;
import net.ocine.minefluence.blocks.MachineBlocks.Machines;

public class TileEntityWorker extends TileEntity implements IMachineBlock {

	private Structure structure;
	
	@Override
	public Machines getType() {
		return Machines.WORKER;
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
