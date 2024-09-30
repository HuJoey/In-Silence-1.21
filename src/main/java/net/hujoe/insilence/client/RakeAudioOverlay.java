package net.hujoe.insilence.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.hujoe.insilence.Insilence;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.LayeredDrawer;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.SimpleDefaultedRegistry;
import net.minecraft.util.Identifier;

public class RakeAudioOverlay implements HudRenderCallback {

    private final MinecraftClient client;
    private final LayeredDrawer layeredDrawer;

    public RakeAudioOverlay(){
        this.client = MinecraftClient.getInstance();
        this.layeredDrawer = new LayeredDrawer();
    }

    public void render(DrawContext context, RenderTickCounter tickCounter) {
        RenderSystem.enableDepthTest();
        this.layeredDrawer.render(context, tickCounter);
        RenderSystem.disableDepthTest();
    }
/*
    public void renderWheel(DrawContext context) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        int i = context.getScaledWindowWidth() / 2;
        context.drawGuiTexture(RAKE_WHEEL, i - 91, context.getScaledWindowHeight() - 22, 182, 182);
    }

 */

    @Override
    public void onHudRender(DrawContext drawContext, RenderTickCounter tickCounter) {

    }
}
