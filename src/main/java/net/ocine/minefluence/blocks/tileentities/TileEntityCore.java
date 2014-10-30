package net.ocine.minefluence.blocks.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.ocine.minefluence.Algorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TileEntityCore extends TileEntity implements Machine, IMachinePart {
    List<IMachinePart> parts = new ArrayList<IMachinePart>();
	private int counter = 0;
    private AbstractMachineLogic logic;

	@Override
	public void updateEntity() {
        if(!isActive()) {
            if (counter >= 20) {
                counter = 0;
                for(Algorithm.Vector vector: Algorithm.doMagic(new Algorithm.Vector(getX(), getY(), getZ()), getWorldObj())){
                    parts.add((IMachinePart) worldObj.getTileEntity(vector.x, vector.y, vector.z));
                }
            }
            counter++;
        }
	}
	
	public Machine getMachine(){
        return this;
    }

    @Override
    public boolean isPartOfMachine() {
        // of cause it is - we are the machine
        return true;
    }

    @Override
    public boolean assignToMachine(Machine machine, boolean force) {
        // NO THIS CAN'T BE - CRY
        throw new RuntimeException("cry");
    }

    @Override
    public boolean removeFromMachine() {
        for(IMachinePart part: parts){
            part.removeFromMachine();
        }
        return true;
    }

    @Override
    public void addPart(IMachinePart machinePart) {
        parts.add(machinePart);
    }

    @Override
    public void removePart(IMachinePart machinePart) {
        parts.remove(machinePart);
    }

    @Override
    public int getX() {
        return xCoord;
    }

    @Override
    public int getY() {
        return yCoord;
    }

    @Override
    public int getZ() {
        return zCoord;
    }

    @Override
    public Collection<IMachinePart> getParts() {
        return new ArrayList<IMachinePart>(parts);
    }

    @Override
    public boolean isActive() {
        return logic != null;
    }

    @Override
    public AbstractMachineLogic getLogic() {
        return logic;
    }

    @Override
    public void readFromNBT(NBTTagCompound p_145839_1_) {
        super.readFromNBT(p_145839_1_);
    }

    @Override
    public void writeToNBT(NBTTagCompound p_145841_1_) {
        super.writeToNBT(p_145841_1_);
    }
}
