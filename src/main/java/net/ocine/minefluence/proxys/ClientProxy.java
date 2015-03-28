package net.ocine.minefluence.proxys;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.ocine.minefluence.MineFluence;
import net.ocine.minefluence.blocks.*;
import net.ocine.minefluence.items.ItemBacteriaFlask;
import net.ocine.minefluence.items.Items;

public class ClientProxy extends CommonProxy {

	public static int MACHINE_BLOCK_RENDER_ID;

	@Override
	public void registerRenderThings() {

		// Item
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
		renderItem.getItemModelMesher().register(Items.bacteriaFlask, 0, new ModelResourceLocation(MineFluence.MODID + ":" + ItemBacteriaFlask.name, "inventory"));
		renderItem.getItemModelMesher().register(Items.bacteriaFlask, 1, new ModelResourceLocation(MineFluence.MODID + ":" + ItemBacteriaFlask.name, "inventory"));
		renderItem.getItemModelMesher().register(Items.bacteriaFlask, 2, new ModelResourceLocation(MineFluence.MODID + ":" + ItemBacteriaFlask.name, "inventory"));

		// Blocks
		int i = 0;
		for (MachineBlockCooler.Variant variant : MachineBlockCooler.Variant.values()) {
			String name = MineFluence.MODID + ":" + MachineBlockCooler.NAME + "." + variant.getName();
			ModelBakery.addVariantName(GameRegistry.findItem(MineFluence.MODID, MachineBlockCooler.NAME),
					name);
			renderItem.getItemModelMesher().register(Item.getItemFromBlock(Blocks.machineBlockCooler), i++,
					new ModelResourceLocation(name, "inventory"));
		}

		i = 0;
		for (MachineBlockWorker.Variant variant : MachineBlockWorker.Variant.values()) {
			String name = MineFluence.MODID + ":" + MachineBlockWorker.NAME + "." + variant.getName();
			ModelBakery.addVariantName(GameRegistry.findItem(MineFluence.MODID, MachineBlockWorker.NAME),
					name);
			renderItem.getItemModelMesher().register(Item.getItemFromBlock(Blocks.machineBlockWorker), i++,
					new ModelResourceLocation(name, "inventory"));
		}

		renderItem.getItemModelMesher().register(Item.getItemFromBlock(Blocks.machineBlockOutput), 0,
				new ModelResourceLocation(MineFluence.MODID + ":" + MachineBlockOutput.NAME, "inventory"));

		renderItem.getItemModelMesher().register(Item.getItemFromBlock(Blocks.machineBlockInput), 0,
				new ModelResourceLocation(MineFluence.MODID + ":" + MachineBlockInput.NAME, "inventory"));

		renderItem.getItemModelMesher().register(Item.getItemFromBlock(Blocks.machineBlockCore), 0,
				new ModelResourceLocation(MineFluence.MODID + ":" + MachineBlockCore.NAME, "inventory"));

		renderItem.getItemModelMesher().register(Item.getItemFromBlock(Blocks.machineBlockDisplay), 0,
				new ModelResourceLocation(MineFluence.MODID + ":" + MachineBlockDisplay.NAME, "inventory"));

	}

}
