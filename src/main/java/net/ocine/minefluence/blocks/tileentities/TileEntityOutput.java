package net.ocine.minefluence.blocks.tileentities;

import net.ocine.minefluence.blocks.MachineBlocks.Machines;

public class TileEntityOutput extends TileEntityGuiMachineBlock {
	
	public TileEntityOutput() {
		super(1);
	}

	@Override
	public Machines getType() {
		return Machines.OUTPUT;
	}

}
