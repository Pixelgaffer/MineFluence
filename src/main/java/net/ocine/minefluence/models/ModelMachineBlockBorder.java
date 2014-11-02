package net.ocine.minefluence.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMachineBlockBorder extends ModelBase {
	
	private ModelRenderer shape1;
	private ModelRenderer shape2;
	private ModelRenderer shape3;
	private ModelRenderer shape4;
	private ModelRenderer shape5;
	private ModelRenderer shape6;
	private ModelRenderer shape7;
	private ModelRenderer shape8;
	private ModelRenderer shape9;
	private ModelRenderer shape10;
	private ModelRenderer shape11;
	private ModelRenderer shape12;

	public ModelMachineBlockBorder() {
		textureWidth = 128;
		textureHeight = 128;

		shape1 = new ModelRenderer(this, 0, 0);
		shape1.addBox(0F, 0F, 0F, 1, 1, 16);
		shape1.setRotationPoint(-8F, 8F, -8F);
		shape1.setTextureSize(64, 32);
		shape1.mirror = true;
		setRotation(shape1, 0F, 0F, 0F);
		shape2 = new ModelRenderer(this, 0, 0);
		shape2.addBox(0F, 0F, 0F, 1, 1, 16);
		shape2.setRotationPoint(7F, 8F, -8F);
		shape2.setTextureSize(64, 32);
		shape2.mirror = true;
		setRotation(shape2, 0F, 0F, 0F);
		shape3 = new ModelRenderer(this, 0, 0);
		shape3.addBox(0F, 0F, 0F, 1, 1, 16);
		shape3.setRotationPoint(7F, 23F, -8F);
		shape3.setTextureSize(64, 32);
		shape3.mirror = true;
		setRotation(shape3, 0F, 0F, 0F);
		shape4 = new ModelRenderer(this, 0, 0);
		shape4.addBox(0F, 0F, 0F, 1, 1, 16);
		shape4.setRotationPoint(-8F, 23F, -8F);
		shape4.setTextureSize(64, 32);
		shape4.mirror = true;
		setRotation(shape4, 0F, 0F, 0F);
		shape5 = new ModelRenderer(this, 0, 19);
		shape5.addBox(0F, 0F, 0F, 14, 1, 1);
		shape5.setRotationPoint(-7F, 23F, -8F);
		shape5.setTextureSize(64, 32);
		shape5.mirror = true;
		setRotation(shape5, 0F, 0F, 0F);
		shape6 = new ModelRenderer(this, 0, 19);
		shape6.addBox(0F, 0F, 0F, 14, 1, 1);
		shape6.setRotationPoint(-7F, 8F, 7F);
		shape6.setTextureSize(64, 32);
		shape6.mirror = true;
		setRotation(shape6, 0F, 0F, 0F);
		shape7 = new ModelRenderer(this, 0, 19);
		shape7.addBox(0F, 0F, 0F, 14, 1, 1);
		shape7.setRotationPoint(-7F, 23F, 7F);
		shape7.setTextureSize(64, 32);
		shape7.mirror = true;
		setRotation(shape7, 0F, 0F, 0F);
		shape8 = new ModelRenderer(this, 0, 19);
		shape8.addBox(0F, 0F, 0F, 14, 1, 1);
		shape8.setRotationPoint(-7F, 8F, -8F);
		shape8.setTextureSize(64, 32);
		shape8.mirror = true;
		setRotation(shape8, 0F, 0F, 0F);
		shape9 = new ModelRenderer(this, 0, 22);
		shape9.addBox(0F, 0F, 0F, 1, 14, 1);
		shape9.setRotationPoint(7F, 9F, -8F);
		shape9.setTextureSize(64, 32);
		shape9.mirror = true;
		setRotation(shape9, 0F, 0F, 0F);
		shape10 = new ModelRenderer(this, 0, 22);
		shape10.addBox(0F, 0F, 0F, 1, 14, 1);
		shape10.setRotationPoint(-8F, 9F, -8F);
		shape10.setTextureSize(64, 32);
		shape10.mirror = true;
		setRotation(shape10, 0F, 0F, 0F);
		shape11 = new ModelRenderer(this, 0, 22);
		shape11.addBox(0F, 0F, 0F, 1, 14, 1);
		shape11.setRotationPoint(-8F, 9F, 7F);
		shape11.setTextureSize(64, 32);
		shape11.mirror = true;
		setRotation(shape11, 0F, 0F, 0F);
		shape12 = new ModelRenderer(this, 0, 21);
		shape12.addBox(0F, 0F, 0F, 1, 14, 1);
		shape12.setRotationPoint(7F, 9F, 7F);
		shape12.setTextureSize(64, 32);
		shape12.mirror = true;
		setRotation(shape12, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		shape1.render(f5);
		shape2.render(f5);
		shape3.render(f5);
		shape4.render(f5);
		shape5.render(f5);
		shape6.render(f5);
		shape7.render(f5);
		shape8.render(f5);
		shape9.render(f5);
		shape10.render(f5);
		shape11.render(f5);
		shape12.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3,
			float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

}
