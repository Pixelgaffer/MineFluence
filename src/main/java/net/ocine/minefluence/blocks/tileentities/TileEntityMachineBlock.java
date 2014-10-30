package net.ocine.minefluence.blocks.tileentities;

import net.minecraft.tileentity.TileEntity;

public abstract class TileEntityMachineBlock extends TileEntity implements IMachineBlock {
	
	protected Structure structure;
	
	@Override
	public void addToStructure(Structure structure) {
		this.structure = structure;
	}

	@Override
	public void destroyStructure(boolean callStructure) {
		if(callStructure) {
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
