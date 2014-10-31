package net.ocine.minefluence.blocks.tileentities;

import net.ocine.minefluence.blocks.MachineBlocks;
import net.ocine.minefluence.machines.Machine;

import javax.annotation.Nullable;

public interface IMachinePart {
	@Nullable public Machine getMachine();

    /**
     * Method to check whether this block is part of a machine
     * @return true if this block is part of a machine
     */
    public boolean isPartOfMachine();

    /**
     * Assigns this part to a machine
     * @param machine the machine
     * @param force if true the block is assigned to the new machine even if it's already part of a machine
     * @return true on success
     */
    public boolean assignToMachine(Machine machine, boolean force);

    /**
     * Makes this block not being part of any machine
     * @return true on success
     */
    public boolean removeFromMachine();

    public MachineBlocks.Machines getType();
}
