package net.ocine.minefluence.items;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemBacteriaFlask extends Item {
	
	public static final String name = "bacteriaFlask";
	
	private List<Bacteria> bacterias = new ArrayList<Bacteria>();
	
	public ItemBacteriaFlask(CreativeTabs tab) {
		setHasSubtypes(true);
		//setMaxDamage(0);
		setCreativeTab(tab);
		bacterias.add(new Bacteria(0xff0000, "red"));
		bacterias.add(new Bacteria(0x00ff00, "green"));
		bacterias.add(new Bacteria(0x0000ff, "blue"));
		System.out.println("Registering Item");
		GameRegistry.registerItem(this, "minefluence." + name);
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
		System.out.println("Registering Sub-Items");
		for(int i = 0; i < bacterias.size(); i++) {
			System.out.println("Adding Bacteria Flask!");
			subItems.add(new ItemStack(item, 1, i));
		}
	}
	
}
