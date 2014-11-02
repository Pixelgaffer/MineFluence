package net.ocine.minefluence.machines;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import scala.actors.threadpool.Arrays;

/**
 * Created by florian on 30.10.14.
 */
public class MachineLogicManager {

	public static AbstractMachineLogic dirtToDiamond = new TransformationLogic("Magie Mit Erde", 400,
			Arrays.asList(new Object[] { new ItemStack(Blocks.dirt) }), Arrays.asList(new Object[] { new ItemStack(Items.diamond) }));

	public static AbstractMachineLogic magicIron = new TransformationLogic("Magie Mit Erde 2", 800,
			Arrays.asList(new Object[] { new ItemStack(Blocks.dirt), new ItemStack(Blocks.stone) }),
			Arrays.asList(new Object[] { new ItemStack(Blocks.diamond_block), new ItemStack(Blocks.iron_bars) }));

	public static AbstractMachineLogic getApplicatableLogic(Machine machine) {
		if (machine.getInputs() == 1 && machine.getOutputs() == 1 && machine.getWorkers() >= 1) {
			return dirtToDiamond;
		}
		if (machine.getInputs() == 2 && machine.getOutputs() == 2 && machine.getWorkers() >= 1) {
			return magicIron;
		}
		return null;
	}
}
