package net.ocine.minefluence.blocks.tileentities;

import net.ocine.minefluence.blocks.MachineBlocks;

public class TileEntityWorker extends TileEntityMachinePart {

	@Override
	public MachineBlocks.Machines getType() {
		return MachineBlocks.Machines.WORKER;
	}

	@Override
	public String getTextureName() {
		return "machineblock_worker.png";
	}

	@Override
	public String getBorder() {
		//TODO Implement
		return "";
	}
}
