package net.ocine.minefluence.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ItemBacteriaFlask extends Item {

	public static final String name = "bacteriaFlask";

	private List<Bacteria> bacterias = new ArrayList<Bacteria>();

	public ItemBacteriaFlask(CreativeTabs tab) {
		setHasSubtypes(true);
		setMaxDamage(0);
		setCreativeTab(tab);
		setTextureName("minefluence:bacteria");
		bacterias.add(new Bacteria(0xff0000, "red"));
		bacterias.add(new Bacteria(0x00ff00, "green"));
		bacterias.add(new Bacteria(0x0000ff, "blue"));
		GameRegistry.registerItem(this, "minefluence." + name);
	}

	@Override
	public boolean requiresMultipleRenderPasses() {
		return true;
	}

	@Override
	public int getColorFromItemStack(ItemStack stack, int p_82790_2_) {
		return bacterias.get(stack.getItemDamage()).color;
	}

	public List<Bacteria> getBacterias() {
		return bacterias;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "bacteriaFlask." + bacterias.get(stack.getItemDamage()).name;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void getSubItems(Item item, CreativeTabs tab, @SuppressWarnings("rawtypes") List subItems) {
		for (int i = 0; i < bacterias.size(); i++) {
			subItems.add(new ItemStack(item, 1, i));
		}
	}

}
