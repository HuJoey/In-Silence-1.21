package net.hujoe.insilence.mixin;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.hujoe.insilence.Insilence;
import net.hujoe.insilence.client.ClientRakeManager;
import net.hujoe.insilence.entity.ModEntities;
import net.hujoe.insilence.entity.client.ModModelLayers;
import net.hujoe.insilence.entity.client.RakeModel;
import net.hujoe.insilence.entity.client.RakeRenderer;
import net.hujoe.insilence.entity.custom.RakeEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import software.bernie.geckolib.animation.AnimatableManager;

import java.nio.file.Path;

@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin<T extends LivingEntity> {
	@Unique
	RakeEntity rake;
	@Unique
	RakeRenderer rakeRenderer;

	@Inject(method="render", at = @At("HEAD"), cancellable = true)
	public void render(T livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, CallbackInfo ci) {
			if (livingEntity.getType() == EntityType.PLAYER) {
				if (ClientRakeManager.getRakeManager().isRake(livingEntity.getNameForScoreboard())) {

					if (rake == null){
						rake = new RakeEntity(ModEntities.RAKE, livingEntity.getWorld());
						rakeRenderer = (RakeRenderer) MinecraftClient.getInstance().getEntityRenderDispatcher().getRenderer(rake);
					}

					rake.handSwinging = livingEntity.handSwinging;
					rake.handSwingTicks = livingEntity.handSwingTicks;
					rake.lastHandSwingProgress = livingEntity.lastHandSwingProgress;
					rake.handSwingProgress = livingEntity.handSwingProgress;
					rake.bodyYaw = livingEntity.bodyYaw;
					rake.prevBodyYaw = livingEntity.prevBodyYaw;
					rake.headYaw = livingEntity.headYaw;
					rake.prevHeadYaw = livingEntity.prevHeadYaw;
					rake.age = livingEntity.age;
					rake.preferredHand = livingEntity.preferredHand;
					rake.setOnGround(livingEntity.isOnGround());
					rake.setVelocity(livingEntity.getVelocity());
					rake.setAttacking(livingEntity.isUsingItem());
					rake.setPose(livingEntity.getPose());

					rakeRenderer.render(rake, f, g, matrixStack, vertexConsumerProvider, i);

					if (isMoving(livingEntity)){
						if (livingEntity.isSprinting()){
							rake.getAnimatableInstanceCache().getManagerForId(rake.getId()).tryTriggerAnimation("controller", "sprint");
						} else {
							rake.getAnimatableInstanceCache().getManagerForId(rake.getId()).tryTriggerAnimation("controller", "walk");
						}
					} else {
						rake.getAnimatableInstanceCache().getManagerForId(rake.getId()).tryTriggerAnimation("controller", "idle");
					}
					ci.cancel();
			}
		}
	}

	@Unique
	public boolean isMoving(LivingEntity living){
		boolean moving = false;
		if (living.lastRenderX != living.getX()){
			moving = true;
		}
		if (living.lastRenderZ != living.getZ()){
			moving = true;
		}
		return moving;
	}
}