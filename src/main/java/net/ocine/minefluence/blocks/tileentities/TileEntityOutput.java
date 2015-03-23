package net.ocine.minefluence.blocks.tileentities;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.ocine.minefluence.MineFluence;
import net.ocine.minefluence.blocks.MachineBlocks;

public class TileEntityOutput extends TileEntityGuiMachinePart {
	@SideOnly(Side.CLIENT)
	private static ResourceLocation texture = new ResourceLocation(MineFluence.MODID, "textures/blocks/machineblocks/machineblock_output.png");

	public TileEntityOutput() {
		super(1, "tileEntityOutput");
	}

	@SideOnly(Side.CLIENT)
	@Override public BorderType getBorderType() {
		return BorderType.DEFAULT;
	}

	@Override
	public MachineBlocks.Machines getType() {
		return MachineBlocks.Machines.OUTPUT;
	}

	@SideOnly(Side.CLIENT)
	@Override public ResourceLocation getTexture() {
		return texture;
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return false;
	}
}
