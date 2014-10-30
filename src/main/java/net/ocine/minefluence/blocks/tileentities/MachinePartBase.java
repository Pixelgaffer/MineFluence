package net.ocine.minefluence.blocks.tileentities;

public class MachinePartBase implements IMachinePart{
    private Machine machine;

    @Override
    public Machine getMachine() {
        return machine;
    }

    @Override
    public boolean isPartOfMachine() {
        return machine != null;
    }

    @Override
    public boolean assignToMachine(Machine machine, boolean force) {
        if(isPartOfMachine() && !force)return false;
        this.machine = machine;
        machine.addPart(this);
        return true;
    }

    @Override
    public boolean removeFromMachine() {
        if(!isPartOfMachine())return false;
        machine.removePart(this);
        machine = null;
        return true;
    }
}
