package net.hujoe.insilence;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.hujoe.insilence.block.ModBlocks;
import net.hujoe.insilence.block.entity.ModBlockEntities;
import net.hujoe.insilence.client.ClientRakeManager;
import net.hujoe.insilence.entity.ModEntities;
import net.hujoe.insilence.entity.custom.RakeEntity;
import net.hujoe.insilence.item.ModItems;
import net.hujoe.insilence.item.custom.FlashlightItem;
import net.hujoe.insilence.network.payloads.*;
import net.hujoe.insilence.server.RakeManager;
import net.hujoe.insilence.sound.ModSounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.math.Box;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Array;
import java.util.List;

import static net.minecraft.server.command.CommandManager.literal;

public class Insilence implements ModInitializer {
	public static final String MOD_ID = "in-silence";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");
		ModEntities.registerModEntities();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModBlockEntities.registerModBlockEntities();
		ModSounds.registerModSounds();

		CommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess, environment) -> dispatcher.register(literal("rake")
				.requires(serverCommandSource -> serverCommandSource.hasPermissionLevel(2))
				.executes(commandContext -> {
					ServerCommandSource source = commandContext.getSource();
					Entity sender = source.getEntity();
					if (sender != null) {
						RakeManager.getRakeManager().toggleUser(sender.getNameForScoreboard(), sender.getWorld());
					}
					commandContext.getSource().sendFeedback(() -> Text.literal("You Toggled Rake"), false);
					return 1;
				}))));

		PayloadTypeRegistry.playS2C().register(RakeUpdatePayload.ID, RakeUpdatePayload.CODEC);
		PayloadTypeRegistry.playS2C().register(RakeListReceivePayload.ID, RakeListReceivePayload.CODEC);
		PayloadTypeRegistry.playC2S().register(SignalSoundPayload.ID, SignalSoundPayload.CODEC);
		PayloadTypeRegistry.playS2C().register(VolumeUpdatePayload.ID, VolumeUpdatePayload.CODEC);
		PayloadTypeRegistry.playS2C().register(FlashReceivePayload.ID, FlashReceivePayload.CODEC);
		PayloadTypeRegistry.playC2S().register(FlashSendPayload.ID, FlashSendPayload.CODEC);
		PayloadTypeRegistry.playC2S().register(DashPayload.ID, DashPayload.CODEC);
		PayloadTypeRegistry.playC2S().register(LockInPayload.ID, LockInPayload.CODEC);
		PayloadTypeRegistry.playC2S().register(FlashlightActivatePayload.ID, FlashlightActivatePayload.CODEC);
		PayloadTypeRegistry.playC2S().register(RakeJumpPayload.ID, RakeJumpPayload.CODEC);
		PayloadTypeRegistry.playS2C().register(LightRestartPayload.ID, LightRestartPayload.CODEC);

		ServerPlayNetworking.registerGlobalReceiver(SignalSoundPayload.ID, (payload, context) -> {
			context.server().execute(() -> {
				if (context.server().getPlayerManager().getPlayer(payload.username()) != null) {
					context.server().getPlayerManager().getPlayer(payload.username()).playSoundToPlayer(ModSounds.SIGNAL_EVENT, SoundCategory.AMBIENT, payload.volume() / 2, 1);
				}
			});
		});

		ServerPlayNetworking.registerGlobalReceiver(DashPayload.ID, (payload, context) -> {
			context.server().execute(() -> {
				if (context.server().getPlayerManager().getPlayer(payload.username()) != null) {
					PlayerEntity player = context.server().getPlayerManager().getPlayer(payload.username());
					InSilenceEssentials p = (InSilenceEssentials) player;
					p.dash();
					//player.getWorld().playSound(player, player.getBlockPos(), ModSounds.DASH_ROAR_EVENT, SoundCategory.PLAYERS);
				}
			});
		});

		ServerPlayNetworking.registerGlobalReceiver(LockInPayload.ID, (payload, context) -> {
			context.server().execute(() -> {
				if (context.server().getPlayerManager().getPlayer(payload.username()) != null) {
					PlayerEntity player = context.server().getPlayerManager().getPlayer(payload.username());
					//player.getWorld().playSound(player, player.getBlockPos(), ModSounds.SCREECH_EVENT, SoundCategory.PLAYERS);
				}
			});
		});

		ServerPlayNetworking.registerGlobalReceiver(FlashSendPayload.ID, (payload, context) -> {
			context.server().execute(() -> {
				ItemStack stack = context.player().getMainHandStack();
				if(stack.getItem() == ModItems.FLASHLIGHT){
					((FlashlightItem) stack.getItem()).flash(context.player().getWorld(), stack, context.player());
				}
				// add flash implementation (get player from ID then get location of player to decide who around should be affected)
			});
		});

		ServerPlayNetworking.registerGlobalReceiver(FlashlightActivatePayload.ID, (payload, context) -> {
			context.server().execute(() -> {
				ItemStack stack = context.player().getMainHandStack();
				if(stack.getItem() == ModItems.FLASHLIGHT){
					stack.set(ModItems.FLASH_ACTIVE, payload.state());
				}
			});
		});

		ServerPlayNetworking.registerGlobalReceiver(RakeJumpPayload.ID, (payload, context) -> {
			context.server().execute(() -> {
				if (context.server().getPlayerManager().getPlayer(context.player().getNameForScoreboard()) != null) {
					PlayerEntity player = context.server().getPlayerManager().getPlayer(context.player().getNameForScoreboard());
					InSilenceEssentials p = (InSilenceEssentials) player;
					p.jumpCooldown();
				}
			});
		});

		ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
			ServerPlayNetworking.send(handler.getPlayer(), new RakeListReceivePayload(RakeManager.getRakeManager().getList()));
		});
	}
}