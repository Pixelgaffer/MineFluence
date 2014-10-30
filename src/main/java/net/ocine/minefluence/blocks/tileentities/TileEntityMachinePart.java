package net.ocine.minefluence.blocks.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import javax.annotation.Nullable;

public abstract class TileEntityMachinePart extends TileEntity implements IMachinePart {
	
	MachinePartBase machinePartBase = new MachinePartBase();

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
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        if(!compound.hasKey("magic"))return;
        NBTTagCompound magic = compound.getCompoundTag("magic");
        int x = magic.getInteger("x");
        int y = magic.getInteger("y");
        int z = magic.getInteger("z");
        TileEntity core = worldObj.getTileEntity(x,y,z);
        if(core instanceof TileEntityCore){
            Machine machine = ((TileEntityCore) core).getMachine();
            machinePartBase.assignToMachine(machine, true);
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        if(machinePartBase.isPartOfMachine()){
            NBTTagCompound magic = new NBTTagCompound();
            magic.setInteger("x", machinePartBase.getMachine().getX());
            magic.setInteger("y", machinePartBase.getMachine().getY());
            magic.setInteger("z", machinePartBase.getMachine().getZ());
            compound.setTag("magic", magic);
        }
    }
}
