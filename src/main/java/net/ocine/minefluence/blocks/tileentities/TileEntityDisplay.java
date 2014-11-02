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
    public Packet getDescriptionPacket()
    {
        NBTTagCompound syncData = new NBTTagCompound();
        writeToNBT(syncData);
        if(!isPartOfMachine())progress = -1;
        syncData.setInteger("progress", progress);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, syncData);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
    {
        readFromNBT(pkt.func_148857_g());
        progress = pkt.func_148857_g().getInteger("progress");
        worldObj.scheduleBlockUpdate(xCoord, yCoord, zCoord, getBlockType(), 0);
        worldObj.markBlockRangeForRenderUpdate(xCoord,yCoord,zCoord,xCoord,yCoord,zCoord);
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
