package net.hujoe.insilence;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.hujoe.insilence.client.ModModelLayers;
import net.hujoe.insilence.client.RakeModel;
import net.hujoe.insilence.client.SoundEntityRenderer;
import net.hujoe.insilence.entity.ModEntities;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.MathHelper;

public class InsilenceClient implements ClientModInitializer {
    private static final net.minecraft.util.Identifier RAKE_WHEEL = Identifier.of(Insilence.MOD_ID,"textures/gui/sprites/wheel.png");
    @Override
    public void onInitializeClient(){
        // renders the rake wheel texture above the hotbar
        HudRenderCallback.EVENT.register((drawContext, tickDeltaManager) -> {
            MinecraftClient client = MinecraftClient.getInstance();
            int x = client.getWindow().getScaledWidth() / 2;
            int y = client.getWindow().getScaledHeight();
            RenderSystem.setShader(GameRenderer::getRenderTypeTextSeeThroughProgram);
            RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);
            RenderSystem.enableBlend(); // allows wheel to be transparent
            drawContext.drawTexture(RAKE_WHEEL, x - 78, y - 110,0, 0, 156, 64, 156, 64); // draws the rake wheel texture
            RenderSystem.disableBlend(); // prevents transparency issue when hitting esc
        });

        EntityRendererRegistry.register(ModEntities.SOUNDENTITY, (context) -> {
            return new SoundEntityRenderer(context);
        });

        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.RAKE, RakeModel::getTexturedModelData);
    }

    private double calculateAngle(double m1, double m2){
        double angle = Math.abs((m2 - m1) / (1 + m1 * m2));
        double ret = Math.atan(angle);
        return (ret * 180) / 3.14159265;
    }
}
