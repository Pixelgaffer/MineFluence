package net.ocine.minefluence.blocks.tileentities;

import net.minecraft.tileentity.TileEntity;

public class TileEntityCore extends TileEntity implements Machine {
	private int counter = 0;
	@Override
	public void updateEntity() {
		if(counter >= 40) {
			counter = 0;
			//TODO Implement structure recognition
		}
		counter++;
	}
	
	public Machine getMachine(){
        return this;
    }

    @Override
    public void addPart(IMachinePart machinePart) {

    }

    @Override
    public void removePart(IMachinePart machinePart) {

    }

    @Override
    public int getX() {
        return xCoord;
    }

    @Override
    public int getY() {
        return yCoord;
    }

    @Override
    public int getZ() {
        return zCoord;
    }
}
