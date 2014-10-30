package net.ocine.minefluence.blocks.tileentities;

public class MachinePartBase{
    private Machine machine;
    private IMachinePart part;

    public MachinePartBase(IMachinePart part) {
        this.part = part;
    }

    public Machine getMachine() {
        return machine;
    }

    public boolean isPartOfMachine() {
        return machine != null;
    }

    public boolean assignToMachine(Machine machine, boolean force) {
        if(isPartOfMachine() && !force)return false;
        this.machine = machine;
        machine.addPart(part);
        return true;
    }

    public boolean removeFromMachine() {
        if(!isPartOfMachine())return false;
        machine.removePart(part);
        machine = null;
        return true;
    }
}
