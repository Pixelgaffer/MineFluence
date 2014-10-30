package net.ocine.minefluence.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public enum GUIs {
	;
	
	private static final ContainerCreator ITEM_GUI_CONTAINER_CREATOR = new ContainerCreator() {
		public Object getContainer(World world, EntityPlayer player, int x, int y, int z) {
			return new Object();
		}
	};
	
	private GuiCreator guiCreator;
	private ContainerCreator containerCreator;
	private GUIs(GuiCreator guiCreator, ContainerCreator containerCreator) {
		this.guiCreator = guiCreator;
		this.containerCreator = containerCreator;
	}
	
	public Object getGuiScreen(World world, EntityPlayer player, int x, int y, int z) {
		return guiCreator.getGuiScreen(world, player, x, y, z);
	}
	public Object getContainer(World world, EntityPlayer player, int x, int y, int z) {
		return containerCreator.getContainer(world, player, x, y, z);
	}
	
	private interface GuiCreator {
		public Object getGuiScreen(World world, EntityPlayer player, int x, int y, int z);
	}
	private interface ContainerCreator {
		public Object getContainer(World world, EntityPlayer player, int x, int y, int z);
	}
}