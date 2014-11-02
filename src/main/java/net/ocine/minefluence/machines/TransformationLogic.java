package net.ocine.minefluence.machines;

import net.minecraft.item.ItemStack;

import java.util.Collection;

public class TransformationLogic extends AbstractMachineLogic {

	Collection<ItemStack> input;
	Collection<ItemStack> output;

	public TransformationLogic(String name, int processTime, Collection<ItemStack> input, Collection<ItemStack> output) {
		super(name, processTime);
		this.input = input;
		this.output = output;
	}

	@Override
	public Collection<ItemStack> getInput(Collection<ItemStack> items) {
		return input;
	}

	@Override
	public Collection<ItemStack> getOutput(Collection<ItemStack> items) {
		return output;
	}
}
