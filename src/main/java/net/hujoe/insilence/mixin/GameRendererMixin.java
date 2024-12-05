package net.hujoe.insilence.mixin;

import com.google.gson.JsonSyntaxException;
import com.mojang.blaze3d.systems.RenderSystem;
import net.hujoe.insilence.FlashActivator;
import net.hujoe.insilence.InSilenceEssentials;
import net.hujoe.insilence.Insilence;
import net.hujoe.insilence.InsilenceClient;
import net.hujoe.insilence.client.BlindnessHandler;
import net.hujoe.insilence.client.ClientRakeManager;
import net.hujoe.insilence.server.RakeManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.PostEffectProcessor;
import net.minecraft.client.gl.ShaderProgram;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.BufferBuilderStorage;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.Entity;
import net.minecraft.resource.ResourceFactory;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin implements FlashActivator {
    @Shadow public abstract MinecraftClient getClient();
    @Shadow @Final private BufferBuilderStorage buffers;
    private static final BlindnessHandler blindness = InsilenceClient.getBlindnessHandler();
    private static final Identifier VISION_TINT = Identifier.of(Insilence.MOD_ID,"textures/misc/rake_vision.png");
    private static final Identifier FLASH = Identifier.of(Insilence.MOD_ID,"textures/misc/flash.png");
    private boolean shouldBeFlashed;
    private int fadeInTime;
    private int fadeOutTime;
    private int flashTime;
    private float tintTransparency = 0;
    private float fadeInAmount = 0;
    private float fadeOutAmount = 0;

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

    @Inject(method = "render", at = @At(value = "INVOKE", target = "net/minecraft/util/profiler/Profiler.swap (Ljava/lang/String;)V", shift = At.Shift.AFTER))
    private void renderTint(CallbackInfo ci){
        DrawContext context = new DrawContext(this.getClient(), this.buffers.getEntityVertexConsumers());
        int guiSize = MinecraftClient.getInstance().options.getGuiScale().getValue();
        int gScale = 7 - guiSize;
        int x = this.getClient().getWindow().getScaledWidth() / 2;
        int y = this.getClient().getWindow().getScaledHeight();
        ShaderProgram og = RenderSystem.getShader();
        RenderSystem.setShader(GameRenderer::getRenderTypeTextSeeThroughProgram);
        if (this.getClient().player != null && ClientRakeManager.getRakeManager().isRake(this.getClient().player.getNameForScoreboard())) {
        if (((InSilenceEssentials) getClient().player).isAttacking()){
            if (tintTransparency < 0.3F){
                tintTransparency += 0.001F;
            }
        } else if (tintTransparency > 0.1F){
            tintTransparency -= 0.01F;
        } else {
            tintTransparency = 0.1F;
        }
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, tintTransparency);
        RenderSystem.enableBlend();
        context.drawTexture(VISION_TINT, x - (512 * gScale) , y - (512 * gScale), 0, 0, 1024 * gScale, 1024 * gScale, 1024 * gScale, 1024 * gScale);
        RenderSystem.disableBlend();
        RenderSystem.clear(256, MinecraftClient.IS_SYSTEM_MAC);
        }

        if (shouldBeFlashed){
            if (fadeInTime > 0){
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, (1F - (fadeInAmount * fadeInTime)));
                RenderSystem.enableBlend();
                context.drawTexture(FLASH, x - (512 * gScale), y - (512 * gScale), 0, 0, 1024 * gScale, 1024 * gScale, 1024 * gScale, 1024 * gScale);
                RenderSystem.disableBlend();
                RenderSystem.clear(256, MinecraftClient.IS_SYSTEM_MAC);
                fadeInTime--;
            } else if (flashTime > 0){
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1F);
                RenderSystem.enableBlend();
                context.drawTexture(FLASH, x - (512 * gScale), y - (512 * gScale), 0, 0, 1024 * gScale, 1024 * gScale, 1024 * gScale, 1024 * gScale);
                RenderSystem.disableBlend();
                RenderSystem.clear(256, MinecraftClient.IS_SYSTEM_MAC);
                flashTime--;
            } else if (fadeOutTime > 0){
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, fadeOutAmount * fadeOutTime);
                RenderSystem.enableBlend();
                context.drawTexture(FLASH, x - (512 * gScale), y - (512 * gScale), 0, 0, 1024 * gScale, 1024 * gScale, 1024 * gScale, 1024 * gScale);
                RenderSystem.disableBlend();
                RenderSystem.clear(256, MinecraftClient.IS_SYSTEM_MAC);
                fadeOutTime--;
            } else {
                shouldBeFlashed = false;
                ((InSilenceEssentials)this.getClient().player).setStunned(false);
            }
        }
    }

    public void activateFlash(){
        fadeInTime = MinecraftClient.getInstance().options.getMaxFps().getValue() / 2;
        if (this.getClient().player != null && ClientRakeManager.getRakeManager().isRake(this.getClient().player.getNameForScoreboard())) {
            flashTime = 5 * MinecraftClient.getInstance().options.getMaxFps().getValue();
            fadeOutTime = 6 * MinecraftClient.getInstance().options.getMaxFps().getValue();
        } else {
            flashTime = 2 * MinecraftClient.getInstance().options.getMaxFps().getValue();
            fadeOutTime = 3 * MinecraftClient.getInstance().options.getMaxFps().getValue();
        }
        fadeInAmount = 1F / fadeInTime;
        fadeOutAmount = 1F / fadeOutTime;
        shouldBeFlashed = true;

        ((InSilenceEssentials)this.getClient().player).setStunned(true);
    }
}
