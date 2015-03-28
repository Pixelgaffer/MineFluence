package net.ocine.minefluence.blocks.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

public class TileEntityDisplay extends TileEntityMachinePart {
	public int progress;

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
	}
}
