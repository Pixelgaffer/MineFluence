package net.ocine.minefluence.blocks.tileentities;

import net.minecraft.util.IStringSerializable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.ocine.minefluence.machines.Machine;

import javax.annotation.Nullable;

public interface IMachinePart {
	@Nullable public Machine getMachine();

	/**
	 * Method to check whether this block is part of a machine
	 *
	 * @return true if this block is part of a machine
	 */
	public boolean isPartOfMachine();

	/**
	 * Assigns this part to a machine
	 *
	 * @param machine the machine
	 * @param force   if true the block is assigned to the new machine even if it's already part of a machine
	 * @return true on success
	 */
	public boolean assignToMachine(Machine machine, boolean force);

	/**
	 * Makes this block not being part of any machine
	 *
	 * @return true on success
	 */
	public boolean removeFromMachine();

	@SideOnly(Side.CLIENT)
	public BorderType getBorderType();

	@SideOnly(Side.CLIENT)
	public enum BorderType implements IStringSerializable{
		DEFAULT, RED, GREEN;

		@Override public String getName() {
			return name().toLowerCase();
		}
	}
}
