package net.ocine.minefluence.blocks.tileentities;

import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.ocine.minefluence.blocks.MachineBlockCooler;

public class TileEntityCooler extends TileEntityMachinePart implements IUpdatePlayerListBox {

	public TileEntityCooler() {
	}

	@Override
	public void update() {
		if(!worldObj.isRemote && getMachine() != null) {
			getMachine().cool(getCoolAmount());
		}
	}

	public int getCoolAmount(){
		switch ((MachineBlockCooler.Variant)worldObj.getBlockState(getPos()).getValue(MachineBlockCooler.PROP_VARIANT)){
			case FAN:
				return 7;
			case WATERCOOLING:
				return 20;
			default:
				return 0;
		}
	}
}
