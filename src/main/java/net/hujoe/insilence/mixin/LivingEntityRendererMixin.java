package net.hujoe.insilence.mixin;

import net.hujoe.insilence.client.ClientRakeManager;
import net.hujoe.insilence.entity.client.ModModelLayers;
import net.hujoe.insilence.entity.client.RakeModel;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin<T extends LivingEntity> {
	@Shadow @Nullable protected abstract RenderLayer getRenderLayer(T entity, boolean showBody, boolean translucent, boolean showOutline);

	@Shadow
	public static int getOverlay(LivingEntity entity, float whiteOverlayProgress) {
		return 0;
	}

	@Unique
	private final EntityModel<T> model = new RakeModel<>(MinecraftClient.getInstance().getEntityModelLoader().getModelPart(ModModelLayers.RAKE));

	@Inject(method="render", at = @At("HEAD"), cancellable = true)
	public void render(T livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, CallbackInfo ci) {
			if (livingEntity.getType() == EntityType.PLAYER) {
				if (ClientRakeManager.getRakeManager().isRake(livingEntity.getNameForScoreboard())) {
					int light;
					if (livingEntity.getWorld() != null) {
						light = WorldRenderer.getLightmapCoordinates(livingEntity.getWorld(), BlockPos.ofFloored(livingEntity.getPos()));
					} else {
						light = LightmapTextureManager.MAX_LIGHT_COORDINATE;
					}
					model.setAngles(livingEntity, 0, 0, 0, livingEntity.getHeadYaw(), 0);
					model.render(matrixStack, vertexConsumerProvider.getBuffer(this.getRenderLayer(livingEntity, true, false, false)), light, this.getOverlay(livingEntity, 0), 0);
					ci.cancel();
			}
		}
	}
}