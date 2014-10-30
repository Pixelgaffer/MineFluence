package net.ocine.minefluence.blocks.tileentities;


import javax.annotation.Nullable;

public abstract class TileEntityGuiMachinePart extends InventoryTileEntity implements IMachinePart {

	public TileEntityGuiMachinePart(int inventorySize) {
		super(inventorySize);
        machinePartBase = new MachinePartBase();
    }

	MachinePartBase machinePartBase;

    @Nullable
    @Override
    public Machine getMachine() {
        return machinePartBase.getMachine();
    }

    @Override
    public boolean isPartOfMachine() {
        return machinePartBase.isPartOfMachine();
    }

    @Override
    public boolean assignToMachine(Machine machine, boolean force) {
        return machinePartBase.assignToMachine(machine, force);
    }

    @Override
    public boolean removeFromMachine() {
        return machinePartBase.removeFromMachine();
    }
}
