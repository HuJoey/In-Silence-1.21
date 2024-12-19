package net.hujoe.insilence.entity.client;

import net.hujoe.insilence.Insilence;
import net.hujoe.insilence.entity.custom.MouseEntity;
import net.hujoe.insilence.entity.custom.RavenEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

// Made with Blockbench 4.11.1
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class RavenModel extends GeoModel<RavenEntity> {

	@Override
	public Identifier getModelResource(RavenEntity animatable) {
		return Identifier.of(Insilence.MOD_ID, "geo/raven.geo.json");
	}

	@Override
	public Identifier getTextureResource(RavenEntity animatable) {
		return Identifier.of(Insilence.MOD_ID, "textures/entity/raven.png");
	}

	@Override
	public Identifier getAnimationResource(RavenEntity animatable) {
		return Identifier.of(Insilence.MOD_ID, "animations/raven.animation.json");
	}
}