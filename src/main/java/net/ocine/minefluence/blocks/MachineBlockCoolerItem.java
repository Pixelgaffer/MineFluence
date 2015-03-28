package net.ocine.minefluence.blocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import java.util.List;

public class MachineBlockCoolerItem extends ItemBlock {

	public MachineBlockCoolerItem(Block block) {
		super(block);
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int metadata) {
		return metadata;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return "tile." + MachineBlockCooler.NAME + "." + MachineBlockCooler.Variant.values()[itemstack.getItemDamage()].getName();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < MachineBlockCooler.Variant.values().length; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}

}
