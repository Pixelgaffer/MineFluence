package net.ocine.minefluence.blocks.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.ocine.minefluence.blocks.MachineBlocks.Machines;

public class TileEntityCooler extends TileEntityMachinePart implements IUpdatePlayerListBox {
	
	private int coolAmount;
	private Machines type;

	public TileEntityCooler() {
		type = Machines.FAN;
	}
	
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

	@Override public void readFromNBT(NBTTagCompound p_145839_1_) {
		super.readFromNBT(p_145839_1_);
		coolAmount = p_145839_1_.getInteger("coolAmount");
		String coolerType = p_145839_1_.getString("coolerType");
		if(coolerType != null && !coolerType.isEmpty()){
			type = Machines.valueOf(coolerType);
		}
	}

	@Override public void writeToNBT(NBTTagCompound p_145841_1_) {
		super.writeToNBT(p_145841_1_);
		p_145841_1_.setInteger("coolAmount", coolAmount);
		p_145841_1_.setString("coolerType", type.getName());
	}
}
