package net.ocine.minefluence.blocks.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.ocine.minefluence.machines.Machine;

import javax.annotation.Nullable;

public abstract class TileEntityMachinePart extends TileEntity implements IMachinePart {

	MachinePartBase machinePartBase;

	protected TileEntityMachinePart() {
		machinePartBase = new MachinePartBase(this);
	}

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
	public void readFromNBT(NBTTagCompound p_145839_1_) {
		super.readFromNBT(p_145839_1_);
		machinePartBase.readFromNBT(p_145839_1_);
	}

	@Override
	public void writeToNBT(NBTTagCompound p_145841_1_) {
		super.writeToNBT(p_145841_1_);
		machinePartBase.writeToNBT(p_145841_1_);
	}
}
