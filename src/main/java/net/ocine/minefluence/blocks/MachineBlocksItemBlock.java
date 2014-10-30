package net.ocine.minefluence.blocks;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class MachineBlocksItemBlock extends ItemBlock {
	
	public MachineBlocksItemBlock() {
		super(Blocks.machineBlocks);
		setHasSubtypes(true);
	}
	
	@Override
	public int getMetadata(int metadata) {
		return metadata;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return MachineBlocks.UNLOCALIZED_NAME + "." + MachineBlocks.names[itemstack.getItemDamage()];
	}
	
}
