package net.ocine.minefluence.blocks.tileentities;

import net.ocine.minefluence.blocks.MachineBlocks;

public class TileEntityInput extends TileEntityGuiMachinePart {

	public TileEntityInput() {
		super(1);
	}

	@Override
	public MachineBlocks.Machines getType() {
		return MachineBlocks.Machines.INPUT;
	}
}
