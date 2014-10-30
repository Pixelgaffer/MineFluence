package net.ocine.minefluence.blocks.containers;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.ocine.minefluence.blocks.tileentities.InventoryTileEntity;

public class ContainerInput extends InventoryContainer {

	public ContainerInput(InventoryPlayer inventoryPlayer, InventoryTileEntity tileEntity) {
		super(inventoryPlayer, tileEntity);
		addSlotToContainer(new Slot(tileEntity, 0, 80, 32));
	}
	
}
