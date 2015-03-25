package net.ocine.minefluence.blocks.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.EnumFacing;
import net.ocine.minefluence.blocks.MachineBlocks;

public class TileEntityDisplay extends TileEntityMachinePart {
	public int progress;

	public EnumFacing facing = EnumFacing.EAST;

	@Override
	public MachineBlocks.Machines getType() {
		return MachineBlocks.Machines.DISPLAY;
	}

	@Override public void readFromNBT(NBTTagCompound p_145839_1_) {
		if(p_145839_1_.hasKey("facing")){
			facing = EnumFacing.byName(p_145839_1_.getString("facing"));
		}
		super.readFromNBT(p_145839_1_);
	}

	@Override public void writeToNBT(NBTTagCompound p_145841_1_) {
		p_145841_1_.setString("facing", facing.toString());
		super.writeToNBT(p_145841_1_);
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound syncData = new NBTTagCompound();
		writeToNBT(syncData);
		if (!isPartOfMachine()) {
			progress = -1;
		}
		syncData.setInteger("progress", progress);
		syncData.setString("facing", facing.toString());
		return new S35PacketUpdateTileEntity(this.getPos(), 1, syncData);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		readFromNBT(pkt.getNbtCompound());
		progress = pkt.getNbtCompound().getInteger("progress");
		facing = EnumFacing.byName(pkt.getNbtCompound().getString("facing"));
	}
}
