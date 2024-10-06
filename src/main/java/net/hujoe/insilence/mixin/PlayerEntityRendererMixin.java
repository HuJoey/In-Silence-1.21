package net.hujoe.insilence.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.hujoe.insilence.Insilence;
import net.hujoe.insilence.server.RakeManager;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin {
	@Unique
	private static final Identifier RAKE = Identifier.of(Insilence.MOD_ID, "textures/rake/rake.png");
	@ModifyReturnValue(method = "getTexture(Lnet/minecraft/client/network/AbstractClientPlayerEntity;)Lnet/minecraft/util/Identifier;", at = @At("RETURN"))
	private Identifier getTexture(Identifier original, AbstractClientPlayerEntity abstractClientPlayerEntity) {
		if (RakeManager.getRakeManager().isRake(abstractClientPlayerEntity.getNameForScoreboard())) {
			return RAKE;
		}
		return original;
	}

	@Inject(method = "setModelPose", at = @At("HEAD"), cancellable = true)
	private void setModelPose(AbstractClientPlayerEntity player, CallbackInfo ci) {
		if (RakeManager.getRakeManager().isRake(player.getNameForScoreboard())) {
			ci.cancel();
		}
	}
}