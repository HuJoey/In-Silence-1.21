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
import net.hujoe.insilence.sound.ModSounds;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.lwjgl.glfw.GLFW;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class InsilenceClient implements ClientModInitializer {
    private static BlindnessHandler blindnessHandler;
    public static KeyBinding flashKeyBinding;
    private static KeyBinding lockInKeyBinding;
    private static KeyBinding dashKeyBinding;
    @Override
    public void onInitializeClient(){
        EntityRendererRegistry.register(ModEntities.SOUNDENTITY, SoundEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.LOCATIONENTITY, LocationEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.RAKE, RakeRenderer::new);
        EntityRendererRegistry.register(ModEntities.MOUSE, MouseRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.RAKE_ARMS, RakeArmModel::getTexturedModelData);

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), ModBlocks.TALL_WHEAT);

        ClientPlayNetworking.registerGlobalReceiver(RakeUpdatePayload.ID, (payload, context) -> {
            context.client().execute(() -> {
                ClientRakeManager.getRakeManager().toggleRakeUser(payload.username());
            });
        });

        ClientPlayNetworking.registerGlobalReceiver(MouseUpdatePayload.ID, (payload, context) -> {
            context.client().execute(() -> {
                ClientRakeManager.getRakeManager().toggleMouseUser(payload.username());
            });
        });

        ClientPlayNetworking.registerGlobalReceiver(RakeListReceivePayload.ID, (payload, context) -> {
            context.client().execute(() -> {
                ClientRakeManager.getRakeManager().receiveRakeList((ArrayList<String>) payload.list());
            });
        });

        ClientPlayNetworking.registerGlobalReceiver(MouseListReceivePayload.ID, (payload, context) -> {
            context.client().execute(() -> {
                ClientRakeManager.getRakeManager().receiveMouseList((ArrayList<String>) payload.list());
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
                ((InSilenceEssentials) context.player().getWorld().getEntityById(payload.attackerId())).cancelAttack();
                ((InSilenceEssentials) context.player().getWorld().getEntityById(payload.targetId())).cancelAttack();

                if (payload.shouldBlind()){
                    FlashActivator renderer = (FlashActivator) context.client().gameRenderer;
                    renderer.activateFlash();
                }
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

        ClientPlayNetworking.registerGlobalReceiver(RakeAttackReceivePayload.ID, (payload, context) -> {
            context.client().execute(() -> {
                PlayerEntity player = (PlayerEntity) context.player().getEntityWorld().getEntityById(payload.attackerId());
                if (context.player().getEntityWorld().getEntityById(payload.attackerId()) != null && context.player().getId() != payload.attackerId()) {
                    InSilenceEssentials p = (InSilenceEssentials) player;
                    p.triggerJumpscare();
                }
                HitResult result = player.raycast(1.3, 0, false);
                if (context.player().getEntityWorld().getEntityById(payload.targetId()) != null) {
                    PlayerEntity target = (PlayerEntity) context.player().getEntityWorld().getEntityById(payload.targetId());
                    InSilenceEssentials p2 = (InSilenceEssentials) target;
                    p2.triggerCaught(player.getYaw(), new Vec3d(result.getPos().x, player.getY(), result.getPos().z));
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
                if (ClientRakeManager.getRakeManager().isMouse(client.player.getNameForScoreboard())){
                    boolean result = ((InSilenceEssentials) client.player).trySqueak();
                    if (result){
                        ClientPlayNetworking.send(new SqueakPayload(client.player.getNameForScoreboard()));
                    }
                } else {
                    PlayerInventory inv = client.player.getInventory();
                    for (int i = 0; i < 36; i++) {
                        ItemStack stack = inv.getStack(i);
                        if (stack.isOf(ModItems.FLASHLIGHT) && stack.get(ModItems.FLASH_STAGE) != null && stack.get(ModItems.FLASH_STAGE) > 1) {
                            FlashlightItem item = (FlashlightItem) stack.getItem();
                            item.flash(client.world, stack, client.player);
                            break;
                        }
                    }
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
