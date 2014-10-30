package net.ocine.minefluence.blocks.tileentities;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.ocine.minefluence.Algorithm;
import net.ocine.minefluence.ExplosionExeption;
import net.ocine.minefluence.blocks.MachineBlocks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TileEntityCore extends TileEntity implements Machine, IMachinePart {
    List<IMachinePart> parts = new ArrayList<IMachinePart>();
	private int counter = 0;
    private AbstractMachineLogic logic;
    Collection<ItemStack> items;
    int remainingTime;

	@Override
	public void updateEntity() {
        if(!isActive()) {
            if (counter >= 20) {
                counter = 0;
                for(Algorithm.Vector vector: Algorithm.doMagic(new Algorithm.Vector(getX(), getY(), getZ()), getWorldObj())){
                    ((IMachinePart) worldObj.getTileEntity(vector.x, vector.y, vector.z)).assignToMachine(this, true);
                }
                logic = MachineLogicManager.getApplicatableLogic(this);
            }
            counter++;
        } else {
            if(isTransformationInProgress()){
                remainingTime--;
                if(remainingTime <= 0){
                    finishProcess();
                }
            } else {
                startNewProcess();
            }
        }
	}

    private void startNewProcess() {
        throw new ExplosionExeption();
    }

    private void finishProcess() {
        throw new ExplosionExeption();
    }

    public Machine getMachine(){
        return this;
    }

    @Override
    public boolean isPartOfMachine() {
        // WE ARE THE MACHINE
        return true;
    }

    @Override
    public boolean assignToMachine(Machine machine, boolean force) {
        if(machine == this)return true;
        // NO THIS CAN'T BE
        throw new ExplosionExeption();
    }

    @Override
    public boolean removeFromMachine() {
        for(IMachinePart part: parts){
            part.removeFromMachine();
        }
        return true;
    }

    @Override
    public MachineBlocks.Machines getType() {
        return MachineBlocks.Machines.CORE;
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
    public int getWorkers() {
        int i = 0;
        for(IMachinePart part: getParts()){
            if(part.getType() == MachineBlocks.Machines.WORKER)i++;
        }
        return i;
    }

    @Override
    public int getInputs() {
        int i = 0;
        for(IMachinePart part: getParts()){
            if(part.getType() == MachineBlocks.Machines.INPUT)i++;
        }
        return i;
    }

    @Override
    public int getOutputs() {
        int i = 0;
        for(IMachinePart part: getParts()){
            if(part.getType() == MachineBlocks.Machines.OUTPUT)i++;
        }
        return i;
    }

    @Override
    public boolean isTransformationInProgress() {
        return remainingTime != 0 && items != null;
    }

    @Override
    public int getRemainingTime() {
        return remainingTime;
    }

    @Override
    public int getProcessTime() {
        return logic.processTime/getWorkers();
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
