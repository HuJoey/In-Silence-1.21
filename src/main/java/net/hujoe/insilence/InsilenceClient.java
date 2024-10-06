package net.hujoe.insilence;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.hujoe.insilence.client.ModModelLayers;
import net.hujoe.insilence.client.RakeModel;
import net.hujoe.insilence.client.RakeRenderer;
import net.hujoe.insilence.client.SoundEntityRenderer;
import net.hujoe.insilence.entity.ModEntities;
import net.hujoe.insilence.entity.custom.RakeEntity;
import net.hujoe.insilence.server.RakeManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.MathHelper;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

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
        EntityRendererRegistry.register(ModEntities.RAKE, (context) -> {
            return new RakeRenderer(context);
        });

        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.RAKE, RakeModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.RAKE, RakeRenderer::new);

        CommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess, environment) -> dispatcher.register(literal("rake")
                .requires(serverCommandSource -> serverCommandSource.hasPermissionLevel(2))
                .executes(commandContext -> {
                    ServerCommandSource source = commandContext.getSource();
                    Entity sender = source.getEntity();
                    if (sender != null) {
                        RakeManager.getRakeManager().toggleUser(sender.getNameForScoreboard());
                    }
                    commandContext.getSource().sendFeedback(() -> Text.literal("You Toggled Rake"), false);
                    return 1;
                }))));
    }

    private double calculateAngle(double m1, double m2){
        double angle = Math.abs((m2 - m1) / (1 + m1 * m2));
        double ret = Math.atan(angle);
        return (ret * 180) / 3.14159265;
    }
}
