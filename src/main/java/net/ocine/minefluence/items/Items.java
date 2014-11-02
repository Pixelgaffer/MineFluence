package net.ocine.minefluence.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class Items {

	public static Item bacteriaFlask;

	public static void initItems(CreativeTabs tab) {
		bacteriaFlask = new ItemBacteriaFlask(tab);
	}

}
