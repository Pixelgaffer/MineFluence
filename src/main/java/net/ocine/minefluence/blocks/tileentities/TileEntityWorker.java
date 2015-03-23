package net.ocine.minefluence.blocks.tileentities;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.ocine.minefluence.MineFluence;
import net.ocine.minefluence.blocks.MachineBlocks;

public class TileEntityWorker extends TileEntityMachinePart {
	@SideOnly(Side.CLIENT)
	private static ResourceLocation texture = new ResourceLocation(MineFluence.MODID, "textures/blocks/machineblocks/machineblock_worker.png");

	@SideOnly(Side.CLIENT)
	@Override public BorderType getBorderType() {
		return BorderType.DEFAULT;
	}

	@Override
	public MachineBlocks.Machines getType() {
		return MachineBlocks.Machines.WORKER;
	}

	@SideOnly(Side.CLIENT)
	@Override public ResourceLocation getTexture() {
		return texture;
	}
}
