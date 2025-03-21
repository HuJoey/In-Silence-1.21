package net.hujoe.insilence.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.hujoe.insilence.InSilenceEssentials;
import net.hujoe.insilence.Insilence;
import net.hujoe.insilence.client.ClientRakeManager;
import net.hujoe.insilence.entity.ModEntities;
import net.hujoe.insilence.entity.client.ModModelLayers;
import net.hujoe.insilence.entity.client.MouseRenderer;
import net.hujoe.insilence.entity.client.RakeModel;
import net.hujoe.insilence.entity.client.RakeRenderer;
import net.hujoe.insilence.entity.custom.MouseEntity;
import net.hujoe.insilence.entity.custom.RakeEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import net.minecraft.client.network.ClientPlayerEntity;
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
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.cache.GeckoLibCache;

import java.nio.file.Path;

@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin<T extends LivingEntity> {

	@Inject(method="render", at = @At("HEAD"), cancellable = true)
	public void render(T livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, CallbackInfo ci) {
			if (livingEntity.getType() == EntityType.PLAYER) {
				if (ClientRakeManager.getRakeManager().isRake(livingEntity.getNameForScoreboard())) {

					InSilenceEssentials p = (InSilenceEssentials) livingEntity;
					if (p.getRakeEntity() == null){
						p.setRakeEntity(new RakeEntity(ModEntities.RAKE, livingEntity.getWorld()));
					}
					RakeEntity r = p.getRakeEntity();
					RakeRenderer rRenderer = (RakeRenderer) MinecraftClient.getInstance().getEntityRenderDispatcher().getRenderer(r);

					r.handSwinging = livingEntity.handSwinging;
					r.handSwingTicks = livingEntity.handSwingTicks;
					r.lastHandSwingProgress = livingEntity.lastHandSwingProgress;
					r.handSwingProgress = livingEntity.handSwingProgress;
					r.bodyYaw = livingEntity.bodyYaw;
					r.prevBodyYaw = livingEntity.prevBodyYaw;
					r.headYaw = livingEntity.headYaw;
					r.prevHeadYaw = livingEntity.prevHeadYaw;
					r.age = livingEntity.age;
					r.preferredHand = livingEntity.preferredHand;
					r.setOnGround(livingEntity.isOnGround());
					r.setVelocity(livingEntity.getVelocity());
					r.setAttacking(livingEntity.isUsingItem());
					r.setPose(livingEntity.getPose());

					rRenderer.render(r, f, g, matrixStack, vertexConsumerProvider, i);

					if (((InSilenceEssentials) livingEntity).isAttacking()){
						r.getAnimatableInstanceCache().getManagerForId(r.getId()).tryTriggerAnimation("controller", "jumpscare");
					} else {
						if (isMoving(livingEntity)) {
							if (((InSilenceEssentials) livingEntity).isDashing()) {
								r.getAnimatableInstanceCache().getManagerForId(r.getId()).tryTriggerAnimation("controller", "sprint");
							} else if (livingEntity.isSprinting()) {
								r.getAnimatableInstanceCache().getManagerForId(r.getId()).tryTriggerAnimation("controller", "run");
							} else {
								r.getAnimatableInstanceCache().getManagerForId(r.getId()).tryTriggerAnimation("controller", "walk");
							}
						} else {
							r.getAnimatableInstanceCache().getManagerForId(r.getId()).tryTriggerAnimation("controller", "idle");
						}
					}
					ci.cancel();
			} else if (ClientRakeManager.getRakeManager().isMouse(livingEntity.getNameForScoreboard())){
					InSilenceEssentials p = (InSilenceEssentials) livingEntity;
					if (p.getMouseEntity() == null){
						p.setMouseEntity(new MouseEntity(ModEntities.MOUSE, livingEntity.getWorld()));
					}
					MouseEntity m = p.getMouseEntity();
					MouseRenderer mRenderer = (MouseRenderer) MinecraftClient.getInstance().getEntityRenderDispatcher().getRenderer(m);

					m.bodyYaw = livingEntity.bodyYaw;
					m.prevBodyYaw = livingEntity.prevBodyYaw;
					m.headYaw = livingEntity.headYaw;
					m.prevHeadYaw = livingEntity.prevHeadYaw;
					m.age = livingEntity.age;
					m.preferredHand = livingEntity.preferredHand;
					m.setOnGround(livingEntity.isOnGround());
					m.setVelocity(livingEntity.getVelocity());
					m.setAttacking(livingEntity.isUsingItem());
					m.setPose(livingEntity.getPose());

					mRenderer.render(m, f, g, matrixStack, vertexConsumerProvider, i);

					if (isMoving(livingEntity)){
						m.getAnimatableInstanceCache().getManagerForId(m.getId()).tryTriggerAnimation("controller", "walk");
					} else {
						m.getAnimatableInstanceCache().getManagerForId(m.getId()).tryTriggerAnimation("controller", "idle");
					}
					ci.cancel();
				}
		}
	}

	@Inject(method="hasLabel", at = @At("HEAD"), cancellable = true)
	public void hasLabel(T livingEntity, CallbackInfoReturnable<Boolean> ci){
		if ((livingEntity.getType() == EntityType.PLAYER) && (ClientRakeManager.getRakeManager().isRake(livingEntity.getNameForScoreboard()) || ClientRakeManager.getRakeManager().isMouse(livingEntity.getNameForScoreboard()))){
			ci.setReturnValue(false);
		} else {
			MinecraftClient minecraftClient = MinecraftClient.getInstance();
			ClientPlayerEntity clientPlayerEntity = minecraftClient.player;
			if (clientPlayerEntity != null && (ClientRakeManager.getRakeManager().isRake(clientPlayerEntity.getNameForScoreboard()) || ClientRakeManager.getRakeManager().isMouse(clientPlayerEntity.getNameForScoreboard()))){
				ci.setReturnValue(false);
			}
		}
	}

	@Inject(method="getShadowRadius(Lnet/minecraft/entity/LivingEntity;)F", at = @At("HEAD"), cancellable = true)
	protected void getShadowRadius(T livingEntity, CallbackInfoReturnable<Float> ci){
		if ((livingEntity.getType() == EntityType.PLAYER) && (ClientRakeManager.getRakeManager().isMouse(livingEntity.getNameForScoreboard()))) {
			ci.setReturnValue(0.3F);
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