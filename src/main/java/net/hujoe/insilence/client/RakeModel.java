package net.hujoe.insilence.client;

import net.hujoe.insilence.entity.custom.RakeEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.joml.Quaternionf;

import static net.minecraft.data.DataProvider.LOGGER;

// Made with Blockbench 4.11.1
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class RakeModel<T extends LivingEntity> extends EntityModel<T> {
	private final ModelPart rake;
	private final ModelPart leftleg;
	private final ModelPart rightleg;
	private final ModelPart torso;
	private final ModelPart tongue;
	private final ModelPart teeth;
	private final ModelPart teeth5;
	private final ModelPart teeth4;
	private final ModelPart teeth2;
	private final ModelPart teeth3;
	private final ModelPart hair;
	private final ModelPart leftarm;
	private final ModelPart rightarm;
	private final ModelPart tail;
	private final ModelPart head;
	private final ModelPart teeth6;
	public float yaw;
	public RakeModel(ModelPart root) {
		this.rake = root.getChild("rake");
		this.leftleg = this.rake.getChild("leftleg");
		this.rightleg = this.rake.getChild("rightleg");
		this.torso = this.rake.getChild("torso");
		this.tongue = this.torso.getChild("tongue");
		this.teeth = this.torso.getChild("teeth");
		this.teeth5 = this.torso.getChild("teeth5");
		this.teeth4 = this.torso.getChild("teeth4");
		this.teeth2 = this.torso.getChild("teeth2");
		this.teeth3 = this.torso.getChild("teeth3");
		this.hair = this.torso.getChild("hair");
		this.leftarm = this.rake.getChild("leftarm");
		this.rightarm = this.rake.getChild("rightarm");
		this.tail = this.rake.getChild("tail");
		this.head = this.rake.getChild("head");
		this.teeth6 = this.head.getChild("teeth6");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData rake = modelPartData.addChild("rake", ModelPartBuilder.create(), ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		ModelPartData leftleg = rake.addChild("leftleg", ModelPartBuilder.create().uv(38, 25).cuboid(-8.0F, 15.0F, 7.0F, 2.0F, 3.0F, 1.0F, new Dilation(0.0F))
		.uv(13, 37).cuboid(-8.0F, 15.0F, 5.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(72, 26).cuboid(-8.0F, 15.0F, 1.0F, 2.0F, 3.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -18.0F, -2.0F));

		ModelPartData cube_r1 = leftleg.addChild("cube_r1", ModelPartBuilder.create().uv(21, 28).cuboid(-3.0F, -3.0F, -4.0F, 4.0F, 6.0F, 9.0F, new Dilation(0.0F)), ModelTransform.of(-4.0F, 0.0F, 0.0F, -0.5444F, -0.2635F, 0.1564F));

		ModelPartData cube_r2 = leftleg.addChild("cube_r2", ModelPartBuilder.create().uv(34, 49).cuboid(-3.0F, -3.0F, -1.0F, 4.0F, 4.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(-5.0F, 1.0F, 3.0F, -1.789F, 0.0F, 0.0F));

		ModelPartData cube_r3 = leftleg.addChild("cube_r3", ModelPartBuilder.create().uv(51, 37).cuboid(-2.0F, -3.0F, -2.0F, 3.0F, 3.0F, 7.0F, new Dilation(0.0F)), ModelTransform.of(-5.0F, 5.0F, 1.0F, -2.3799F, -0.2143F, 0.2194F));

		ModelPartData cube_r4 = leftleg.addChild("cube_r4", ModelPartBuilder.create().uv(22, 66).cuboid(-2.0F, -3.0F, -1.0F, 2.0F, 3.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(-5.0F, 10.0F, -2.0F, -1.3917F, -0.2595F, -0.035F));

		ModelPartData cube_r5 = leftleg.addChild("cube_r5", ModelPartBuilder.create().uv(54, 67).cuboid(-2.0F, -3.0F, -1.0F, 2.0F, 3.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(-6.0F, 15.0F, -1.0F, -0.6545F, 0.0F, 0.0F));

		ModelPartData rightleg = rake.addChild("rightleg", ModelPartBuilder.create().uv(0, 19).cuboid(4.0F, 13.0F, -3.0F, 2.0F, 3.0F, 1.0F, new Dilation(0.0F))
		.uv(33, 1).cuboid(4.0F, 13.0F, -5.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(12, 72).cuboid(4.0F, 13.0F, -9.0F, 2.0F, 3.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -16.0F, -2.0F));

		ModelPartData cube_r6 = rightleg.addChild("cube_r6", ModelPartBuilder.create().uv(23, 10).cuboid(0.0F, -3.0F, -4.0F, 4.0F, 6.0F, 9.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 0.0F, -2.0F, -1.0309F, 0.3007F, -0.18F));

		ModelPartData cube_r7 = rightleg.addChild("cube_r7", ModelPartBuilder.create().uv(0, 55).cuboid(-1.0F, -3.0F, -1.0F, 3.0F, 4.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(4.0F, 6.0F, -1.0F, -2.9644F, -0.1719F, -0.0306F));

		ModelPartData cube_r8 = rightleg.addChild("cube_r8", ModelPartBuilder.create().uv(48, 53).cuboid(-2.0F, -3.0F, -1.0F, 4.0F, 3.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(4.0F, 3.0F, -1.0F, -1.9171F, -0.0447F, -0.1231F));

		ModelPartData cube_r9 = rightleg.addChild("cube_r9", ModelPartBuilder.create().uv(0, 66).cuboid(0.0F, -3.0F, -1.0F, 2.0F, 3.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(5.0F, 7.0F, -7.0F, -2.1009F, -0.0096F, 0.1569F));

		ModelPartData cube_r10 = rightleg.addChild("cube_r10", ModelPartBuilder.create().uv(70, 67).cuboid(0.0F, -3.0F, -1.0F, 2.0F, 3.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(4.0F, 12.0F, -9.0F, -1.2654F, 0.0F, 0.0F));

		ModelPartData torso = rake.addChild("torso", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -27.0F, -5.0F));

		ModelPartData cube_r11 = torso.addChild("cube_r11", ModelPartBuilder.create().uv(0, 19).cuboid(-5.0F, -10.0F, -4.0F, 8.0F, 11.0F, 7.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.4784F, 0.0403F, 0.0774F));

		ModelPartData cube_r12 = torso.addChild("cube_r12", ModelPartBuilder.create().uv(0, 0).cuboid(-5.0F, -10.0F, -4.0F, 8.0F, 12.0F, 7.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 9.0F, 0.0F, 0.0F, 0.0F, -0.0436F));

		ModelPartData tongue = torso.addChild("tongue", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r13 = tongue.addChild("cube_r13", ModelPartBuilder.create().uv(56, 16).cuboid(-2.0F, -8.0F, 3.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.4784F, 0.0403F, 0.0774F));

		ModelPartData cube_r14 = tongue.addChild("cube_r14", ModelPartBuilder.create().uv(49, 19).cuboid(-2.0F, -8.0F, -5.0F, 2.0F, 1.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 3.0F, 3.0F, -1.0456F, 0.0403F, 0.0774F));

		ModelPartData teeth = torso.addChild("teeth", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r15 = teeth.addChild("cube_r15", ModelPartBuilder.create().uv(0, 0).cuboid(1.0F, -2.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 2.0F, 3.0F, -0.48F, 0.0F, 0.0F));

		ModelPartData cube_r16 = teeth.addChild("cube_r16", ModelPartBuilder.create().uv(0, 0).cuboid(1.0F, -2.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -6.0F, 7.0F, -0.48F, 0.0F, 0.0F));

		ModelPartData cube_r17 = teeth.addChild("cube_r17", ModelPartBuilder.create().uv(0, 0).cuboid(1.0F, -2.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -4.0F, 5.0F, -0.48F, 0.0F, 0.0F));

		ModelPartData cube_r18 = teeth.addChild("cube_r18", ModelPartBuilder.create().uv(0, 0).cuboid(1.0F, -2.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -1.0F, 5.0F, -0.48F, 0.0F, 0.0F));

		ModelPartData cube_r19 = teeth.addChild("cube_r19", ModelPartBuilder.create().uv(0, 0).cuboid(1.0F, -2.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 1.0F, 5.0F, -0.48F, 0.0F, 0.0F));

		ModelPartData cube_r20 = teeth.addChild("cube_r20", ModelPartBuilder.create().uv(0, 0).cuboid(1.0F, -2.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -6.0F, 8.0F, -0.48F, 0.0F, 0.0F));

		ModelPartData cube_r21 = teeth.addChild("cube_r21", ModelPartBuilder.create().uv(0, 0).cuboid(1.0F, -2.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -4.0F, 7.0F, -0.48F, 0.0F, 0.0F));

		ModelPartData cube_r22 = teeth.addChild("cube_r22", ModelPartBuilder.create().uv(0, 0).cuboid(1.0F, -2.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -2.0F, 6.0F, -0.48F, 0.0F, 0.0F));

		ModelPartData teeth5 = torso.addChild("teeth5", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r23 = teeth5.addChild("cube_r23", ModelPartBuilder.create().uv(0, 0).cuboid(1.0F, -2.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(3.0F, -2.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(5.0F, -2.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-4.0F, 5.0F, 3.0F, 0.0436F, 0.0F, 0.0F));

		ModelPartData cube_r24 = teeth5.addChild("cube_r24", ModelPartBuilder.create().uv(0, 0).cuboid(1.0F, -2.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(3.0F, -2.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(5.0F, -2.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-4.0F, 3.0F, 5.0F, 0.0436F, 0.0F, 0.0F));

		ModelPartData cube_r25 = teeth5.addChild("cube_r25", ModelPartBuilder.create().uv(0, 0).cuboid(1.0F, -2.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(3.0F, -2.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(5.0F, -2.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-4.0F, 6.0F, 4.0F, 0.0436F, 0.0F, 0.0F));

		ModelPartData cube_r26 = teeth5.addChild("cube_r26", ModelPartBuilder.create().uv(0, 0).cuboid(1.0F, -2.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(3.0F, -2.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(5.0F, -2.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(7.0F, -2.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-5.0F, 4.0F, 4.0F, 0.0436F, 0.0F, 0.0F));

		ModelPartData cube_r27 = teeth5.addChild("cube_r27", ModelPartBuilder.create().uv(0, 0).cuboid(1.0F, -2.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(3.0F, -2.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(7.0F, -2.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-5.0F, 6.0F, 5.0F, 0.0436F, 0.0F, 0.0F));

		ModelPartData cube_r28 = teeth5.addChild("cube_r28", ModelPartBuilder.create().uv(0, 0).cuboid(1.0F, -2.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, 8.0F, 4.0F, 0.0436F, 0.0F, 0.0F));

		ModelPartData cube_r29 = teeth5.addChild("cube_r29", ModelPartBuilder.create().uv(0, 0).cuboid(1.0F, -2.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 7.0F, 5.0F, 0.0436F, 0.0F, 0.0F));

		ModelPartData teeth4 = torso.addChild("teeth4", ModelPartBuilder.create(), ModelTransform.pivot(-5.0F, 0.0F, 0.0F));

		ModelPartData cube_r30 = teeth4.addChild("cube_r30", ModelPartBuilder.create().uv(0, 0).cuboid(1.0F, -2.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 2.0F, 3.0F, -0.48F, 0.0F, 0.0F));

		ModelPartData cube_r31 = teeth4.addChild("cube_r31", ModelPartBuilder.create().uv(0, 0).cuboid(1.0F, -2.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -6.0F, 7.0F, -0.48F, 0.0F, 0.0F));

		ModelPartData cube_r32 = teeth4.addChild("cube_r32", ModelPartBuilder.create().uv(0, 0).cuboid(1.0F, -2.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -4.0F, 5.0F, -0.48F, 0.0F, 0.0F));

		ModelPartData cube_r33 = teeth4.addChild("cube_r33", ModelPartBuilder.create().uv(0, 0).cuboid(1.0F, -2.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -1.0F, 5.0F, -0.48F, 0.0F, 0.0F));

		ModelPartData cube_r34 = teeth4.addChild("cube_r34", ModelPartBuilder.create().uv(0, 0).cuboid(1.0F, -2.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 1.0F, 5.0F, -0.48F, 0.0F, 0.0F));

		ModelPartData cube_r35 = teeth4.addChild("cube_r35", ModelPartBuilder.create().uv(0, 0).cuboid(1.0F, -2.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -6.0F, 8.0F, -0.48F, 0.0F, 0.0F));

		ModelPartData cube_r36 = teeth4.addChild("cube_r36", ModelPartBuilder.create().uv(0, 0).cuboid(1.0F, -2.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -4.0F, 7.0F, -0.48F, 0.0F, 0.0F));

		ModelPartData cube_r37 = teeth4.addChild("cube_r37", ModelPartBuilder.create().uv(0, 0).cuboid(1.0F, -2.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -2.0F, 6.0F, -0.48F, 0.0F, 0.0F));

		ModelPartData teeth2 = torso.addChild("teeth2", ModelPartBuilder.create(), ModelTransform.pivot(-1.0F, 0.0F, 0.0F));

		ModelPartData cube_r38 = teeth2.addChild("cube_r38", ModelPartBuilder.create().uv(0, 0).cuboid(1.0F, -2.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 1.0F, 4.0F, -0.48F, 0.0F, 0.0F));

		ModelPartData cube_r39 = teeth2.addChild("cube_r39", ModelPartBuilder.create().uv(0, 0).cuboid(1.0F, -2.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 2.0F, 3.0F, -0.48F, 0.0F, 0.0F));

		ModelPartData cube_r40 = teeth2.addChild("cube_r40", ModelPartBuilder.create().uv(0, 0).cuboid(1.0F, -2.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -3.0F, 6.0F, -0.48F, 0.0F, 0.0F));

		ModelPartData cube_r41 = teeth2.addChild("cube_r41", ModelPartBuilder.create().uv(0, 0).cuboid(1.0F, -2.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -2.0F, 5.0F, -0.48F, 0.0F, 0.0F));

		ModelPartData cube_r42 = teeth2.addChild("cube_r42", ModelPartBuilder.create().uv(0, 0).cuboid(1.0F, -2.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 0.0F, 5.0F, -0.48F, 0.0F, 0.0F));

		ModelPartData cube_r43 = teeth2.addChild("cube_r43", ModelPartBuilder.create().uv(0, 0).cuboid(1.0F, -2.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -5.0F, 7.0F, -0.48F, 0.0F, 0.0F));

		ModelPartData cube_r44 = teeth2.addChild("cube_r44", ModelPartBuilder.create().uv(0, 0).cuboid(1.0F, -2.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -4.0F, 6.0F, -0.48F, 0.0F, 0.0F));

		ModelPartData teeth3 = torso.addChild("teeth3", ModelPartBuilder.create(), ModelTransform.pivot(-4.0F, 0.0F, 0.0F));

		ModelPartData cube_r45 = teeth3.addChild("cube_r45", ModelPartBuilder.create().uv(0, 0).cuboid(1.0F, -2.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 1.0F, 4.0F, -0.48F, 0.0F, 0.0F));

		ModelPartData cube_r46 = teeth3.addChild("cube_r46", ModelPartBuilder.create().uv(0, 0).cuboid(1.0F, -2.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 2.0F, 3.0F, -0.48F, 0.0F, 0.0F));

		ModelPartData cube_r47 = teeth3.addChild("cube_r47", ModelPartBuilder.create().uv(0, 0).cuboid(1.0F, -2.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -3.0F, 6.0F, -0.48F, 0.0F, 0.0F));

		ModelPartData cube_r48 = teeth3.addChild("cube_r48", ModelPartBuilder.create().uv(0, 0).cuboid(1.0F, -2.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -2.0F, 5.0F, -0.48F, 0.0F, 0.0F));

		ModelPartData cube_r49 = teeth3.addChild("cube_r49", ModelPartBuilder.create().uv(0, 0).cuboid(1.0F, -2.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 0.0F, 5.0F, -0.48F, 0.0F, 0.0F));

		ModelPartData cube_r50 = teeth3.addChild("cube_r50", ModelPartBuilder.create().uv(0, 0).cuboid(1.0F, -2.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -5.0F, 7.0F, -0.48F, 0.0F, 0.0F));

		ModelPartData cube_r51 = teeth3.addChild("cube_r51", ModelPartBuilder.create().uv(0, 0).cuboid(1.0F, -2.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -4.0F, 6.0F, -0.48F, 0.0F, 0.0F));

		ModelPartData hair = torso.addChild("hair", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r52 = hair.addChild("cube_r52", ModelPartBuilder.create().uv(27, 7).cuboid(-3.0F, 0.0F, -2.0F, 4.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, -2.0F, -3.0F, 0.2618F, 0.0F, 0.0436F));

		ModelPartData cube_r53 = hair.addChild("cube_r53", ModelPartBuilder.create().uv(27, 7).cuboid(-3.0F, 0.0F, -2.0F, 4.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, -2.0F, -4.0F, 0.3491F, 0.0F, 0.0436F));

		ModelPartData cube_r54 = hair.addChild("cube_r54", ModelPartBuilder.create().uv(27, 7).cuboid(-3.0F, 0.0F, -2.0F, 4.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -3.0F, -3.0F, 0.1745F, 0.0F, 0.0436F));

		ModelPartData cube_r55 = hair.addChild("cube_r55", ModelPartBuilder.create().uv(27, 7).cuboid(-3.0F, 0.0F, -2.0F, 4.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, -4.0F, -3.0F, 0.48F, 0.0F, 0.0436F));

		ModelPartData cube_r56 = hair.addChild("cube_r56", ModelPartBuilder.create().uv(27, 7).cuboid(-3.0F, 0.0F, -2.0F, 4.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, -5.0F, -2.0F, 0.829F, 0.0F, 0.0436F));

		ModelPartData cube_r57 = hair.addChild("cube_r57", ModelPartBuilder.create().uv(27, 7).cuboid(-3.0F, 0.0F, -2.0F, 4.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -7.0F, -1.0F, 0.5672F, 0.0F, 0.0436F));

		ModelPartData cube_r58 = hair.addChild("cube_r58", ModelPartBuilder.create().uv(27, 7).cuboid(-3.0F, 0.0F, -2.0F, 4.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, -6.0F, -2.0F, 0.2618F, 0.0F, 0.0436F));

		ModelPartData cube_r59 = hair.addChild("cube_r59", ModelPartBuilder.create().uv(27, 7).cuboid(-3.0F, 0.0F, -2.0F, 4.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, -9.0F, 0.0F, 0.1309F, 0.0F, 0.0436F));

		ModelPartData cube_r60 = hair.addChild("cube_r60", ModelPartBuilder.create().uv(27, 7).cuboid(-3.0F, 0.0F, -2.0F, 4.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 1.0F, -5.0F, 0.3054F, 0.0F, 0.0F));

		ModelPartData cube_r61 = hair.addChild("cube_r61", ModelPartBuilder.create().uv(27, 7).cuboid(-3.0F, 0.0F, -2.0F, 4.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 4.0F, -5.0F, 0.1745F, 0.0F, 0.0F));

		ModelPartData cube_r62 = hair.addChild("cube_r62", ModelPartBuilder.create().uv(27, 7).cuboid(-3.0F, 0.0F, -2.0F, 4.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, 3.0F, -4.0F, 0.48F, 0.0F, 0.0F));

		ModelPartData cube_r63 = hair.addChild("cube_r63", ModelPartBuilder.create().uv(27, 7).cuboid(-3.0F, 0.0F, -2.0F, 4.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 6.0F, -5.0F, 0.1309F, 0.0F, 0.0F));

		ModelPartData cube_r64 = hair.addChild("cube_r64", ModelPartBuilder.create().uv(27, 7).cuboid(-3.0F, 0.0F, -2.0F, 4.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, 7.0F, -4.0F, 0.6981F, 0.0F, 0.0F));

		ModelPartData cube_r65 = hair.addChild("cube_r65", ModelPartBuilder.create().uv(27, 7).cuboid(-3.0F, 0.0F, -2.0F, 4.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 9.0F, -5.0F, 0.3054F, 0.0F, 0.0F));

		ModelPartData cube_r66 = hair.addChild("cube_r66", ModelPartBuilder.create().uv(27, 7).cuboid(-3.0F, 0.0F, -2.0F, 4.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 8.0F, -5.0F, 0.1309F, 0.0F, 0.0F));

		ModelPartData cube_r67 = hair.addChild("cube_r67", ModelPartBuilder.create().uv(27, 7).cuboid(-3.0F, 0.0F, -2.0F, 4.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 5.0F, -4.0F, 0.5672F, 0.0F, 0.0F));

		ModelPartData cube_r68 = hair.addChild("cube_r68", ModelPartBuilder.create().uv(27, 7).cuboid(-3.0F, 0.0F, -2.0F, 4.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 2.0F, -4.0F, 0.6545F, 0.0F, 0.0F));

		ModelPartData cube_r69 = hair.addChild("cube_r69", ModelPartBuilder.create().uv(27, 7).cuboid(-3.0F, 0.0F, -2.0F, 4.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 0.0F, -5.0F, -0.0436F, 0.0F, 0.0F));

		ModelPartData cube_r70 = hair.addChild("cube_r70", ModelPartBuilder.create().uv(27, 7).cuboid(-3.0F, 0.0F, -2.0F, 4.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, -8.0F, -1.0F, 0.5236F, 0.0F, 0.0436F));

		ModelPartData leftarm = rake.addChild("leftarm", ModelPartBuilder.create(), ModelTransform.pivot(-6.0F, -12.0F, 4.0F));

		ModelPartData cube_r71 = leftarm.addChild("cube_r71", ModelPartBuilder.create().uv(40, 0).cuboid(-1.0F, -3.0F, -1.0F, 4.0F, 13.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -21.0F, -9.0F, -0.1705F, -0.0376F, 0.265F));

		ModelPartData cube_r72 = leftarm.addChild("cube_r72", ModelPartBuilder.create().uv(53, 0).cuboid(-2.0F, 0.0F, -1.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, 0.0F, 0.0F, -2.0257F, -0.1586F, -0.3123F));

		ModelPartData cube_r73 = leftarm.addChild("cube_r73", ModelPartBuilder.create().uv(32, 69).cuboid(-2.0F, 0.0F, -1.0F, 2.0F, 2.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(5.0F, -4.0F, 3.0F, -2.0316F, -0.1393F, -0.2727F));

		ModelPartData cube_r74 = leftarm.addChild("cube_r74", ModelPartBuilder.create().uv(62, 28).cuboid(-2.0F, -1.0F, -1.0F, 2.0F, 3.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, -9.0F, 3.0F, -1.5298F, 0.0149F, -0.3488F));

		ModelPartData cube_r75 = leftarm.addChild("cube_r75", ModelPartBuilder.create().uv(62, 47).cuboid(-2.0F, -2.0F, -1.0F, 2.0F, 4.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -13.0F, 0.0F, -1.2582F, 0.3332F, -0.468F));

		ModelPartData cube_r76 = leftarm.addChild("cube_r76", ModelPartBuilder.create().uv(62, 57).cuboid(-2.0F, -2.0F, -1.0F, 2.0F, 4.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -13.0F, -4.0F, 0.082F, 0.0298F, -0.3478F));

		ModelPartData cube_r77 = leftarm.addChild("cube_r77", ModelPartBuilder.create().uv(32, 59).cuboid(-3.0F, -2.0F, -1.0F, 3.0F, 3.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -13.0F, -9.0F, -0.0807F, -0.0334F, -0.3914F));

		ModelPartData rightarm = rake.addChild("rightarm", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -11.0F, 4.0F));

		ModelPartData cube_r78 = rightarm.addChild("cube_r78", ModelPartBuilder.create().uv(0, 37).cuboid(-1.0F, -3.0F, -1.0F, 4.0F, 13.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, -22.0F, -9.0F, -0.0879F, -0.151F, -0.5606F));

		ModelPartData cube_r79 = rightarm.addChild("cube_r79", ModelPartBuilder.create().uv(48, 49).cuboid(1.0F, 0.0F, -1.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -4.0F, 9.0F, -1.9056F, -0.4149F, 0.1393F));

		ModelPartData cube_r80 = rightarm.addChild("cube_r80", ModelPartBuilder.create().uv(67, 18).cuboid(0.0F, 0.0F, -1.0F, 2.0F, 2.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -9.0F, 10.0F, -1.62F, -0.4795F, 0.0227F));

		ModelPartData cube_r81 = rightarm.addChild("cube_r81", ModelPartBuilder.create().uv(62, 9).cuboid(0.0F, -1.0F, -1.0F, 2.0F, 3.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, -14.0F, 8.0F, -1.1476F, -0.1096F, 0.2382F));

		ModelPartData cube_r82 = rightarm.addChild("cube_r82", ModelPartBuilder.create().uv(12, 62).cuboid(0.0F, -2.0F, -1.0F, 2.0F, 4.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(4.0F, -17.0F, 4.0F, -0.6272F, -0.2136F, 0.1525F));

		ModelPartData cube_r83 = rightarm.addChild("cube_r83", ModelPartBuilder.create().uv(44, 62).cuboid(0.0F, -2.0F, -1.0F, 2.0F, 4.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(6.0F, -14.0F, 0.0F, 0.737F, -0.3931F, -0.1946F));

		ModelPartData cube_r84 = rightarm.addChild("cube_r84", ModelPartBuilder.create().uv(58, 0).cuboid(0.0F, -2.0F, -1.0F, 3.0F, 3.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, -14.0F, -5.0F, -0.088F, -0.1304F, 0.0115F));

		ModelPartData tail = rake.addChild("tail", ModelPartBuilder.create(), ModelTransform.pivot(-1.0F, -31.0F, -22.0F));

		ModelPartData cube_r85 = tail.addChild("cube_r85", ModelPartBuilder.create().uv(35, 43).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -2.4871F, 0.0F, 0.0F));

		ModelPartData cube_r86 = tail.addChild("cube_r86", ModelPartBuilder.create().uv(23, 0).cuboid(-1.0F, -2.0F, -3.0F, 2.0F, 2.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 4.0F, -3.0F, -2.138F, 0.0F, 0.0F));

		ModelPartData cube_r87 = tail.addChild("cube_r87", ModelPartBuilder.create().uv(64, 37).cuboid(-1.0F, -2.0F, -3.0F, 2.0F, 2.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 9.0F, -4.0F, -1.3526F, 0.0F, 0.0F));

		ModelPartData cube_r88 = tail.addChild("cube_r88", ModelPartBuilder.create().uv(72, 4).cuboid(-1.0F, -2.0F, -3.0F, 2.0F, 2.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 13.0F, -1.0F, -0.829F, 0.0F, 0.0F));

		ModelPartData cube_r89 = tail.addChild("cube_r89", ModelPartBuilder.create().uv(56, 19).cuboid(-1.0F, -2.0F, -3.0F, 2.0F, 2.0F, 7.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 15.0F, 3.0F, -0.2618F, 0.0F, 0.0F));

		ModelPartData cube_r90 = tail.addChild("cube_r90", ModelPartBuilder.create().uv(37, 37).cuboid(-1.0F, -2.0F, -6.0F, 2.0F, 2.0F, 10.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 14.0F, 12.0F, 0.3927F, 0.0F, 0.0F));

		ModelPartData head = rake.addChild("head", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -38.0F, 4.0F));

		ModelPartData cube_r91 = head.addChild("cube_r91", ModelPartBuilder.create().uv(84, 24).cuboid(-3.0F, 2.0F, -2.0F, 6.0F, 5.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -1.0F, 0.0F, 1.0036F, 0.0F, 0.0F));

		ModelPartData cube_r92 = head.addChild("cube_r92", ModelPartBuilder.create().uv(78, 9).cuboid(-3.0F, -4.0F, -4.0F, 6.0F, 7.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -2.0F, 0.0F, 1.309F, 0.0F, 0.0F));

		ModelPartData cube_r93 = head.addChild("cube_r93", ModelPartBuilder.create().uv(18, 44).cuboid(-3.0F, -1.0F, -4.0F, 6.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -1.0F, -4.0F, 0.6545F, 0.0F, 0.0F));

		ModelPartData teeth6 = head.addChild("teeth6", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 11.0F, -9.0F));

		ModelPartData cube_r94 = teeth6.addChild("cube_r94", ModelPartBuilder.create().uv(0, 0).cuboid(1.0F, -2.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(5.0F, -2.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, -9.0F, 10.0F, -2.1817F, 0.0F, 0.0F));

		ModelPartData cube_r95 = teeth6.addChild("cube_r95", ModelPartBuilder.create().uv(0, 0).cuboid(1.0F, -2.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(5.0F, -2.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, -9.0F, 8.0F, -2.1817F, 0.0F, 0.0F));

		ModelPartData cube_r96 = teeth6.addChild("cube_r96", ModelPartBuilder.create().uv(0, 0).cuboid(1.0F, -2.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, -6.0F, 12.0F, -3.1416F, -0.9599F, 1.5708F));

		ModelPartData cube_r97 = teeth6.addChild("cube_r97", ModelPartBuilder.create().uv(0, 0).cuboid(1.0F, -2.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -6.0F, 12.0F, -3.1416F, -0.9599F, 1.5708F));

		ModelPartData cube_r98 = teeth6.addChild("cube_r98", ModelPartBuilder.create().uv(0, 0).cuboid(1.0F, -2.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(5.0F, -2.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, -7.0F, 11.0F, -2.1817F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 128, 128);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
		matrices.push();
		rotate(matrices);
		matrices.translate(0.0F, -1.5F, 0.0F);
		rake.render(matrices, vertices, light, overlay);
		matrices.pop();
	}

	@Override
	public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
		this.yaw = headYaw;
	}

	public void rotate(MatrixStack matrices) {
		this.yaw += 180;
		this.yaw = this.yaw % 360;
		this.yaw -= 180;
		if (this.yaw != 0.0F) {
			matrices.multiply(new Quaternionf().rotationZYX(0F, (float) (-(this.yaw * Math.PI)/180), -110F));
		}
	}
}