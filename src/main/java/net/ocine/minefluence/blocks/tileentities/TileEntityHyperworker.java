package net.ocine.minefluence.blocks.tileentities;


import net.ocine.minefluence.blocks.MachineBlocks;

public class TileEntityHyperworker extends TileEntityMachinePart {

    @Override
    public MachineBlocks.Machines getType() {
        return MachineBlocks.Machines.HYPERWORKER;
    }
}
