package net.hujoe.insilence.entity.client;

import net.hujoe.insilence.Insilence;
import net.hujoe.insilence.entity.custom.SobrXShuppetEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

// Made with Blockbench 4.11.2
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class SobrXShuppetEntityModel extends EntityModel<SobrXShuppetEntity> {
	public static final EntityModelLayer SOBRXSHUPPET = new EntityModelLayer(Identifier.of(Insilence.MOD_ID, "sobr_shuppet_statue"), "main");
	private final ModelPart Sobrs;
	private final ModelPart Head;
	private final ModelPart Body;
	private final ModelPart RightArm;
	private final ModelPart LeftArm;
	private final ModelPart RightLeg;
	private final ModelPart LeftLeg;
	private final ModelPart Spoxs;
	private final ModelPart Head2;
	private final ModelPart Body2;
	private final ModelPart RightArm2;
	private final ModelPart LeftArm2;
	private final ModelPart RightLeg2;
	private final ModelPart LeftLeg2;
	private final ModelPart stick;
	private final ModelPart stick3;
	private final ModelPart stick2;
	private final ModelPart bb_main;
	public SobrXShuppetEntityModel(ModelPart root) {
		this.Sobrs = root.getChild("Sobrs");
		this.Head = this.Sobrs.getChild("Head");
		this.Body = this.Sobrs.getChild("Body");
		this.RightArm = this.Sobrs.getChild("RightArm");
		this.LeftArm = this.Sobrs.getChild("LeftArm");
		this.RightLeg = this.Sobrs.getChild("RightLeg");
		this.LeftLeg = this.Sobrs.getChild("LeftLeg");
		this.Spoxs = root.getChild("Spoxs");
		this.Head2 = this.Spoxs.getChild("Head2");
		this.Body2 = this.Spoxs.getChild("Body2");
		this.RightArm2 = this.Spoxs.getChild("RightArm2");
		this.LeftArm2 = this.Spoxs.getChild("LeftArm2");
		this.RightLeg2 = this.Spoxs.getChild("RightLeg2");
		this.LeftLeg2 = this.Spoxs.getChild("LeftLeg2");
		this.stick = root.getChild("stick");
		this.stick3 = root.getChild("stick3");
		this.stick2 = root.getChild("stick2");
		this.bb_main = root.getChild("bb_main");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData Sobrs = modelPartData.addChild("Sobrs", ModelPartBuilder.create(), ModelTransform.of(2.0F, 24.0F, 13.0F, 0.0F, -0.1745F, 0.0F));

		ModelPartData Head = Sobrs.addChild("Head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F))
				.uv(32, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.5F)), ModelTransform.of(0.0F, -24.0F, 0.0F, -0.1047F, 0.0873F, 0.0F));

		ModelPartData Body = Sobrs.addChild("Body", ModelPartBuilder.create().uv(16, 16).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F))
				.uv(16, 32).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(0.0F, -24.0F, 0.0F));

		ModelPartData RightArm = Sobrs.addChild("RightArm", ModelPartBuilder.create().uv(40, 16).cuboid(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new Dilation(0.0F))
				.uv(40, 32).cuboid(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.of(-5.0F, -22.0F, 0.0F, -3.098F, 0.0F, 0.0F));

		ModelPartData LeftArm = Sobrs.addChild("LeftArm", ModelPartBuilder.create().uv(32, 48).cuboid(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new Dilation(0.0F))
				.uv(48, 48).cuboid(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.of(5.0F, -22.0F, 0.0F, -3.098F, 0.0F, 0.0F));

		ModelPartData RightLeg = Sobrs.addChild("RightLeg", ModelPartBuilder.create().uv(16, 48).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
				.uv(0, 32).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.of(-1.9F, -12.0F, 0.0F, 0.192F, 0.0F, 0.0349F));

		ModelPartData LeftLeg = Sobrs.addChild("LeftLeg", ModelPartBuilder.create().uv(0, 16).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
				.uv(0, 48).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.of(1.9F, -12.0F, 0.0F, -0.5672F, 0.0F, -0.0349F));

		ModelPartData Spoxs = modelPartData.addChild("Spoxs", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, -12.0F));

		ModelPartData Head2 = Spoxs.addChild("Head2", ModelPartBuilder.create().uv(64, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F))
				.uv(96, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.5F)), ModelTransform.of(0.0F, -24.0F, 0.0F, -0.1923F, -0.9946F, 0.171F));

		ModelPartData Body2 = Spoxs.addChild("Body2", ModelPartBuilder.create().uv(80, 16).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F))
				.uv(80, 32).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(0.0F, -24.0F, 0.0F));

		ModelPartData RightArm2 = Spoxs.addChild("RightArm2", ModelPartBuilder.create().uv(104, 16).cuboid(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new Dilation(0.0F))
				.uv(104, 32).cuboid(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.of(-5.0F, -22.0F, 0.0F, -0.6981F, 0.0F, 0.0F));

		ModelPartData LeftArm2 = Spoxs.addChild("LeftArm2", ModelPartBuilder.create().uv(96, 48).cuboid(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new Dilation(0.0F))
				.uv(112, 48).cuboid(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.of(5.0F, -22.0F, 0.0F, -0.6981F, 0.0F, 0.0F));

		ModelPartData RightLeg2 = Spoxs.addChild("RightLeg2", ModelPartBuilder.create().uv(80, 48).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
				.uv(64, 32).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.of(-1.9F, -12.0F, 0.0F, -0.1571F, 0.0F, 0.0349F));

		ModelPartData LeftLeg2 = Spoxs.addChild("LeftLeg2", ModelPartBuilder.create().uv(64, 16).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
				.uv(64, 48).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.of(1.9F, -12.0F, 0.0F, 0.1745F, 0.0F, -0.0349F));

		ModelPartData stick = modelPartData.addChild("stick", ModelPartBuilder.create().uv(103, 64).cuboid(-6.0F, -10.0F, -23.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(107, 64).cuboid(-6.0F, -10.0F, -24.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(99, 68).cuboid(-6.0F, -9.0F, -22.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(103, 68).cuboid(-6.0F, -9.0F, -23.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(107, 68).cuboid(-6.0F, -9.0F, -24.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(95, 72).cuboid(-6.0F, -8.0F, -21.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(99, 72).cuboid(-6.0F, -8.0F, -22.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(103, 72).cuboid(-6.0F, -8.0F, -23.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(91, 76).cuboid(-6.0F, -7.0F, -20.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(95, 76).cuboid(-6.0F, -7.0F, -21.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(99, 76).cuboid(-6.0F, -7.0F, -22.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(87, 80).cuboid(-6.0F, -6.0F, -19.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(91, 80).cuboid(-6.0F, -6.0F, -20.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(95, 80).cuboid(-6.0F, -6.0F, -21.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(83, 84).cuboid(-6.0F, -5.0F, -18.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(87, 84).cuboid(-6.0F, -5.0F, -19.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(91, 84).cuboid(-6.0F, -5.0F, -20.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(79, 88).cuboid(-6.0F, -4.0F, -17.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(83, 88).cuboid(-6.0F, -4.0F, -18.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(87, 88).cuboid(-6.0F, -4.0F, -19.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(75, 92).cuboid(-6.0F, -3.0F, -16.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(79, 92).cuboid(-6.0F, -3.0F, -17.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(83, 92).cuboid(-6.0F, -3.0F, -18.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(71, 96).cuboid(-6.0F, -2.0F, -15.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(75, 96).cuboid(-6.0F, -2.0F, -16.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(79, 96).cuboid(-6.0F, -2.0F, -17.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(67, 100).cuboid(-6.0F, -1.0F, -14.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(71, 100).cuboid(-6.0F, -1.0F, -15.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(75, 100).cuboid(-6.0F, -1.0F, -16.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(63, 104).cuboid(-6.0F, 0.0F, -13.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(67, 104).cuboid(-6.0F, 0.0F, -14.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(71, 104).cuboid(-6.0F, 0.0F, -15.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(59, 108).cuboid(-6.0F, 1.0F, -12.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(63, 108).cuboid(-6.0F, 1.0F, -13.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(67, 108).cuboid(-6.0F, 1.0F, -14.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(59, 112).cuboid(-6.0F, 2.0F, -12.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(63, 112).cuboid(-6.0F, 2.0F, -13.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-14.0F, 14.0F, -6.0F, -0.6981F, 0.0F, 1.5708F));

		ModelPartData stick3 = modelPartData.addChild("stick3", ModelPartBuilder.create().uv(103, 64).cuboid(-6.0F, -10.0F, -23.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(107, 64).cuboid(-6.0F, -10.0F, -24.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(99, 68).cuboid(-6.0F, -9.0F, -22.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(103, 68).cuboid(-6.0F, -9.0F, -23.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(107, 68).cuboid(-6.0F, -9.0F, -24.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(95, 72).cuboid(-6.0F, -8.0F, -21.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(99, 72).cuboid(-6.0F, -8.0F, -22.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(103, 72).cuboid(-6.0F, -8.0F, -23.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(91, 76).cuboid(-6.0F, -7.0F, -20.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(95, 76).cuboid(-6.0F, -7.0F, -21.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(99, 76).cuboid(-6.0F, -7.0F, -22.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(87, 80).cuboid(-6.0F, -6.0F, -19.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(91, 80).cuboid(-6.0F, -6.0F, -20.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(95, 80).cuboid(-6.0F, -6.0F, -21.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(83, 84).cuboid(-6.0F, -5.0F, -18.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(87, 84).cuboid(-6.0F, -5.0F, -19.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(91, 84).cuboid(-6.0F, -5.0F, -20.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(79, 88).cuboid(-6.0F, -4.0F, -17.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(83, 88).cuboid(-6.0F, -4.0F, -18.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(87, 88).cuboid(-6.0F, -4.0F, -19.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(75, 92).cuboid(-6.0F, -3.0F, -16.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(79, 92).cuboid(-6.0F, -3.0F, -17.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(83, 92).cuboid(-6.0F, -3.0F, -18.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(71, 96).cuboid(-6.0F, -2.0F, -15.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(75, 96).cuboid(-6.0F, -2.0F, -16.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(79, 96).cuboid(-6.0F, -2.0F, -17.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(67, 100).cuboid(-6.0F, -1.0F, -14.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(71, 100).cuboid(-6.0F, -1.0F, -15.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(75, 100).cuboid(-6.0F, -1.0F, -16.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(63, 104).cuboid(-6.0F, 0.0F, -13.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(67, 104).cuboid(-6.0F, 0.0F, -14.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(71, 104).cuboid(-6.0F, 0.0F, -15.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(59, 108).cuboid(-6.0F, 1.0F, -12.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(63, 108).cuboid(-6.0F, 1.0F, -13.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(67, 108).cuboid(-6.0F, 1.0F, -14.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(59, 112).cuboid(-6.0F, 2.0F, -12.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(63, 112).cuboid(-6.0F, 2.0F, -13.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-17.0F, 7.0F, -12.0F, -0.9708F, 0.1283F, 1.9224F));

		ModelPartData stick2 = modelPartData.addChild("stick2", ModelPartBuilder.create().uv(103, 64).cuboid(-6.0F, -10.0F, -23.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(107, 64).cuboid(-6.0F, -10.0F, -24.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(99, 68).cuboid(-6.0F, -9.0F, -22.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(103, 68).cuboid(-6.0F, -9.0F, -23.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(107, 68).cuboid(-6.0F, -9.0F, -24.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(95, 72).cuboid(-6.0F, -8.0F, -21.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(99, 72).cuboid(-6.0F, -8.0F, -22.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(103, 72).cuboid(-6.0F, -8.0F, -23.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(91, 76).cuboid(-6.0F, -7.0F, -20.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(95, 76).cuboid(-6.0F, -7.0F, -21.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(99, 76).cuboid(-6.0F, -7.0F, -22.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(87, 80).cuboid(-6.0F, -6.0F, -19.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(91, 80).cuboid(-6.0F, -6.0F, -20.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(95, 80).cuboid(-6.0F, -6.0F, -21.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(83, 84).cuboid(-6.0F, -5.0F, -18.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(87, 84).cuboid(-6.0F, -5.0F, -19.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(91, 84).cuboid(-6.0F, -5.0F, -20.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(79, 88).cuboid(-6.0F, -4.0F, -17.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(83, 88).cuboid(-6.0F, -4.0F, -18.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(87, 88).cuboid(-6.0F, -4.0F, -19.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(75, 92).cuboid(-6.0F, -3.0F, -16.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(79, 92).cuboid(-6.0F, -3.0F, -17.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(83, 92).cuboid(-6.0F, -3.0F, -18.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(71, 96).cuboid(-6.0F, -2.0F, -15.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(75, 96).cuboid(-6.0F, -2.0F, -16.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(79, 96).cuboid(-6.0F, -2.0F, -17.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(67, 100).cuboid(-6.0F, -1.0F, -14.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(71, 100).cuboid(-6.0F, -1.0F, -15.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(75, 100).cuboid(-6.0F, -1.0F, -16.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(63, 104).cuboid(-6.0F, 0.0F, -13.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(67, 104).cuboid(-6.0F, 0.0F, -14.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(71, 104).cuboid(-6.0F, 0.0F, -15.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(59, 108).cuboid(-6.0F, 1.0F, -12.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(63, 108).cuboid(-6.0F, 1.0F, -13.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(67, 108).cuboid(-6.0F, 1.0F, -14.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(59, 112).cuboid(-6.0F, 2.0F, -12.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(63, 112).cuboid(-6.0F, 2.0F, -13.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-10.0F, 7.0F, -4.0F, -0.3997F, -0.5437F, 1.472F));

		ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(0, 64).cuboid(-6.0F, -48.0F, 4.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
		return TexturedModelData.of(modelData, 128, 128);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
		Sobrs.render(matrices, vertices, light, overlay, color);
		Spoxs.render(matrices, vertices, light, overlay, color);
		stick.render(matrices, vertices, light, overlay, color);
		stick2.render(matrices, vertices, light, overlay, color);
		stick3.render(matrices, vertices, light, overlay, color);
		bb_main.render(matrices, vertices, light, overlay, color);
	}

	@Override
	public void setAngles(SobrXShuppetEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

	}
}