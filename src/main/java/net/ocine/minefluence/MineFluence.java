package net.ocine.minefluence;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.ocine.minefluence.blocks.Blocks;
import net.ocine.minefluence.gui.GuiHandler;
import net.ocine.minefluence.items.Items;
import net.ocine.minefluence.proxys.CommonProxy;

@Mod(modid = MineFluence.MODID, version = MineFluence.VERSION)
public class MineFluence {
	public static final String MODID = "minefluence";
	public static final String VERSION = "1.0";

	@SidedProxy(clientSide = Constants.CLIENT_PROXY_CLASS, serverSide = Constants.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;

	@Instance(value = "minefluence")
	public static MineFluence instance;

	private CreativeTabs minefluenceTab = new CreativeTabs("tabMineFluence") {

		@Override
		public Item getTabIconItem() {
			return Items.bacteriaFlask;
		}
	};

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		Items.initItems(minefluenceTab);
		Blocks.initBlocks(minefluenceTab);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.registerTileEntities();
		proxy.registerRenderThings();
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}
}
