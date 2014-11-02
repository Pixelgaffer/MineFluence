package net.ocine.minefluence.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		for (GUIs gui : GUIs.values()) {
			if (gui.ordinal() == ID) {
				return gui.getContainer(world, player, x, y, z);
			}
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		for (GUIs gui : GUIs.values()) {
			if (gui.ordinal() == ID) {
				return gui.getGuiScreen(world, player, x, y, z);
			}
		}
		return null;
	}
}
