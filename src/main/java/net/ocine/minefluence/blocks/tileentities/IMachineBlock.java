package net.ocine.minefluence.blocks.tileentities;

import net.ocine.minefluence.blocks.MachineBlocks.Machines;

public interface IMachineBlock {
	public Machines getType();
	public void addToStructure(Structure structure);
	public void destroyStructure(boolean callStructure);
	public Structure getStructure();
}
