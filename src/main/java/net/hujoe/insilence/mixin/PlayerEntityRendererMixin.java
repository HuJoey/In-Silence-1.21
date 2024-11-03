package net.hujoe.insilence.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.hujoe.insilence.Insilence;
import net.hujoe.insilence.client.ClientRakeManager;
import net.hujoe.insilence.entity.client.ModModelLayers;
import net.hujoe.insilence.entity.client.RakeModel;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Arm;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin<T extends LivingEntity> {
	@Unique
	private static final Identifier rakeTexture = Identifier.of(Insilence.MOD_ID, "textures/rake/rake.png");
	private final ModelPart leftArm = new RakeModel<>(MinecraftClient.getInstance().getEntityModelLoader().getModelPart(ModModelLayers.RAKE)).leftwrist;
	private final ModelPart rightArm = new RakeModel<>(MinecraftClient.getInstance().getEntityModelLoader().getModelPart(ModModelLayers.RAKE)).rightwrist;

    @ModifyReturnValue(method = "getTexture(Lnet/minecraft/client/network/AbstractClientPlayerEntity;)Lnet/minecraft/util/Identifier;", at = @At("RETURN"))
	private Identifier getTexture(Identifier original, AbstractClientPlayerEntity abstractClientPlayerEntity) {
		if (ClientRakeManager.getRakeManager().isRake(abstractClientPlayerEntity.getNameForScoreboard())) {
			return rakeTexture;
		}
		return original;
	}

	@Inject(method = "setModelPose", at = @At("HEAD"), cancellable = true)
	private void setModelPose(AbstractClientPlayerEntity player, CallbackInfo ci) {
		if (ClientRakeManager.getRakeManager().isRake(player.getNameForScoreboard())) {
			ci.cancel();
		}
	}


	@Inject(method = "renderArm", at = @At("HEAD"), cancellable = true)
	private void renderArm(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, AbstractClientPlayerEntity player, ModelPart arm, ModelPart sleeve, CallbackInfo ci) {
		if (ClientRakeManager.getRakeManager().isRake(player.getNameForScoreboard())) {
			PlayerEntityModel<AbstractClientPlayerEntity> playerEntityModel = ((PlayerEntityRenderer) (Object) this).getModel();
            playerEntityModel.setVisible(!player.isSpectator());
			matrices.push();
			if (player.getMainArm() == Arm.RIGHT) {
				matrices.translate(0, -0.75, 1.5);
				arm = rightArm;
				arm.pitch = 4.7F;
				arm.yaw = 4.5F;
			} else {
				matrices.translate(0, -0.25, 1.5);
				arm = leftArm;
				arm.pitch = 5F;
				arm.yaw = -4.25F;
			}
			matrices.scale(3F, 3F, 3F);
			arm.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntitySolid(rakeTexture)), light, OverlayTexture.DEFAULT_UV);
			matrices.pop();
			ci.cancel();
		}
	}
}