package net.hujoe.insilence.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

// Made with Blockbench 4.11.2
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class RakeArmModel extends EntityModel<Entity> {
	private final ModelPart rake;
	private final ModelPart leftarm;
	private final ModelPart shoulder;
	public final ModelPart leftwrist;
	private final ModelPart claw;
	private final ModelPart arm;
	private final ModelPart rightarm;
	private final ModelPart shoulder2;
	public final ModelPart rightwrist;
	private final ModelPart arm2;
	private final ModelPart claw2;

	public RakeArmModel(ModelPart root) {
		this.rake = root.getChild("rake");
		this.leftarm = this.rake.getChild("leftarm");
		this.shoulder = this.leftarm.getChild("shoulder");
		this.leftwrist = this.leftarm.getChild("leftwrist");
		this.claw = this.leftwrist.getChild("claw");
		this.arm = this.leftwrist.getChild("arm");
		this.rightarm = this.rake.getChild("rightarm");
		this.shoulder2 = this.rightarm.getChild("shoulder2");
		this.rightwrist = this.rightarm.getChild("rightwrist");
		this.arm2 = this.rightwrist.getChild("arm2");
		this.claw2 = this.rightwrist.getChild("claw2");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData rake = modelPartData.addChild("rake", ModelPartBuilder.create(), ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		ModelPartData leftarm = rake.addChild("leftarm", ModelPartBuilder.create(), ModelTransform.pivot(-4.0F, -35.0F, -3.0F));

		ModelPartData shoulder = leftarm.addChild("shoulder", ModelPartBuilder.create(), ModelTransform.pivot(-2.0F, 23.0F, 7.0F));

		ModelPartData cube_r71 = shoulder.addChild("cube_r71", ModelPartBuilder.create().uv(40, 0).cuboid(-1.0F, -3.0F, -1.0F, 4.0F, 13.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -21.0F, -9.0F, -0.1705F, -0.0376F, 0.265F));

		ModelPartData leftwrist = leftarm.addChild("leftwrist", ModelPartBuilder.create(), ModelTransform.pivot(-3.0F, 10.0F, 0.0F));

		ModelPartData claw = leftwrist.addChild("claw", ModelPartBuilder.create(), ModelTransform.pivot(8.0F, 13.0F, 7.0F));

		ModelPartData cube_r72 = claw.addChild("cube_r72", ModelPartBuilder.create().uv(53, 0).cuboid(-2.0F, 0.0F, -1.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -2.0257F, -0.1586F, -0.3123F));

		ModelPartData cube_r73 = claw.addChild("cube_r73", ModelPartBuilder.create().uv(32, 69).cuboid(-2.0F, 0.0F, -1.0F, 2.0F, 2.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, -4.0F, 3.0F, -2.0316F, -0.1393F, -0.2727F));

		ModelPartData cube_r74 = claw.addChild("cube_r74", ModelPartBuilder.create().uv(62, 28).cuboid(-2.0F, -1.0F, -1.0F, 2.0F, 3.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(-4.0F, -9.0F, 3.0F, -1.5298F, 0.0149F, -0.3488F));

		ModelPartData cube_r75 = claw.addChild("cube_r75", ModelPartBuilder.create().uv(62, 47).cuboid(-2.0F, -2.0F, -1.0F, 2.0F, 4.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(-7.0F, -13.0F, 0.0F, -1.2582F, 0.3332F, -0.468F));

		ModelPartData arm = leftwrist.addChild("arm", ModelPartBuilder.create(), ModelTransform.pivot(1.0F, 13.0F, 7.0F));

		ModelPartData cube_r76 = arm.addChild("cube_r76", ModelPartBuilder.create().uv(62, 57).cuboid(-2.0F, -2.0F, -1.0F, 2.0F, 4.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -13.0F, -4.0F, 0.082F, 0.0298F, -0.3478F));

		ModelPartData cube_r77 = arm.addChild("cube_r77", ModelPartBuilder.create().uv(32, 59).cuboid(-3.0F, -2.0F, -1.0F, 3.0F, 3.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -13.0F, -9.0F, -0.0807F, -0.0334F, -0.3914F));

		ModelPartData rightarm = rake.addChild("rightarm", ModelPartBuilder.create(), ModelTransform.pivot(3.0F, -35.0F, -3.0F));

		ModelPartData shoulder2 = rightarm.addChild("shoulder2", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 2.0F, -2.0F));

		ModelPartData cube_r78 = shoulder2.addChild("cube_r78", ModelPartBuilder.create().uv(0, 37).cuboid(-1.0F, -3.0F, -1.0F, 4.0F, 13.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.0879F, -0.151F, -0.5606F));

		ModelPartData rightwrist = rightarm.addChild("rightwrist", ModelPartBuilder.create(), ModelTransform.pivot(4.0F, 10.0F, 2.0F));

		ModelPartData arm2 = rightwrist.addChild("arm2", ModelPartBuilder.create(), ModelTransform.pivot(-7.0F, 14.0F, 5.0F));

		ModelPartData cube_r79 = arm2.addChild("cube_r79", ModelPartBuilder.create().uv(44, 62).cuboid(0.0F, -2.0F, -1.0F, 2.0F, 4.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(6.0F, -14.0F, 0.0F, 0.737F, -0.3931F, -0.1946F));

		ModelPartData cube_r80 = arm2.addChild("cube_r80", ModelPartBuilder.create().uv(58, 0).cuboid(0.0F, -2.0F, -1.0F, 3.0F, 3.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, -14.0F, -5.0F, -0.088F, -0.1304F, 0.0115F));

		ModelPartData claw2 = rightwrist.addChild("claw2", ModelPartBuilder.create(), ModelTransform.pivot(-6.0F, 5.0F, 15.0F));

		ModelPartData cube_r81 = claw2.addChild("cube_r81", ModelPartBuilder.create().uv(67, 18).cuboid(0.0F, 0.0F, -1.0F, 2.0F, 2.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -1.62F, -0.4795F, 0.0227F));

		ModelPartData cube_r82 = claw2.addChild("cube_r82", ModelPartBuilder.create().uv(62, 9).cuboid(0.0F, -1.0F, -1.0F, 2.0F, 3.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, -5.0F, -2.0F, -1.1476F, -0.1096F, 0.2382F));

		ModelPartData cube_r83 = claw2.addChild("cube_r83", ModelPartBuilder.create().uv(12, 62).cuboid(0.0F, -2.0F, -1.0F, 2.0F, 4.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, -8.0F, -6.0F, -0.6272F, -0.2136F, 0.1525F));

		ModelPartData cube_r84 = claw2.addChild("cube_r84", ModelPartBuilder.create().uv(48, 49).cuboid(1.0F, 0.0F, -1.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 5.0F, -1.0F, -1.9056F, -0.4149F, 0.1393F));

		return TexturedModelData.of(modelData, 128, 128);
	}
	@Override
	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
		rake.render(matrices, vertices, light, overlay);
	}
}