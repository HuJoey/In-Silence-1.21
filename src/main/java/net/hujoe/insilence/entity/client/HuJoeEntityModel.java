package net.hujoe.insilence.entity.client;

import net.hujoe.insilence.Insilence;
import net.hujoe.insilence.entity.custom.HuJoeEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.entity.Entity;

// Made with Blockbench 4.11.2
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class HuJoeEntityModel extends EntityModel<HuJoeEntity> {
	public static final EntityModelLayer HUJOE = new EntityModelLayer(Identifier.of(Insilence.MOD_ID, "hujoe_statue"), "main");
	private final ModelPart HuJoe;
	private final ModelPart Head;
	private final ModelPart Body;
	private final ModelPart RightArm;
	private final ModelPart LeftArm;
	private final ModelPart RightLeg;
	private final ModelPart LeftLeg;
	private final ModelPart stick;
	private final ModelPart bone;
	public HuJoeEntityModel(ModelPart root) {
		this.HuJoe = root.getChild("HuJoe");
		this.Head = this.HuJoe.getChild("Head");
		this.Body = this.HuJoe.getChild("Body");
		this.RightArm = this.HuJoe.getChild("RightArm");
		this.LeftArm = this.HuJoe.getChild("LeftArm");
		this.RightLeg = this.HuJoe.getChild("RightLeg");
		this.LeftLeg = this.HuJoe.getChild("LeftLeg");
		this.stick = root.getChild("stick");
		this.bone = root.getChild("bone");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData HuJoe = modelPartData.addChild("HuJoe", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 16.0F, 0.0F));

		ModelPartData Head = HuJoe.addChild("Head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, 4.0F, -7.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F))
		.uv(0, 16).cuboid(-4.0F, 4.5F, -7.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.5F)), ModelTransform.pivot(0.0F, -16.0F, 0.0F));

		ModelPartData Body = HuJoe.addChild("Body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -16.0F, 0.0F));

		ModelPartData BodyLayer_r1 = Body.addChild("BodyLayer_r1", ModelPartBuilder.create().uv(32, 16).cuboid(-4.0F, -12.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.25F))
		.uv(32, 0).cuboid(-4.0F, -12.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 23.0F, -7.0F, -0.3927F, 0.0F, 0.0F));

		ModelPartData RightArm = HuJoe.addChild("RightArm", ModelPartBuilder.create(), ModelTransform.pivot(-5.0F, -14.0F, 0.0F));

		ModelPartData RightArmLayer_r1 = RightArm.addChild("RightArmLayer_r1", ModelPartBuilder.create().uv(30, 32).cuboid(-7.0F, -2.0F, -12.0F, 3.0F, 4.0F, 8.0F, new Dilation(0.25F))
		.uv(0, 32).cuboid(-7.0F, -2.0F, -12.0F, 3.0F, 4.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(5.0F, 11.0F, -2.0F, 0.3927F, 0.0F, 0.0F));

		ModelPartData LeftArm = HuJoe.addChild("LeftArm", ModelPartBuilder.create(), ModelTransform.pivot(5.0F, -14.0F, 0.0F));

		ModelPartData LeftArmLayer_r1 = LeftArm.addChild("LeftArmLayer_r1", ModelPartBuilder.create().uv(56, 16).cuboid(4.0F, 0.0F, -2.0F, 3.0F, 12.0F, 4.0F, new Dilation(0.25F))
		.uv(16, 48).cuboid(4.0F, 0.0F, -2.0F, 3.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-5.0F, 10.0F, -4.0F, 0.3927F, 0.0F, 0.0F));

		ModelPartData RightLeg = HuJoe.addChild("RightLeg", ModelPartBuilder.create(), ModelTransform.pivot(-1.9F, -4.0F, 0.0F));

		ModelPartData RightLegLayer_r1 = RightLeg.addChild("RightLegLayer_r1", ModelPartBuilder.create().uv(46, 44).cuboid(-3.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F))
		.uv(30, 44).cuboid(-3.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(1.9F, 11.0F, -7.0F, -0.7854F, 0.0F, 0.0F));

		ModelPartData LeftLeg = HuJoe.addChild("LeftLeg", ModelPartBuilder.create(), ModelTransform.pivot(1.9F, -4.0F, 0.0F));

		ModelPartData LeftLegLayer_r1 = LeftLeg.addChild("LeftLegLayer_r1", ModelPartBuilder.create().uv(56, 0).cuboid(-0.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F))
		.uv(0, 48).cuboid(-0.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-1.9F, 11.0F, -7.0F, -0.3927F, 0.0F, 0.0F));

		ModelPartData stick = modelPartData.addChild("stick", ModelPartBuilder.create().uv(112, 0).cuboid(-6.0F, -10.0F, -23.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(116, 0).cuboid(-6.0F, -10.0F, -24.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(108, 4).cuboid(-6.0F, -9.0F, -22.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(112, 4).cuboid(-6.0F, -9.0F, -23.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(116, 4).cuboid(-6.0F, -9.0F, -24.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(104, 8).cuboid(-6.0F, -8.0F, -21.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(108, 8).cuboid(-6.0F, -8.0F, -22.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(112, 8).cuboid(-6.0F, -8.0F, -23.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(100, 12).cuboid(-6.0F, -7.0F, -20.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(104, 12).cuboid(-6.0F, -7.0F, -21.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(108, 12).cuboid(-6.0F, -7.0F, -22.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(96, 16).cuboid(-6.0F, -6.0F, -19.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(100, 16).cuboid(-6.0F, -6.0F, -20.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(104, 16).cuboid(-6.0F, -6.0F, -21.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(92, 20).cuboid(-6.0F, -5.0F, -18.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(96, 20).cuboid(-6.0F, -5.0F, -19.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(100, 20).cuboid(-6.0F, -5.0F, -20.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(88, 24).cuboid(-6.0F, -4.0F, -17.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(92, 24).cuboid(-6.0F, -4.0F, -18.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(96, 24).cuboid(-6.0F, -4.0F, -19.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(84, 28).cuboid(-6.0F, -3.0F, -16.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(88, 28).cuboid(-6.0F, -3.0F, -17.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(92, 28).cuboid(-6.0F, -3.0F, -18.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(80, 32).cuboid(-6.0F, -2.0F, -15.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(84, 32).cuboid(-6.0F, -2.0F, -16.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(88, 32).cuboid(-6.0F, -2.0F, -17.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(76, 36).cuboid(-6.0F, -1.0F, -14.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(80, 36).cuboid(-6.0F, -1.0F, -15.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(84, 36).cuboid(-6.0F, -1.0F, -16.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(72, 40).cuboid(-6.0F, 0.0F, -13.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(76, 40).cuboid(-6.0F, 0.0F, -14.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(80, 40).cuboid(-6.0F, 0.0F, -15.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(68, 44).cuboid(-6.0F, 1.0F, -12.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(72, 44).cuboid(-6.0F, 1.0F, -13.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(76, 44).cuboid(-6.0F, 1.0F, -14.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(68, 48).cuboid(-6.0F, 2.0F, -12.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(72, 48).cuboid(-6.0F, 2.0F, -13.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 16.0F, 0.0F));

		ModelPartData bone = modelPartData.addChild("bone", ModelPartBuilder.create(), ModelTransform.pivot(8.0F, 24.0F, -8.0F));

		ModelPartData cube_r1 = bone.addChild("cube_r1", ModelPartBuilder.create().uv(85, 42).cuboid(-3.0F, -2.0F, -1.0F, 3.0F, 4.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-12.0F, -17.0F, -15.0F, -0.7854F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 128, 128);
	}

	@Override
	public void setAngles(HuJoeEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
		HuJoe.render(matrices, vertices, light, overlay, color);
		stick.render(matrices, vertices, light, overlay, color);
		bone.render(matrices, vertices, light, overlay, color);
	}

	public ModelPart getPart(){
		return HuJoe;
	}
}