package net.ocine.minefluence.blocks.tileentities;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.ocine.minefluence.Algorithm;
import net.ocine.minefluence.blocks.MachineBlocks;
import net.ocine.minefluence.machines.AbstractMachineLogic;
import net.ocine.minefluence.machines.Machine;
import net.ocine.minefluence.machines.MachineLogicManager;
import scala.actors.threadpool.Arrays;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class TileEntityCore extends TileEntity implements Machine, IMachinePart {
    List<IMachinePart> parts = new ArrayList<IMachinePart>();
	private int counter = 0;
    private AbstractMachineLogic logic;
    Collection<ItemStack> items;
    int remainingTime;

	@Override
	public void updateEntity() {
        if(FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
            if (!isActive()) {
                if (counter >= 20) {
                    counter = 0;
                    for (Algorithm.Vector vector : Algorithm.doMagic(new Algorithm.Vector(getX(), getY(), getZ()), getWorldObj())) {
                        ((IMachinePart) worldObj.getTileEntity(vector.x, vector.y, vector.z)).assignToMachine(this, true);
                    }
                    logic = MachineLogicManager.getApplicatableLogic(this);
                    super.markDirty();
                }
                counter++;
            } else {
                if (isTransformationInProgress()) {
                    remainingTime--;
                    super.markDirty();
                    if (remainingTime <= 0) {
                        finishProcess();
                    }
                } else {
                    startNewProcess();
                }
            }
        }
	}

    @SuppressWarnings("unchecked")
	private void startNewProcess() {
        List<ItemStack> newInv = Algorithm.getRemaining(Arrays.asList(getInputInventory()), logic.getInput(Arrays.asList(getInputInventory())));
        if(newInv != null){
            // can start - check whether we have place for output
            if(Algorithm.mergeItems(getOutputInventory(), logic.getOutput(Arrays.asList(getInputInventory()))) != null) {
            	// yes we can
                remainingTime = getProcessTime();
                items = logic.getInput(Arrays.asList(getInputInventory()));
                ItemStack arr[] = new ItemStack[getInputs()];
                for(int i = 0; i < getInputs(); i++){
                    arr[i] = newInv.get(i);
                    if(arr[i] != null && arr[i].stackSize <= 0)arr[i] = null;
                }
                setInputInventory(arr);
            }
        }
    }

    private void finishProcess() {
        Collection<ItemStack> result = getLogic().getOutput(items);
        ItemStack[] inv = Algorithm.mergeItems(getOutputInventory(), result);
        if(inv == null){
            ItemStack[] inv1 = Algorithm.mergeItems(getOutputInventory(), items);
            if(inv1 != null)
            setInputInventory(inv1);
            else{

            }
        } else {
            setOutputInventory(inv);
        }
        items = null;
        remainingTime = 0;
        super.markDirty();
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
        TileEntityCore core2 = (TileEntityCore) machine;
        core2.worldObj.setBlockToAir(getX(), getY(), getZ());
        return false;
    }

    @Override
    public boolean removeFromMachine() {
        for(IMachinePart part: new ArrayList<IMachinePart>(parts)){
            part.removeFromMachine();
        }
        dropItems(worldObj, getX(), getY(), getZ());
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
    public ItemStack[] getInputInventory() {
        ItemStack[] inv = new ItemStack[getInputs()];
        int i = 0;
        for(IMachinePart part: parts){
            if(part instanceof TileEntityInput){
                TileEntityInput input = (TileEntityInput) part;
                inv[i] = input.getStackInSlot(0).copy();
                i++;
            }
        }
        return inv;
    }

    @Override
    public void setInputInventory(ItemStack[] inv) {
        int i = 0;
        for(IMachinePart part: parts){
            if(part instanceof TileEntityInput){
                TileEntityInput input = (TileEntityInput) part;
                input.setInventorySlotContents(0, inv[i]);
                i++;
            }
        }
        super.markDirty();
    }

    @Override
    public ItemStack[] getOutputInventory() {
        ItemStack[] inv = new ItemStack[getInputs()];
        int i = 0;
        for(IMachinePart part: parts){
            if(part instanceof TileEntityOutput){
                TileEntityOutput input = (TileEntityOutput) part;
                if(input.getStackInSlot(0) == null) {
                	continue;
                }
                inv[i] = input.getStackInSlot(0).copy();
                i++;
            }
        }
        return inv;
    }

    @Override
    public void setOutputInventory(ItemStack[] inv) {
        int i = 0;
        for(IMachinePart part: parts){
            if(part instanceof TileEntityOutput){
                TileEntityOutput input = (TileEntityOutput) part;
                input.setInventorySlotContents(0, inv[i]);
                i++;
            }
        }
        super.markDirty();
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        remainingTime = compound.getInteger("remaining");
        if(compound.hasKey("items")) {
            items = new ArrayList<ItemStack>();
            NBTTagList list = compound.getTagList("items", Constants.NBT.TAG_COMPOUND);
            for (int i = 0; i < list.tagCount(); i++) {
                NBTTagCompound stack = list.getCompoundTagAt(i);
                items.add(ItemStack.loadItemStackFromNBT(stack));
            }
        } else {
            items = null;
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("remaining", remainingTime);
        if(items != null) {
            NBTTagList list = new NBTTagList();
            for (ItemStack is : items) {
                list.appendTag(is.writeToNBT(new NBTTagCompound()));
            }
            compound.setTag("items", list);
        }
    }

    public void dropItems(World world, int x, int y, int z) {
        if(items == null)return;

        Random rand = new Random();

        for (ItemStack item: items) {

            if (item != null && item.stackSize > 0) {
                float rx = rand.nextFloat() * 0.8F + 0.1F;
                float ry = rand.nextFloat() * 0.8F + 0.1F;
                float rz = rand.nextFloat() * 0.8F + 0.1F;

                EntityItem entityItem = new EntityItem(world, x + rx, y + ry, z + rz, item.copy());

                if (item.hasTagCompound()) {
                    entityItem.getEntityItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
                }

                float factor = 0.05F;
                entityItem.motionX = rand.nextGaussian() * factor;
                entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
                entityItem.motionZ = rand.nextGaussian() * factor;
                world.spawnEntityInWorld(entityItem);
                item.stackSize = 0;
            }
        }
    }
}
