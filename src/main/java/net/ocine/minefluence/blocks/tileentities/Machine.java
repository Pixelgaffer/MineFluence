package net.ocine.minefluence.blocks.tileentities;

public interface Machine {

    public void addPart(IMachinePart machinePart);

    public void removePart(IMachinePart machinePart);

    public int getX();

    public int getY();

    public int getZ();
}
