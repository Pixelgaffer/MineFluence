package net.ocine.minefluence.blocks.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.ocine.minefluence.machines.Machine;

import javax.annotation.Nullable;

public abstract class TileEntityGuiMachinePart extends InventoryTileEntity implements IMachinePart {

	public TileEntityGuiMachinePart(int inventorySize) {
		super(inventorySize);
	}

	public TileEntityGuiMachinePart(int inventorySize, String name) {
		super(inventorySize, name);
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
		worldObj.markBlockForUpdate(getPos());
		return machinePartBase.assignToMachine(machine, force);
	}

	@Override
	public boolean removeFromMachine() {
		worldObj.markBlockForUpdate(getPos());
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

	@SideOnly(Side.CLIENT)
	@Override public BorderType getBorderType() {
		if(isPartOfMachine()){
			if(getMachine().isTransformationInProgress()){
				return BorderType.RED;
			} else {
				return BorderType.GREEN;
			}
		}
		return BorderType.DEFAULT;
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound syncData = new NBTTagCompound();
		writeToNBT(syncData);
		return new S35PacketUpdateTileEntity(this.getPos(), 1, syncData);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		readFromNBT(pkt.getNbtCompound());
		worldObj.markBlockRangeForRenderUpdate(getPos(), getPos());
	}
}
