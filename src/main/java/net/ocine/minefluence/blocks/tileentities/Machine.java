package net.ocine.minefluence.blocks.tileentities;

import java.util.Collection;

public interface Machine {

    public void addPart(IMachinePart machinePart);

    public void removePart(IMachinePart machinePart);

    public int getX();

    public int getY();

    public int getZ();

    public Collection<IMachinePart> getParts();

    public boolean isActive();

    public AbstractMachineLogic getLogic();

    public int getWorkers();
    public int getInputs();
    public int getOutputs();
}