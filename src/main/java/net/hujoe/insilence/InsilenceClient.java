package net.hujoe.insilence;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.hujoe.insilence.block.ModBlocks;
import net.hujoe.insilence.client.*;
import net.hujoe.insilence.entity.ModEntities;
import net.hujoe.insilence.entity.custom.RakeEntity;
import net.hujoe.insilence.network.InsilenceNetworking;
import net.hujoe.insilence.network.payloads.RakeUpdatePayload;
import net.hujoe.insilence.network.payloads.SignalSoundPayload;
import net.hujoe.insilence.server.RakeManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderLayer;
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
    private static BlindnessHandler blindnessHandler;
    @Override
    public void onInitializeClient(){
        EntityRendererRegistry.register(ModEntities.SOUNDENTITY, SoundEntityRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.RAKE, RakeModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.RAKE, RakeRenderer::new);

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), ModBlocks.TALL_WHEAT);

        ClientPlayNetworking.registerGlobalReceiver(RakeUpdatePayload.ID, (payload, context) -> {
            context.client().execute(() -> {
                ClientRakeManager.getRakeManager().toggleUser(payload.username());
            });
        });

        blindnessHandler = new BlindnessHandler();
    }

    public static BlindnessHandler getBlindnessHandler(){
        return blindnessHandler;
    }
}
