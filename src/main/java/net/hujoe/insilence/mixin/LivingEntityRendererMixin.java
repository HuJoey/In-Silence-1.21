package net.hujoe.insilence.mixin;

import net.hujoe.insilence.Insilence;
import net.hujoe.insilence.client.ClientRakeManager;
import net.hujoe.insilence.entity.ModEntities;
import net.hujoe.insilence.entity.client.ModModelLayers;
import net.hujoe.insilence.entity.client.RakeModel;
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

import java.nio.file.Path;

@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin<T extends LivingEntity> {
	@Inject(method="render", at = @At("HEAD"), cancellable = true)
	public void render(T livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, CallbackInfo ci) {
			if (livingEntity.getType() == EntityType.PLAYER) {
				if (ClientRakeManager.getRakeManager().isRake(livingEntity.getNameForScoreboard())) {

					PathAwareEntity rake = new RakeEntity(ModEntities.RAKE, livingEntity.getWorld());
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

					EntityRenderer<? super PathAwareEntity> rakeRenderer = MinecraftClient.getInstance().getEntityRenderDispatcher().getRenderer(rake);
					rakeRenderer.render(rake, f, g, matrixStack, vertexConsumerProvider, i);

					int light;
					if (livingEntity.getWorld() != null) {
						light = WorldRenderer.getLightmapCoordinates(livingEntity.getWorld(), BlockPos.ofFloored(livingEntity.getPos()));
					} else {
						light = LightmapTextureManager.MAX_LIGHT_COORDINATE;
					}
					//model.setAngles(livingEntity, 0, 0, 0, livingEntity.getHeadYaw(), 0);
					//model.render(matrixStack, vertexConsumerProvider.getBuffer(this.getRenderLayer(livingEntity, true, false, false)), light, this.getOverlay(livingEntity, 0), 0);
					ci.cancel();
			}
		}
	}
}