package net.ocine.minefluence.blocks.tileentities;

import net.minecraft.item.ItemStack;

import java.util.Collection;

/**
 * Created by florian on 30.10.14.
 */
public abstract class AbstractMachineLogic {

    String name;

    public AbstractMachineLogic(String name) {
        this.name = name;
    }

    /**
     * Hi
     * @param items items in the input blocks
     * @return items to be transfered into the core
     */
    public abstract Collection<ItemStack> getInput(Collection<ItemStack> items);

    /**
     *
     * @param items items in the core
     * @return items to be placed in the output blocks
     */
    public abstract Collection<ItemStack> getOutput(Collection<ItemStack> items);


    public String getName() {
        return name;
    }
}
