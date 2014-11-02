package net.ocine.minefluence.blocks.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.ocine.minefluence.machines.Machine;

public class MachinePartBase {
	private IMachinePart part;
	private int x;
	private int y;
	private int z;
	private boolean hasCore;

	public MachinePartBase(IMachinePart part) {
		this.part = part;
	}

	public Machine getMachine() {
		if (!hasCore) {
			return null;
		}
		TileEntity core = ((TileEntity) part).getWorldObj().getTileEntity(x, y, z);
		if (core == null) {
			return null;
		}
		if (core instanceof TileEntityCore) {
			return ((TileEntityCore) core);
		}
		return null;
	}

	public boolean isPartOfMachine() {
		return getMachine() != null;
	}

	public boolean assignToMachine(Machine machine, boolean force) {
		if (isPartOfMachine() && !force) {
			return false;
		}
		hasCore = true;
		x = machine.getX();
		y = machine.getY();
		z = machine.getZ();
		((TileEntity) part).markDirty();
		return true;
	}

	public boolean removeFromMachine() {
		if (!isPartOfMachine()) {
			return false;
		}
		hasCore = false;
		((TileEntity) part).markDirty();
		return true;
	}

	public void readFromNBT(NBTTagCompound compound) {
		hasCore = false;
		if (part == null) {
			return;
		}
		if (!compound.hasKey("magic")) {
			return;
		}
		hasCore = true;
		NBTTagCompound magic = compound.getCompoundTag("magic");
		x = magic.getInteger("x");
		y = magic.getInteger("y");
		z = magic.getInteger("z");
	}

	public void writeToNBT(NBTTagCompound compound) {
		if (isPartOfMachine()) {
			NBTTagCompound magic = new NBTTagCompound();
			magic.setInteger("x", getMachine().getX());
			magic.setInteger("y", getMachine().getY());
			magic.setInteger("z", getMachine().getZ());
			compound.setTag("magic", magic);
		}
	}
}
