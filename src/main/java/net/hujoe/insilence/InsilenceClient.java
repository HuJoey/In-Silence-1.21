package net.hujoe.insilence;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.hujoe.insilence.block.ModBlocks;
import net.hujoe.insilence.block.entity.FlashlightLightBlockEntity;
import net.hujoe.insilence.block.entity.ModBlockEntities;
import net.hujoe.insilence.client.*;
import net.hujoe.insilence.entity.ModEntities;
import net.hujoe.insilence.entity.client.*;
import net.hujoe.insilence.item.ModItems;
import net.hujoe.insilence.item.custom.FlashlightItem;
import net.hujoe.insilence.network.payloads.*;
import net.hujoe.insilence.server.RakeManager;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.lwjgl.glfw.GLFW;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class InsilenceClient implements ClientModInitializer {
    private static BlindnessHandler blindnessHandler;
    private static KeyBinding flashKeyBinding;
    private static KeyBinding lockInKeyBinding;
    private static KeyBinding dashKeyBinding;
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

        ClientPlayNetworking.registerGlobalReceiver(VolumeUpdatePayload.ID, (payload, context) -> {
            context.client().execute(() -> {
                InSilenceEssentials player = (InSilenceEssentials) context.player();
                player.setLastVolume(payload.volume());
            });
        });

        ClientPlayNetworking.registerGlobalReceiver(FlashReceivePayload.ID, (payload, context) -> {
            context.client().execute(() -> {
                // add flash implementation here
            });
        });

        ClientPlayNetworking.registerGlobalReceiver(LightRestartPayload.ID, (payload, context) -> {
            context.client().execute(() -> {
                World world = context.player().getWorld();
                if (world.getBlockEntity(new BlockPos(payload.x(), payload.y(), payload.z())) != null) {
                    ((FlashlightLightBlockEntity) world.getBlockEntity(new BlockPos(payload.x(), payload.y(), payload.z()))).restartLife();
                }
            });
        });

        ClientPlayNetworking.registerGlobalReceiver(DashClientPayload.ID, (payload, context) -> {
            context.client().execute(() -> {
                if (context.player().getWorld().getEntityById(payload.id()) != null) {
                    LivingEntity player = (LivingEntity) context.player().getWorld().getEntityById(payload.id());
                    InSilenceEssentials p = (InSilenceEssentials) player;
                    p.dash();
                }
            });
        });

        blindnessHandler = new BlindnessHandler();

        flashKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.in-silence.flash",
                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
                GLFW.GLFW_KEY_B, // The keycode of the key
                "category.in-silence.in-silence" // The translation key of the keybinding's category.
        ));

        lockInKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.in-silence.lockIn",
                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
                GLFW.GLFW_KEY_Y, // The keycode of the key
                "category.in-silence.in-silence" // The translation key of the keybinding's category.
        ));

        dashKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.in-silence.dash",
                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
                GLFW.GLFW_KEY_R, // The keycode of the key
                "category.in-silence.in-silence" // The translation key of the keybinding's category.
        ));


        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (flashKeyBinding.wasPressed()) {
                ItemStack stack = client.player.getMainHandStack();
                if(stack.getItem() == ModItems.FLASHLIGHT){
                    ((FlashlightItem) stack.getItem()).flash(client.world, stack, client.player);
                }
            }

            while (lockInKeyBinding.wasPressed()) {
                if (ClientRakeManager.getRakeManager().isRake(client.player.getNameForScoreboard())) {
                    InSilenceEssentials player = (InSilenceEssentials) client.player;
                    if (player.canLockIn()) {
                        player.lockIn();
                        blindnessHandler.lockIn();
                        ClientPlayNetworking.send(new LockInPayload(client.player.getNameForScoreboard()));
                    }
                }
            }

            while (dashKeyBinding.wasPressed()) {
                if (ClientRakeManager.getRakeManager().isRake(client.player.getNameForScoreboard())) {
                    InSilenceEssentials player = (InSilenceEssentials) client.player;
                    if (player.canDash() && client.player.isOnGround()) {
                        player.dash();
                        ClientPlayNetworking.send(new DashPayload(client.player.getNameForScoreboard()));
                    }
                }
            }
        });
    }

    public static BlindnessHandler getBlindnessHandler(){
        return blindnessHandler;
    }
}
