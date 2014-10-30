package net.ocine.minefluence.blocks.tileentities;

import net.minecraft.tileentity.TileEntity;

import java.util.ArrayList;
import java.util.List;

public class TileEntityCore extends TileEntity implements Machine, IMachinePart {
    List<IMachinePart> parts = new ArrayList<IMachinePart>();
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
    public boolean isPartOfMachine() {
        // of cause it is - we are the machine
        return true;
    }

    @Override
    public boolean assignToMachine(Machine machine, boolean force) {
        // NO THIS CAN'T BE - CRY
        throw new RuntimeException("cry");
    }

    @Override
    public boolean removeFromMachine() {
        for(IMachinePart part: parts){
            part.removeFromMachine();
        }
        return true;
    }

    @Override
    public void addPart(IMachinePart machinePart) {
        parts.add(machinePart);
    }

    @Override
    public void removePart(IMachinePart machinePart) {
        parts.remove(machinePart);
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
