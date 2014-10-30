package net.ocine.minefluence.blocks.containers;

import net.minecraft.entity.player.InventoryPlayer;
import net.ocine.minefluence.blocks.tileentities.InventoryTileEntity;

public class ContainerOutput extends InventoryContainer {

	public ContainerOutput(InventoryPlayer inventoryPlayer, InventoryTileEntity tileEntity) {
		super(inventoryPlayer, tileEntity);
		addSlotToContainer(new OutputSlot(tileEntity, 0, 80, 32));
	}
	
}
