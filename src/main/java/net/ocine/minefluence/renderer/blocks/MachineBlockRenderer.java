package net.ocine.minefluence.renderer.blocks;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.ocine.minefluence.blocks.tileentities.IMachinePart;
import net.ocine.minefluence.models.ModelMachineBlockBlock;
import net.ocine.minefluence.models.ModelMachineBlockBorder;

public class MachineBlockRenderer implements ISimpleBlockRenderingHandler{

	public static final String NOT_IN_MACHINE = "border_standart.png";
	public static final String IN_MACHINE = "border_inmachine.png";
	public static final String ACTIVATED = "border_activated.png";
	
	private ModelMachineBlockBlock modelBase;
	private ModelMachineBlockBorder modelBorder;
	
	public MachineBlockRenderer() {
		modelBase = new ModelMachineBlockBlock();
		modelBorder = new ModelMachineBlockBorder();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float scale) {
		IMachinePart machinePart = (IMachinePart)tileEntity;
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		ResourceLocation locationBase = new ResourceLocation("minefluence:textures/blocks/machineblocks/" + machinePart.getTextureName());
		Minecraft.getMinecraft().renderEngine.bindTexture(locationBase);
		GL11.glPushMatrix();
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
        modelBase.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
        ResourceLocation locationBorder = new ResourceLocation("minefluence:textures/blocks/machineblocks/" + machinePart.getBorder());
		Minecraft.getMinecraft().renderEngine.bindTexture(locationBorder);
		GL11.glPushMatrix();
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
        modelBorder.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
	}

	@Override
	public int getRenderId() {
		return 0;
	}

	@Override
	public void renderInventoryBlock(Block block, int arg1, int arg2, RenderBlocks arg3) {
		
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess arg0, int arg1, int arg2,
			int arg3, Block arg4, int arg5, RenderBlocks arg6) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int arg0) {
		return false;
	}
	
}
