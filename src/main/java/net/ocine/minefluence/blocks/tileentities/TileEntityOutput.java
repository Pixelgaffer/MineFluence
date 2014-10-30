package net.ocine.minefluence.blocks.tileentities;


import net.ocine.minefluence.blocks.MachineBlocks;

public class TileEntityOutput extends TileEntityGuiMachinePart {
	
	public TileEntityOutput() {
		super(1);
	}

    @Override
    public MachineBlocks.Machines getType() {
        return MachineBlocks.Machines.OUTPUT;
    }
}
