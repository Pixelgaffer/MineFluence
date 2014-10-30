package net.ocine.minefluence.blocks.tileentities;

import net.ocine.minefluence.blocks.MachineBlocks.Machines;

public class TileEntityCore extends TileEntityMachineBlock {
		
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
}
