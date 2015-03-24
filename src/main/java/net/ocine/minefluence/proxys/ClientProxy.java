package net.ocine.minefluence.proxys;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.ocine.minefluence.MineFluence;
import net.ocine.minefluence.blocks.Blocks;
import net.ocine.minefluence.blocks.MachineBlocks;
import net.ocine.minefluence.blocks.tileentities.*;
import net.ocine.minefluence.items.ItemBacteriaFlask;
import net.ocine.minefluence.items.Items;
import net.ocine.minefluence.renderer.blocks.MachineBlockRenderer;

public class ClientProxy extends CommonProxy {

	public static int MACHINE_BLOCK_RENDER_ID;

	@Override
	public void registerRenderThings() {
		MachineBlockRenderer renderer = new MachineBlockRenderer();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCore.class, renderer);
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDisplay.class, renderer);
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityInput.class, renderer);
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityOutput.class, renderer);
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWorker.class, renderer);
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHyperworker.class, renderer);
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCooler.class, renderer);

		// Item
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
		renderItem.getItemModelMesher().register(Items.bacteriaFlask, 0, new ModelResourceLocation(MineFluence.MODID + ":" + ItemBacteriaFlask.name, "inventory"));
		renderItem.getItemModelMesher().register(Items.bacteriaFlask, 1, new ModelResourceLocation(MineFluence.MODID + ":" + ItemBacteriaFlask.name, "inventory"));
		renderItem.getItemModelMesher().register(Items.bacteriaFlask, 2, new ModelResourceLocation(MineFluence.MODID + ":" + ItemBacteriaFlask.name, "inventory"));

		// Blocks
		Item itemMachineBlock = GameRegistry.findItem(MineFluence.MODID, MachineBlocks.UNLOCALIZED_NAME);
		ModelBakery.addVariantName(itemMachineBlock, MineFluence.MODID + ":" + MachineBlocks.UNLOCALIZED_NAME);
		ModelBakery.addVariantName(itemMachineBlock, MineFluence.MODID + ":" + MachineBlocks.UNLOCALIZED_NAME + "_display");
		ModelBakery.addVariantName(itemMachineBlock, MineFluence.MODID + ":" + MachineBlocks.UNLOCALIZED_NAME + "_input");
		ModelBakery.addVariantName(itemMachineBlock, MineFluence.MODID + ":" + MachineBlocks.UNLOCALIZED_NAME + "_output");
		ModelBakery.addVariantName(itemMachineBlock, MineFluence.MODID + ":" + MachineBlocks.UNLOCALIZED_NAME + "_worker");
		ModelBakery.addVariantName(itemMachineBlock, MineFluence.MODID + ":" + MachineBlocks.UNLOCALIZED_NAME + "_hyperworker");
		ModelBakery.addVariantName(itemMachineBlock, MineFluence.MODID + ":" + MachineBlocks.UNLOCALIZED_NAME + "_fan");
		ModelBakery.addVariantName(itemMachineBlock, MineFluence.MODID + ":" + MachineBlocks.UNLOCALIZED_NAME + "_watercooling");

		renderItem.getItemModelMesher().register(Item.getItemFromBlock(Blocks.machineBlocks), 0, new ModelResourceLocation(MineFluence.MODID + ":" + MachineBlocks.UNLOCALIZED_NAME, "inventory"));
		renderItem.getItemModelMesher().register(Item.getItemFromBlock(Blocks.machineBlocks), 1, new ModelResourceLocation(MineFluence.MODID + ":" + MachineBlocks.UNLOCALIZED_NAME + "_display", "inventory"));
		renderItem.getItemModelMesher().register(Item.getItemFromBlock(Blocks.machineBlocks), 2, new ModelResourceLocation(MineFluence.MODID + ":" + MachineBlocks.UNLOCALIZED_NAME + "_input", "inventory"));
		renderItem.getItemModelMesher().register(Item.getItemFromBlock(Blocks.machineBlocks), 3, new ModelResourceLocation(MineFluence.MODID + ":" + MachineBlocks.UNLOCALIZED_NAME + "_output", "inventory"));
		renderItem.getItemModelMesher().register(Item.getItemFromBlock(Blocks.machineBlocks), 4, new ModelResourceLocation(MineFluence.MODID + ":" + MachineBlocks.UNLOCALIZED_NAME + "_worker", "inventory"));
		renderItem.getItemModelMesher().register(Item.getItemFromBlock(Blocks.machineBlocks), 5, new ModelResourceLocation(MineFluence.MODID + ":" + MachineBlocks.UNLOCALIZED_NAME + "_hyperworker", "inventory"));
		renderItem.getItemModelMesher().register(Item.getItemFromBlock(Blocks.machineBlocks), 6, new ModelResourceLocation(MineFluence.MODID + ":" + MachineBlocks.UNLOCALIZED_NAME + "_fan", "inventory"));
		renderItem.getItemModelMesher().register(Item.getItemFromBlock(Blocks.machineBlocks), 7, new ModelResourceLocation(MineFluence.MODID + ":" + MachineBlocks.UNLOCALIZED_NAME + "_watercooling", "inventory"));
	}

}
