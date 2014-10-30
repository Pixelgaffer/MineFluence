package net.ocine.minefluence.blocks.tileentities;

import net.minecraft.item.ItemStack;

import java.util.Collection;

/**
 * Created by florian on 30.10.14.
 */
public class TransformationLogic extends AbstractMachineLogic {

    Collection<ItemStack> input;
    Collection<ItemStack> output;

    public TransformationLogic(String name, Collection<ItemStack> input, Collection<ItemStack> output) {
        super(name);
        this.input = input;
        this.output = output;
    }

    @Override
    public Collection<ItemStack> getInput(Collection<ItemStack> items) {

        for(ItemStack is: input){
            boolean has = false;
            for(ItemStack is2: items){
                if(ItemStack.areItemStacksEqual(is, is2))has = true;
            }
            if(!has)return null;
        }
        return input;
    }

    @Override
    public Collection<ItemStack> getOutput(Collection<ItemStack> items) {
        return null;
    }
}
