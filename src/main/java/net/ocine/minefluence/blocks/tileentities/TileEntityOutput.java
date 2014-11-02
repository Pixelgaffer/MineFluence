package net.ocine.minefluence.blocks.tileentities;

import net.minecraft.item.ItemStack;
import net.ocine.minefluence.blocks.MachineBlocks;

public class TileEntityOutput extends TileEntityGuiMachinePart {

	public TileEntityOutput() {
		super(1, "tileEntityOutput");
	}

	@Override
	public MachineBlocks.Machines getType() {
		return MachineBlocks.Machines.OUTPUT;
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return false;
	}

	@Override
	public String getTextureName() {
		return "machineblock_output.png";
	}

	@Override
	public String getBorder() {
		//TODO Implement
		return "";
	}
}
