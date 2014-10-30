package net.ocine.minefluence.blocks.tileentities;


import net.minecraft.nbt.NBTTagCompound;

import javax.annotation.Nullable;

public abstract class TileEntityGuiMachinePart extends InventoryTileEntity implements IMachinePart {

	public TileEntityGuiMachinePart(int inventorySize) {
		super(inventorySize);
    }

	MachinePartBase machinePartBase = new MachinePartBase(this);

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

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        machinePartBase.readFromNBT(tagCompound);
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        machinePartBase.writeToNBT(tagCompound);
    }
}
