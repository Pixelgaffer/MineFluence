package net.ocine.minefluence.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.ocine.minefluence.blocks.tileentities.TileEntityDisplay;

public class GuiDisplay extends GuiScreen {

	private TileEntityDisplay entity;

	public GuiDisplay(TileEntityDisplay entity) {
		this.entity = entity;
	}

	@Override
	public void drawBackground(int p_146278_1_) {
		mc.renderEngine.bindTexture(new ResourceLocation("minefluence", "textures/gui/display.png"));
		int k = this.width / 2;
		int l = this.height / 2;
		this.drawTexturedModalRect(0, 0, 0, 0, this.width, this.height);
	}
}
