package net.hujoe.insilence.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.hujoe.insilence.Insilence;
import net.hujoe.insilence.server.RakeManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Unique
    private static final net.minecraft.util.Identifier RAKE_WHEEL = Identifier.of(Insilence.MOD_ID,"textures/gui/sprites/wheel.png");

    @Inject(method="render", at = @At("HEAD"))
    public void render(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci){
            MinecraftClient minecraftClient = MinecraftClient.getInstance();
            ClientPlayerEntity clientPlayerEntity = minecraftClient.player;
        assert clientPlayerEntity != null;
        if (RakeManager.getRakeManager().isRake(clientPlayerEntity.getNameForScoreboard())) {
                int x = minecraftClient.getWindow().getScaledWidth() / 2;
                int y = minecraftClient.getWindow().getScaledHeight();
                RenderSystem.setShader(GameRenderer::getRenderTypeTextSeeThroughProgram);
                RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);
                RenderSystem.enableBlend(); // allows wheel to be transparent
                context.drawTexture(RAKE_WHEEL, x - 78, y - 110,0, 0, 156, 64, 156, 64); // draws the rake wheel texture
                RenderSystem.disableBlend(); // prevents transparency issue when hitting esc
        }
    }
}
