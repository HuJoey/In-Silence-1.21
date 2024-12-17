package net.hujoe.insilence.entity.client;

import net.hujoe.insilence.Insilence;
import net.hujoe.insilence.entity.custom.MouseEntity;
import net.hujoe.insilence.entity.custom.RakeEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

// Made with Blockbench 4.11.1
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class MouseModel extends GeoModel<MouseEntity> {

	@Override
	public Identifier getModelResource(MouseEntity animatable) {
		return Identifier.of(Insilence.MOD_ID, "geo/mouse.geo.json");
	}

	@Override
	public Identifier getTextureResource(MouseEntity animatable) {
		return Identifier.of(Insilence.MOD_ID, "textures/entity/mouse.png");
	}

	@Override
	public Identifier getAnimationResource(MouseEntity animatable) {
		return Identifier.of(Insilence.MOD_ID, "animations/mouse.animation.json");
	}
}