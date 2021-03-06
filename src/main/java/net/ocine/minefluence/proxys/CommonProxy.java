package net.ocine.minefluence.proxys;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.ocine.minefluence.blocks.tileentities.*;

public class CommonProxy {

	public void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityCore.class, "TileEntityCore");
		GameRegistry.registerTileEntity(TileEntityDisplay.class, "TileEntityDisplay");
		GameRegistry.registerTileEntity(TileEntityInput.class, "TileEntityInput");
		GameRegistry.registerTileEntity(TileEntityOutput.class, "TileEntityOutput");
		GameRegistry.registerTileEntity(TileEntityWorker.class, "TileEntityWorker");
		GameRegistry.registerTileEntity(TileEntityHyperworker.class, "TileEntityHyperworker");
		GameRegistry.registerTileEntity(TileEntityCooler.class, "TileEntityCooler");
	}

	public void registerRenderThings() {
	}

}
