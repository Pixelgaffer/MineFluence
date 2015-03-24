package net.ocine.minefluence.blocks.tileentities;

import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.util.ResourceLocation;
import net.ocine.minefluence.MineFluence;
import net.ocine.minefluence.blocks.MachineBlocks.Machines;

public class TileEntityCooler extends TileEntityMachinePart implements IUpdatePlayerListBox {
	
	private int coolAmount;
	private Machines type;
	private ResourceLocation texture;
	
	public TileEntityCooler(Machines type, String location, int coolAmount) {
		this(type, new ResourceLocation(MineFluence.MODID, location), coolAmount);
	}
	
	public TileEntityCooler(Machines type, ResourceLocation texture, int coolAmount) {
		this.coolAmount = coolAmount;
		this.type = type;
		this.texture = texture;
	}
	
	@Override
	public Machines getType() {
		return type;
	}

	@Override
	public ResourceLocation getTexture() {
		return texture;
	}

	@Override
	public void update() {
		if(!worldObj.isRemote) {
			getMachine().cool(coolAmount);
		}
	}
}
