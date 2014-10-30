package net.ocine.minefluence.blocks.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class MachinePartBase {
    private Machine machine;
    private IMachinePart part;

    public MachinePartBase(IMachinePart part) {
        this.part = part;
    }

    public Machine getMachine() {
        return machine;
    }

    public boolean isPartOfMachine() {
        return machine != null;
    }

    public boolean assignToMachine(Machine machine, boolean force) {
        if (isPartOfMachine() && !force) return false;
        this.machine = machine;
        machine.addPart(part);
        return true;
    }

    public boolean removeFromMachine() {
        if (!isPartOfMachine()) return false;
        machine.removePart(part);
        machine = null;
        return true;
    }

    public void readFromNBT(NBTTagCompound compound) {
        if(part == null)return;
        if (!compound.hasKey("magic")) return;
        NBTTagCompound magic = compound.getCompoundTag("magic");
        int x = magic.getInteger("x");
        int y = magic.getInteger("y");
        int z = magic.getInteger("z");
        if(((TileEntity)part).getWorldObj() == null){
            System.out.println("ticking world magic");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            System.out.println("EXPLOSION * MAGIC");
            return;
        }
        TileEntity core = ((TileEntity)part).getWorldObj().getTileEntity(x, y, z);
        if(core == null)return;
        if (core instanceof TileEntityCore) {
            Machine machine = ((TileEntityCore) core).getMachine();
            assignToMachine(machine, true);
        }
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
