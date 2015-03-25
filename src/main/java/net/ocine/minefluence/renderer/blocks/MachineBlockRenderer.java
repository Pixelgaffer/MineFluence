package net.ocine.minefluence.renderer.blocks;

import com.google.common.collect.Maps;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.ocine.minefluence.MineFluence;
import net.ocine.minefluence.blocks.MachineBlocks;
import net.ocine.minefluence.blocks.tileentities.IMachinePart;
import net.ocine.minefluence.blocks.tileentities.TileEntityDisplay;
import net.ocine.minefluence.models.ModelMachineBlockBlock;
import net.ocine.minefluence.models.ModelMachineBlockBorder;
import org.lwjgl.opengl.GL11;

import java.util.Map;

public class MachineBlockRenderer extends TileEntitySpecialRenderer {
	private static final Map<IMachinePart.BorderType, ResourceLocation> borders = Maps.newHashMap();
	private static final Map<MachineBlocks.Machines, ResourceLocation> textures = Maps.newHashMap();
	private static ResourceLocation texture_display_0 = new ResourceLocation(MineFluence.MODID, "textures/blocks/machineblocks/machineblock_display_0.png");
	private static ResourceLocation texture_display_20 = new ResourceLocation(MineFluence.MODID, "textures/blocks/machineblocks/machineblock_display_20.png");
	private static ResourceLocation texture_display_40 = new ResourceLocation(MineFluence.MODID, "textures/blocks/machineblocks/machineblock_display_40.png");
	private static ResourceLocation texture_display_70 = new ResourceLocation(MineFluence.MODID, "textures/blocks/machineblocks/machineblock_display_70.png");
	private static ResourceLocation texture_display_90 = new ResourceLocation(MineFluence.MODID, "textures/blocks/machineblocks/machineblock_display_90.png");
	private static ResourceLocation texture_display_100 = new ResourceLocation(MineFluence.MODID, "textures/blocks/machineblocks/machineblock_display_100.png");
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
		Minecraft.getMinecraft().renderEngine.bindTexture(textures.get(machinePart.getType()));
		if (machinePart.getType() == MachineBlocks.Machines.DISPLAY && ((TileEntityDisplay) machinePart).progress != -1) {
			int progress = ((TileEntityDisplay) machinePart).progress;
			if (progress < 20) {
				Minecraft.getMinecraft().renderEngine.bindTexture(texture_display_0);
			}
			else if (progress < 40) {
				Minecraft.getMinecraft().renderEngine.bindTexture(texture_display_20);
			}
			else if (progress < 70) {
				Minecraft.getMinecraft().renderEngine.bindTexture(texture_display_40);
			}
			else if (progress < 90) {
				Minecraft.getMinecraft().renderEngine.bindTexture(texture_display_70);
			}
			else if (progress < 100) {
				Minecraft.getMinecraft().renderEngine.bindTexture(texture_display_90);
			}
			else
				Minecraft.getMinecraft().renderEngine.bindTexture(texture_display_100);
		}
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

		textures.put(MachineBlocks.Machines.CORE, new ResourceLocation(MineFluence.MODID, "textures/blocks/machineblocks/machineblock_core.png"));
		textures.put(MachineBlocks.Machines.DISPLAY, new ResourceLocation(MineFluence.MODID, "textures/blocks/machineblocks/machineblock_display.png"));
		textures.put(MachineBlocks.Machines.WORKER, new ResourceLocation(MineFluence.MODID, "textures/blocks/machineblocks/machineblock_worker.png"));
		textures.put(MachineBlocks.Machines.HYPERWORKER, new ResourceLocation(MineFluence.MODID, "textures/blocks/machineblocks/machineblock_hyperworker.png"));
		textures.put(MachineBlocks.Machines.INPUT, new ResourceLocation(MineFluence.MODID, "textures/blocks/machineblocks/machineblock_input.png"));
		textures.put(MachineBlocks.Machines.OUTPUT, new ResourceLocation(MineFluence.MODID, "textures/blocks/machineblocks/machineblock_output.png"));
		textures.put(MachineBlocks.Machines.FAN, new ResourceLocation(MineFluence.MODID, "textures/blocks/machineblocks/machineblock_fan.png"));
		textures.put(MachineBlocks.Machines.WATERCOOLING, new ResourceLocation(MineFluence.MODID, "textures/blocks/machineblocks/machineblock_watercooling.png"));
	}
}