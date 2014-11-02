package net.ocine.minefluence.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.ocine.minefluence.blocks.containers.ContainerInput;
import net.ocine.minefluence.blocks.tileentities.InventoryTileEntity;
import org.lwjgl.opengl.GL11;

public class GuiInput extends GuiContainer {

	public GuiInput(InventoryPlayer inventory, InventoryTileEntity tileEntity) {
		super(new ContainerInput(inventory, tileEntity));
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(new ResourceLocation("minefluence", "textures/gui/input.png"));
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		fontRendererObj.drawString("Input", 8, 6, 4210752);
	}

}
