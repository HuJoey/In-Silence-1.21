package net.hujoe.insilence.entity.client;

import net.hujoe.insilence.Insilence;
import net.hujoe.insilence.entity.custom.RakeEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import org.joml.Quaternionf;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;

import static net.minecraft.data.DataProvider.LOGGER;

// Made with Blockbench 4.11.1
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class RakeModel extends GeoModel<RakeEntity> {

	@Override
	public Identifier getModelResource(RakeEntity animatable) {
		return Identifier.of(Insilence.MOD_ID, "geo/rake.geo.json");
	}

	@Override
	public Identifier getTextureResource(RakeEntity animatable) {
		return Identifier.of(Insilence.MOD_ID, "textures/entity/rake.png");
	}

	@Override
	public Identifier getAnimationResource(RakeEntity animatable) {
		return Identifier.of(Insilence.MOD_ID, "animations/rake.animation.json");
	}
}