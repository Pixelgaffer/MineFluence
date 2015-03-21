package net.ocine.minefluence.blocks.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.ocine.minefluence.blocks.MachineBlocks;

public class TileEntityDisplay extends TileEntityMachinePart {

	public int progress;

	@Override
	public MachineBlocks.Machines getType() {
		return MachineBlocks.Machines.DISPLAY;
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound syncData = new NBTTagCompound();
		writeToNBT(syncData);
		if (!isPartOfMachine()) {
			progress = -1;
		}
		syncData.setInteger("progress", progress);
		return new S35PacketUpdateTileEntity(this.getPos(), 1, syncData);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		readFromNBT(pkt.getNbtCompound());
		progress = pkt.getNbtCompound().getInteger("progress");
		worldObj.scheduleUpdate(getPos(), getBlockType(), 0);
		worldObj.markBlockRangeForRenderUpdate(getPos().getX(), getPos().getY(), getPos().getZ(), getPos().getX(), getPos().getY(), getPos().getZ());
	}

	@Override
	public String getTextureName() {
		return "machineblock_display.png";
	}

	@Override
	public String getBorder() {
		//TODO Implement
		return "";
	}
}
