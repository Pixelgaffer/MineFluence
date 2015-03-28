package net.ocine.minefluence.blocks.tileentities;

import net.minecraft.item.ItemStack;

public class TileEntityOutput extends TileEntityGuiMachinePart {

	public TileEntityOutput() {
		super(1, "tileEntityOutput");
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return false;
	}
}
