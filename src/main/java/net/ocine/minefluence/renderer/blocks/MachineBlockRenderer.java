package net.ocine.minefluence.renderer.blocks;

import com.google.common.collect.Maps;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.ocine.minefluence.blocks.tileentities.IMachinePart;
import net.ocine.minefluence.blocks.tileentities.TileEntityDisplay;
import net.ocine.minefluence.models.ModelMachineBlockBlock;
import net.ocine.minefluence.models.ModelMachineBlockBorder;
import org.lwjgl.opengl.GL11;

import java.util.Map;

public class MachineBlockRenderer extends TileEntitySpecialRenderer {
	private static final Map<IMachinePart.BorderType, ResourceLocation> borders = Maps.newHashMap();
	private ModelMachineBlockBlock modelBase;
	private ModelMachineBlockBorder modelBorder;

	public MachineBlockRenderer() {
		modelBase = new ModelMachineBlockBlock();
		modelBorder = new ModelMachineBlockBorder();
	}

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float scale, int i) {
		IMachinePart machinePart = (IMachinePart) tileEntity;
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		if(tileEntity instanceof TileEntityDisplay) {
			EnumFacing face = ((TileEntityDisplay) tileEntity).facing;
			float rot = 90;
			if(face == EnumFacing.NORTH)rot = 180;
			if(face == EnumFacing.WEST)rot = 270;
			if(face == EnumFacing.SOUTH)rot = 0;
			GL11.glRotatef(rot, 0, 1, 0);
		}
		GL11.glPushMatrix();
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		Minecraft.getMinecraft().renderEngine.bindTexture(machinePart.getTexture());
		modelBase.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		Minecraft.getMinecraft().renderEngine.bindTexture(borders.get(machinePart.getBorderType()));
		modelBorder.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}

	static {
		borders.put(IMachinePart.BorderType.DEFAULT, new ResourceLocation("minefluence", "textures/blocks/machineblocks/machineblock_border.png"));
		borders.put(IMachinePart.BorderType.RED, new ResourceLocation("minefluence", "textures/blocks/machineblocks/machineblock_border_red.png"));
		borders.put(IMachinePart.BorderType.GREEN, new ResourceLocation("minefluence", "textures/blocks/machineblocks/machineblock_border_green.png"));
	}
}