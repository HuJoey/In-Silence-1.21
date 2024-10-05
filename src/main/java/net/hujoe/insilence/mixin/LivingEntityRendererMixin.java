package net.hujoe.insilence.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.hujoe.insilence.Insilence;
import net.hujoe.insilence.client.RakeModel;
import net.hujoe.insilence.server.RakeManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin<M extends EntityModel> {
	@Unique
	private final M RAKE = ;

	@ModifyReturnValue(method = "getModel", at = @At("RETURN"))
	public M getModel(M original) {
		if ((Object) this instanceof PlayerEntityRenderer pem) {
			MinecraftClient minecraftClient = MinecraftClient.getInstance();
			ClientPlayerEntity clientPlayerEntity = minecraftClient.player;
			if (RakeManager.getRakeManager().isRake(clientPlayerEntity.getNameForScoreboard())) {
				MinecraftClient.getInstance().getEntityModelLoader().get
				return RAKE;
			}
		}
		return original;
	}
}