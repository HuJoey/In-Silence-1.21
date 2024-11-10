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
import net.hujoe.insilence.entity.client.*;
import net.hujoe.insilence.network.payloads.RakeListReceivePayload;
import net.hujoe.insilence.network.payloads.RakeUpdatePayload;
import net.hujoe.insilence.server.RakeManager;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.EntityType;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class InsilenceClient implements ClientModInitializer {
    private static BlindnessHandler blindnessHandler;
    @Override
    public void onInitializeClient(){
        EntityRendererRegistry.register(ModEntities.SOUNDENTITY, SoundEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.RAKE, RakeRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.RAKE_ARMS, RakeArmModel::getTexturedModelData);

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), ModBlocks.TALL_WHEAT);

        ClientPlayNetworking.registerGlobalReceiver(RakeUpdatePayload.ID, (payload, context) -> {
            context.client().execute(() -> {
                ClientRakeManager.getRakeManager().toggleUser(payload.username());
            });
        });

        ClientPlayNetworking.registerGlobalReceiver(RakeListReceivePayload.ID, (payload, context) -> {
            context.client().execute(() -> {
                ClientRakeManager.getRakeManager().receiveList((ArrayList<String>) payload.list());
            });
        });

        blindnessHandler = new BlindnessHandler();
    }

    public static BlindnessHandler getBlindnessHandler(){
        return blindnessHandler;
    }
}
