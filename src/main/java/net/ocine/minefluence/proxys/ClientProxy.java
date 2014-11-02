package net.ocine.minefluence.proxys;

import net.ocine.minefluence.blocks.tileentities.TileEntityCore;
import net.ocine.minefluence.blocks.tileentities.TileEntityDisplay;
import net.ocine.minefluence.blocks.tileentities.TileEntityHyperworker;
import net.ocine.minefluence.blocks.tileentities.TileEntityInput;
import net.ocine.minefluence.blocks.tileentities.TileEntityOutput;
import net.ocine.minefluence.blocks.tileentities.TileEntityWorker;
import net.ocine.minefluence.renderer.blocks.MachineBlockRenderer;
import cpw.mods.fml.client.registry.ClientRegistry;

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
	}
	
}
