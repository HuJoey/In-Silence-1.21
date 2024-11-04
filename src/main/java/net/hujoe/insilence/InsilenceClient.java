package net.hujoe.insilence;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.hujoe.insilence.block.ModBlocks;
import net.hujoe.insilence.client.*;
import net.hujoe.insilence.entity.ModEntities;
import net.hujoe.insilence.entity.client.ModModelLayers;
import net.hujoe.insilence.entity.client.RakeModel;
import net.hujoe.insilence.entity.client.RakeRenderer;
import net.hujoe.insilence.entity.client.SoundEntityRenderer;
import net.hujoe.insilence.network.payloads.RakeUpdatePayload;
import net.minecraft.client.render.RenderLayer;

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

        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            HasRakeManager s = (HasRakeManager) server;
            ClientRakeManager.getRakeManager().receiveList(s.getRakeManager().getList());
        });

        blindnessHandler = new BlindnessHandler();
    }

    public static BlindnessHandler getBlindnessHandler(){
        return blindnessHandler;
    }
}
