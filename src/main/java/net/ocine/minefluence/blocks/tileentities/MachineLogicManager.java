package net.ocine.minefluence.blocks.tileentities;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import scala.actors.threadpool.Arrays;

/**
 * Created by florian on 30.10.14.
 */
public class MachineLogicManager {

    public static AbstractMachineLogic dirtToDiamond = new TransformationLogic("Magie Mit Erde", 400,
            Arrays.asList(new Object[]{new ItemStack(Blocks.dirt)}), Arrays.asList(new Object[]{new ItemStack(Items.diamond)}));

    public static AbstractMachineLogic getApplicatableLogic(Machine machine){
        if(machine.getInputs() == 1 && machine.getOutputs() == 1)return dirtToDiamond;
        return null;
    }
}
