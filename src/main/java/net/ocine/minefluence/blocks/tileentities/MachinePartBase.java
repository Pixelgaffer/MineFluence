package net.ocine.minefluence.blocks.tileentities;

<<<<<<< HEAD
public class MachinePartBase implements IMachinePart{
    private Machine machine;

=======
import javax.annotation.Nullable;

public class MachinePartBase implements IMachinePart{
    private Machine machine;

    @Nullable
>>>>>>> origin/master
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
