package net.ocine.minefluence.blocks.tileentities;

import net.ocine.minefluence.blocks.MachineBlocks.Machines;

public class TileEntityInput extends TileEntityGuiMachineBlock {
	
	public TileEntityInput() {
		super(1);
	}

	@Override
	public Machines getType() {
		return Machines.INPUT;
	}
	
}
