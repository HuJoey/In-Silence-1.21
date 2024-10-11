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
    @Override
    public void onInitializeClient(){
        EntityRendererRegistry.register(ModEntities.SOUNDENTITY, SoundEntityRenderer::new);

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


}
