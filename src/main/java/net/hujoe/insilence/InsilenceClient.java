package net.hujoe.insilence;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.MathHelper;

public class InsilenceClient implements ClientModInitializer {
    private static final net.minecraft.util.Identifier RAKE_WHEEL = Identifier.of(Insilence.MOD_ID,"textures/gui/wheel.png");
    @Override
    public void onInitializeClient(){
        HudRenderCallback.EVENT.register((drawContext, tickDeltaManager) -> {
            int i = drawContext.getScaledWindowWidth() / 2;
            drawContext.drawGuiTexture(RAKE_WHEEL, i - 91, drawContext.getScaledWindowHeight() - 22, 182, 182);
        });
    }
}
