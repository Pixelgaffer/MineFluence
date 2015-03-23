package net.ocine.minefluence.blocks.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.ocine.minefluence.MineFluence;
import net.ocine.minefluence.blocks.MachineBlocks;

public class TileEntityDisplay extends TileEntityMachinePart {
	public int progress;
	@SideOnly(Side.CLIENT)
	private static ResourceLocation texture = new ResourceLocation(MineFluence.MODID, "textures/blocks/machineblocks/machineblock_display.png");
	@SideOnly(Side.CLIENT)
	private static ResourceLocation texture_0 = new ResourceLocation(MineFluence.MODID, "textures/blocks/machineblocks/machineblock_display_0.png");
	@SideOnly(Side.CLIENT)
	private static ResourceLocation texture_20 = new ResourceLocation(MineFluence.MODID, "textures/blocks/machineblocks/machineblock_display_20.png");
	@SideOnly(Side.CLIENT)
	private static ResourceLocation texture_40 = new ResourceLocation(MineFluence.MODID, "textures/blocks/machineblocks/machineblock_display_40.png");
	@SideOnly(Side.CLIENT)
	private static ResourceLocation texture_70 = new ResourceLocation(MineFluence.MODID, "textures/blocks/machineblocks/machineblock_display_70.png");
	@SideOnly(Side.CLIENT)
	private static ResourceLocation texture_90 = new ResourceLocation(MineFluence.MODID, "textures/blocks/machineblocks/machineblock_display_90.png");
	@SideOnly(Side.CLIENT)
	private static ResourceLocation texture_100 = new ResourceLocation(MineFluence.MODID, "textures/blocks/machineblocks/machineblock_display_100.png");

	@SideOnly(Side.CLIENT)
	@Override public BorderType getBorderType() {
		return BorderType.DEFAULT;
	}

	@Override
	public MachineBlocks.Machines getType() {
		return MachineBlocks.Machines.DISPLAY;
	}

	@SideOnly(Side.CLIENT)
	@Override public ResourceLocation getTexture() {
		if (progress == -1) {
			return texture;
		}
		if (progress < 20) {
			return texture_0;
		}
		if (progress < 40) {
			return texture_20;
		}
		if (progress < 70) {
			return texture_40;
		}
		if (progress < 90) {
			return texture_70;
		}
		if (progress < 100) {
			return texture_90;
		}
		return texture_100;
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
}
