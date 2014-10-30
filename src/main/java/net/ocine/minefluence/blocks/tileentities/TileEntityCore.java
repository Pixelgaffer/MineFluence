package net.ocine.minefluence.blocks.tileentities;

import net.minecraft.tileentity.TileEntity;
import net.ocine.minefluence.blocks.MachineBlocks.Machines;

public class TileEntityCore extends TileEntity implements IMachineBlock {
	
	private Structure structure;
	
	private int counter = 0;
	@Override
	public void updateEntity() {
		if(counter >= 40) {
			counter = 0;
			//TODO Implement structure recognition
		}
		counter++;
	}
	
	@Override
	public Machines getType() {
		return Machines.CORE;
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
