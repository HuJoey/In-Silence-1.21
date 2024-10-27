package net.hujoe.insilence.mixin;

import com.google.gson.JsonSyntaxException;
import net.hujoe.insilence.Insilence;
import net.hujoe.insilence.client.ClientRakeManager;
import net.hujoe.insilence.server.RakeManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.PostEffectProcessor;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
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
    private static final Identifier BLUR_PROCESSOR = Identifier.ofVanilla("shaders/post/blur.json");
    @Nullable private PostEffectProcessor rakeBlurPostProcessor;

    @Inject(method = "tick", at = @At("HEAD"))
    public void tick(CallbackInfo ci){
        if (this.getClient().player != null && ClientRakeManager.getRakeManager().isRake(this.getClient().player.getNameForScoreboard())){
            if (this.rakeBlurPostProcessor != null) {
                this.rakeBlurPostProcessor.setUniforms("Radius", 1);
                this.rakeBlurPostProcessor.render(0.16F);
            }
        }
    }


    @Inject(method = "renderBlur", at = @At("HEAD"), cancellable = true)
    public void renderBlur(float delta, CallbackInfo ci) {
        //Insilence.LOGGER.info(delta + "");
    }

    @Inject(method = "loadBlurPostProcessor", at = @At("TAIL"))
    private void loadBlurPostProcessor(ResourceFactory resourceFactory, CallbackInfo ci) {
        if (this.rakeBlurPostProcessor != null) {
            this.rakeBlurPostProcessor.close();
        }

        try {
            this.rakeBlurPostProcessor = new PostEffectProcessor(this.getClient().getTextureManager(), resourceFactory, this.getClient().getFramebuffer(), BLUR_PROCESSOR);
            this.rakeBlurPostProcessor.setupDimensions(this.getClient().getWindow().getFramebufferWidth(), this.getClient().getWindow().getFramebufferHeight());
        } catch (IOException var3) {
            Insilence.LOGGER.warn("Failed to load shader: {}", BLUR_PROCESSOR, var3);
        } catch (JsonSyntaxException var4) {
            Insilence.LOGGER.warn("Failed to parse shader: {}", BLUR_PROCESSOR, var4);
        }
    }
}
