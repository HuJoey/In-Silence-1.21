package net.hujoe.insilence.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.block.enums.CameraSubmersionType;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.FogShape;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BackgroundRenderer.class)
public class BackgroundRendererMixin {
    @Shadow private static float red;
    @Shadow private static float blue;
    @Shadow private static float green;

    @Inject(method = "render", at = @At("RETURN"))
    private static void changeFogColor(Camera camera, float tickDelta, ClientWorld world, int viewDistance, float skyDarkness, CallbackInfo ci){
        red = 11 / 255.0F;
        green = 14 / 255.0F;
        blue = 23 / 255.0F;
    }

    @Inject(method = "applyFog", at = @At("RETURN"))
    private static void applySpookyFog(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog, float tickDelta, CallbackInfo ci){
        BackgroundRenderer.FogData fogData = new BackgroundRenderer.FogData(fogType);
        CameraSubmersionType cameraSubmersionType = camera.getSubmersionType();
        if (cameraSubmersionType == CameraSubmersionType.NONE && fogType == BackgroundRenderer.FogType.FOG_TERRAIN) {
            fogData.fogStart = -8.0F;
            fogData.fogEnd = 45.0F;
            RenderSystem.setShaderFogStart(fogData.fogStart);
            RenderSystem.setShaderFogEnd(fogData.fogEnd);
        }
    }
}
