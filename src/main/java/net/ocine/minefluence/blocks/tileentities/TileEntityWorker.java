package net.ocine.minefluence.blocks.tileentities;

import net.ocine.minefluence.blocks.MachineBlocks.Machines;

public class TileEntityWorker extends TileEntityMachineBlock {
	
	@Override
	public Machines getType() {
		return Machines.WORKER;
	}

}
