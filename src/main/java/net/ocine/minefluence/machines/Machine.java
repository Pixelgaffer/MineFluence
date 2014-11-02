package net.ocine.minefluence.machines;

import net.minecraft.item.ItemStack;
import net.ocine.minefluence.blocks.tileentities.IMachinePart;

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

	public boolean isTransformationInProgress();

	public int getRemainingTime();

	public int getProcessTime();

	public ItemStack[] getInputInventory();

	public void setInputInventory(ItemStack[] inv);

	public ItemStack[] getOutputInventory();

	public void setOutputInventory(ItemStack[] inv);

	/**
	 * percentage  progress for display
	 *
	 * @return int from 0 to 100 indicating progress
	 */
	public int getProgressForDisplay();
}