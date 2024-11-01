package net.hujoe.insilence.mixin;

import com.google.gson.JsonSyntaxException;
import net.hujoe.insilence.Insilence;
import net.hujoe.insilence.InsilenceClient;
import net.hujoe.insilence.client.BlindnessHandler;
import net.hujoe.insilence.client.ClientRakeManager;
import net.hujoe.insilence.server.RakeManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.PostEffectProcessor;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.Entity;
import net.minecraft.resource.ResourceFactory;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
    @Shadow public abstract MinecraftClient getClient();
    private static final BlindnessHandler blindness = InsilenceClient.getBlindnessHandler();

    @Inject(method = "loadBlurPostProcessor", at = @At("TAIL"))
    private void loadBlurPostProcessor(ResourceFactory resourceFactory, CallbackInfo ci) {
        if (blindness.getProcessor() != null) {
            blindness.close();
        }

        blindness.loadProcessor(resourceFactory);
    }

    @Inject(method = "onResized", at = @At("TAIL"))
    public void onResized(int width, int height, CallbackInfo ci){
        blindness.setDimensions(width, height);
    }

    @Inject(method = "renderWorld", at = @At("TAIL"))
    public void renderWorld(RenderTickCounter tickCounter, CallbackInfo ci){
        if (this.getClient().player != null && ClientRakeManager.getRakeManager().isRake(this.getClient().player.getNameForScoreboard())) {
            blindness.renderBlur(getClient().getRenderTickCounter().getLastDuration());
        }
    }
}
