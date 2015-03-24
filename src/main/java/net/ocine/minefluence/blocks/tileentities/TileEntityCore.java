package net.ocine.minefluence.blocks.tileentities;

import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.ocine.minefluence.Algorithm;
import net.ocine.minefluence.MineFluence;
import net.ocine.minefluence.blocks.MachineBlocks;
import net.ocine.minefluence.machines.AbstractMachineLogic;
import net.ocine.minefluence.machines.Machine;
import net.ocine.minefluence.machines.MachineLogicManager;
import scala.actors.threadpool.Arrays;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class TileEntityCore extends TileEntity implements Machine, IMachinePart, IUpdatePlayerListBox {
	Collection<Algorithm.Vector> parts = new ArrayList<Algorithm.Vector>();
	private int counter = 0;
	private AbstractMachineLogic logic;
	Collection<ItemStack> items;
	int remainingTime;
	int numWorkers;
	@SideOnly(Side.CLIENT)
	private static ResourceLocation texture = new ResourceLocation(MineFluence.MODID, "textures/blocks/machineblocks/machineblock_core.png");

	@Override
	public void update() {
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
			if (logic == null) {
				if (counter >= 20) {
					counter = 0;
					parts = Algorithm.doMagic(new Algorithm.Vector(getX(), getY(), getZ()), getWorld());
					calcWorkers();
					logic = MachineLogicManager.getApplicatableLogic(this);
					if (logic == null) {
						parts.clear();
						numWorkers = 0;
						return;
					}
					for (Algorithm.Vector vector : parts) {
						((IMachinePart) worldObj.getTileEntity(new BlockPos(vector.x, vector.y, vector.z))).assignToMachine(this, true);
					}
					super.markDirty();
				}
				counter++;
			}
			else {
				if (isTransformationInProgress()) {
					remainingTime--;
					super.markDirty();
					if (remainingTime <= 0) {
						finishProcess();
					}
				}
				if (!isTransformationInProgress()) {
					startNewProcess();
				}
				updateDisplays();
			}
		}
	}

	private void updateDisplays() {
		for (Algorithm.Vector vec : parts) {
			TileEntity part = worldObj.getTileEntity(new BlockPos(vec.x, vec.y, vec.z));
			if (part instanceof TileEntityDisplay) {
				TileEntityDisplay tileEntityDisplay = (TileEntityDisplay) part;
				tileEntityDisplay.progress = getProgressForDisplay();
				worldObj.markBlockForUpdate(tileEntityDisplay.getPos());
			}
		}
		worldObj.markBlockForUpdate(getPos());
	}

	@SuppressWarnings("unchecked")
	private void startNewProcess() {
		List<ItemStack> newInv = Algorithm.getRemaining(Arrays.asList(getInputInventory()), logic.getInput(Arrays.asList(getInputInventory())));
		if (newInv != null) {
			// can start - check whether we have place for output
			if (Algorithm.mergeItems(getOutputInventory(), logic.getOutput(newInv)) != null) {
				// yes we can
				remainingTime = getProcessTime();
				items = logic.getInput(Arrays.asList(getInputInventory()));
				ItemStack arr[] = new ItemStack[getInputs()];
				for (int i = 0; i < getInputs(); i++) {
					arr[i] = newInv.get(i);
					if (arr[i] != null && arr[i].stackSize <= 0) {
						arr[i] = null;
					}
				}
				setInputInventory(arr);
			}
		}
	}

	private void finishProcess() {
		Collection<ItemStack> result = getLogic().getOutput(items);
		ItemStack[] inv = Algorithm.mergeItems(getOutputInventory(), result);
		if (inv == null) {
			ItemStack[] inv1 = Algorithm.mergeItems(getOutputInventory(), items);
			setInputInventory(inv1);
			System.out.println("THIS SHOULD NOT HAPPEN");
		}
		else {
			setOutputInventory(inv);
		}
		items = null;
		remainingTime = 0;
		// super.markDirty();
	}

	public Machine getMachine() {
		return this;
	}

	@Override
	public boolean isPartOfMachine() {
		// WE ARE THE MACHINE
		return true;
	}

	@Override
	public boolean assignToMachine(Machine machine, boolean force) {
		if (machine == this) {
			return true;
		}
		// NO THIS CAN'T BE
		TileEntityCore core2 = (TileEntityCore) machine;
		core2.worldObj.setBlockToAir(new BlockPos(getX(), getY(), getZ()));
		return false;
	}

	@Override
	public boolean removeFromMachine() {
		for (IMachinePart part : new ArrayList<IMachinePart>(getParts())) {
			if (part != this && !(part instanceof TileEntityCore)) {
				part.removeFromMachine();
			}
		}
		parts.clear();
		dropItems(worldObj, getX(), getY(), getZ());
		logic = null;
		remainingTime = 0;
		items = null;
		return true;
	}

	@SideOnly(Side.CLIENT)
	@Override public BorderType getBorderType() {
		if(isActive()){
			if(isTransformationInProgress()){
				return BorderType.RED;
			} else {
				return BorderType.GREEN;
			}
		}
		return BorderType.DEFAULT;
	}

	@Override
	public MachineBlocks.Machines getType() {
		return MachineBlocks.Machines.CORE;
	}

	@SideOnly(Side.CLIENT)
	@Override public ResourceLocation getTexture() {
		return texture;
	}

	@Override
	public void addPart(IMachinePart machinePart) {
		parts.add(new Algorithm.Vector(((TileEntity) machinePart).getPos().getX(), ((TileEntity) machinePart).getPos().getY(), ((TileEntity) machinePart).getPos().getZ()));
	}

	@Override
	public void removePart(IMachinePart machinePart) {
		for (IMachinePart part : new ArrayList<IMachinePart>(getParts())) {
			if (part != this && !(part instanceof TileEntityCore)) {
				part.removeFromMachine();
			}
		}
		parts.clear();
		logic = null;
	}

	@Override
	public int getX() {
		return getPos().getX();
	}

	@Override
	public int getY() {
		return getPos().getY();
	}

	@Override
	public int getZ() {
		return getPos().getZ();
	}

	@Override
	public Collection<IMachinePart> getParts() {
		ArrayList<IMachinePart> parts1 = new ArrayList<IMachinePart>(parts.size());
		for (Algorithm.Vector vec : parts) {
			TileEntity entity = worldObj.getTileEntity(new BlockPos(vec.x, vec.y, vec.z));
			if (entity instanceof IMachinePart) {
				parts1.add((IMachinePart) entity);
			}
		}
		return new ArrayList<IMachinePart>(parts1);
	}

	@Override
	public boolean isActive() {
		return !parts.isEmpty();
	}

	@Override
	public AbstractMachineLogic getLogic() {
		return logic;
	}

	@Override
	public int getWorkers() {
		return numWorkers;
	}

	public void calcWorkers() {
		int i = 0;
		for (IMachinePart part : getParts()) {
			if (part.getType() == MachineBlocks.Machines.WORKER) {
				i++;
			}
			if (part.getType() == MachineBlocks.Machines.HYPERWORKER) {
				i += 10; // this is awesome
			}
		}
		numWorkers = i;
	}

	@Override
	public int getInputs() {
		int i = 0;
		for (IMachinePart part : getParts()) {
			if (part.getType() == MachineBlocks.Machines.INPUT) {
				i++;
			}
		}
		return i;
	}

	@Override
	public int getOutputs() {
		int i = 0;
		for (IMachinePart part : getParts()) {
			if (part.getType() == MachineBlocks.Machines.OUTPUT) {
				i++;
			}
		}
		return i;
	}

	@Override
	public boolean isTransformationInProgress() {
		return remainingTime > 0 && items != null;
	}

	@Override
	public int getRemainingTime() {
		return remainingTime;
	}

	@Override
	public int getProcessTime() {
		return logic.processTime / getWorkers();
	}

	@Override
	public ItemStack[] getInputInventory() {
		ItemStack[] inv = new ItemStack[getInputs()];
		int i = 0;
		for (IMachinePart part : getParts()) {
			if (part instanceof TileEntityInput) {
				TileEntityInput input = (TileEntityInput) part;
				inv[i] = input.getStackInSlot(0) == null ? null : input.getStackInSlot(0).copy();
				i++;
			}
		}
		return inv;
	}

	@Override
	public void setInputInventory(ItemStack[] inv) {
		int i = 0;
		for (IMachinePart part : getParts()) {
			if (part instanceof TileEntityInput) {
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
		for (IMachinePart part : getParts()) {
			if (part instanceof TileEntityOutput) {
				TileEntityOutput output = (TileEntityOutput) part;
				inv[i] = output.getStackInSlot(0) == null ? null : output.getStackInSlot(0).copy();
				i++;
			}
		}
		return inv;
	}

	@Override
	public void setOutputInventory(ItemStack[] inv) {
		int i = 0;
		for (IMachinePart part : getParts()) {
			if (part instanceof TileEntityOutput) {
				TileEntityOutput output = (TileEntityOutput) part;
				output.setInventorySlotContents(0, inv[i]);
				i++;
			}
		}
		super.markDirty();
	}

	@Override
	public int getProgressForDisplay() {
		if (!isActive()) {
			return 0;
		}
		if (isTransformationInProgress()) {
			return (getProcessTime() - getRemainingTime()) * 100 / getProcessTime();
		}
		return 100;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		remainingTime = compound.getInteger("remaining");
		numWorkers = compound.getInteger("numWorkers");
		if (compound.hasKey("items")) {
			items = new ArrayList<ItemStack>();
			NBTTagList list = compound.getTagList("items", Constants.NBT.TAG_COMPOUND);
			for (int i = 0; i < list.tagCount(); i++) {
				NBTTagCompound stack = list.getCompoundTagAt(i);
				items.add(ItemStack.loadItemStackFromNBT(stack));
			}
		}
		else {
			items = null;
		}
		if (compound.hasKey("parts")) {
			parts = new ArrayList<Algorithm.Vector>();
			NBTTagList list = compound.getTagList("parts", Constants.NBT.TAG_COMPOUND);
			for (int i = 0; i < list.tagCount(); i++) {
				NBTTagCompound part = list.getCompoundTagAt(i);
				parts.add(new Algorithm.Vector(part.getInteger("x"), part.getInteger("y"), part.getInteger("z")));
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("remaining", remainingTime);
		compound.setInteger("numWorkers", numWorkers);
		if (items != null) {
			NBTTagList list = new NBTTagList();
			for (ItemStack is : items) {
				list.appendTag(is.writeToNBT(new NBTTagCompound()));
			}
			compound.setTag("items", list);
		}
		NBTTagList list = new NBTTagList();
		for (Algorithm.Vector vec : parts) {
			NBTTagCompound f = new NBTTagCompound();
			f.setInteger("x", vec.x);
			f.setInteger("y", vec.y);
			f.setInteger("z", vec.z);
			list.appendTag(f);
		}
		compound.setTag("parts", list);
	}

	public void dropItems(World world, int x, int y, int z) {
		if (items == null) {
			return;
		}

		Random rand = new Random();

		for (ItemStack item : items) {

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
			}
		}
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound compound = new NBTTagCompound();
		writeToNBT(compound);
		compound.setInteger("remaining", remainingTime);
		compound.setInteger("numWorkers", numWorkers);
		if (items != null) {
			NBTTagList list = new NBTTagList();
			for (ItemStack is : items) {
				list.appendTag(is.writeToNBT(new NBTTagCompound()));
			}
			compound.setTag("items", list);
		}
		NBTTagList list = new NBTTagList();
		for (Algorithm.Vector vec : parts) {
			NBTTagCompound f = new NBTTagCompound();
			f.setInteger("x", vec.x);
			f.setInteger("y", vec.y);
			f.setInteger("z", vec.z);
			list.appendTag(f);
		}
		compound.setTag("parts", list);
		return new S35PacketUpdateTileEntity(this.getPos(), 1, compound);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		NBTTagCompound compound = pkt.getNbtCompound();
		readFromNBT(compound);
		remainingTime = compound.getInteger("remaining");
		numWorkers = compound.getInteger("numWorkers");
		if (compound.hasKey("items")) {
			items = new ArrayList<ItemStack>();
			NBTTagList list = compound.getTagList("items", Constants.NBT.TAG_COMPOUND);
			for (int i = 0; i < list.tagCount(); i++) {
				NBTTagCompound stack = list.getCompoundTagAt(i);
				items.add(ItemStack.loadItemStackFromNBT(stack));
			}
		}
		else {
			items = null;
		}
		if (compound.hasKey("parts")) {
			parts = new ArrayList<Algorithm.Vector>();
			NBTTagList list = compound.getTagList("parts", Constants.NBT.TAG_COMPOUND);
			for (int i = 0; i < list.tagCount(); i++) {
				NBTTagCompound part = list.getCompoundTagAt(i);
				parts.add(new Algorithm.Vector(part.getInteger("x"), part.getInteger("y"), part.getInteger("z")));
			}
		}
		//worldObj.scheduleUpdate(getPos(), getBlockType(), 0);
		//worldObj.markBlockRangeForRenderUpdate(getPos().getX(), getPos().getY(), getPos().getZ(), getPos().getX(), getPos().getY(), getPos().getZ());
	}
}
