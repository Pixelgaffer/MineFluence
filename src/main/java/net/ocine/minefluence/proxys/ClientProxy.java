package net.ocine.minefluence.proxys;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.ocine.minefluence.blocks.tileentities.*;
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
	}

}
