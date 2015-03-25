package net.ocine.minefluence.blocks.tileentities;

import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.ocine.minefluence.blocks.MachineBlocks.Machines;

public class TileEntityCooler extends TileEntityMachinePart implements IUpdatePlayerListBox {
	
	private int coolAmount;
	private Machines type;
	
	public TileEntityCooler(Machines type, int coolAmount) {
		this.coolAmount = coolAmount;
		this.type = type;
	}
	
	@Override
	public Machines getType() {
		return type;
	}

	@Override
	public void update() {
		if(!worldObj.isRemote && isPartOfMachine()) {
			getMachine().cool(coolAmount);
		}
	}
}
