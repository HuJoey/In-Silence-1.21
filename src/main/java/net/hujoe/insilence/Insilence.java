package net.hujoe.insilence;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.hujoe.insilence.block.ModBlocks;
import net.hujoe.insilence.block.entity.ModBlockEntities;
import net.hujoe.insilence.commands.ModCommands;
import net.hujoe.insilence.entity.ModEntities;
import net.hujoe.insilence.entity.custom.SoundEntity;
import net.hujoe.insilence.item.ModItems;
import net.hujoe.insilence.item.custom.FlashlightItem;
import net.hujoe.insilence.network.payloads.*;
import net.hujoe.insilence.server.RakeManager;
import net.hujoe.insilence.sound.ModSounds;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.particle.FireworksSparkParticle;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.ClearTitleS2CPacket;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.Predicate;

import static net.minecraft.block.Block.getRawIdFromState;
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
		ModCommands.registerModCommands();

		PayloadTypeRegistry.playS2C().register(RakeUpdatePayload.ID, RakeUpdatePayload.CODEC);
		PayloadTypeRegistry.playS2C().register(MouseUpdatePayload.ID, MouseUpdatePayload.CODEC);
		PayloadTypeRegistry.playS2C().register(RakeListReceivePayload.ID, RakeListReceivePayload.CODEC);
		PayloadTypeRegistry.playS2C().register(MouseListReceivePayload.ID, MouseListReceivePayload.CODEC);
		PayloadTypeRegistry.playC2S().register(SignalSoundPayload.ID, SignalSoundPayload.CODEC);
		PayloadTypeRegistry.playS2C().register(VolumeUpdatePayload.ID, VolumeUpdatePayload.CODEC);
		PayloadTypeRegistry.playS2C().register(FlashReceivePayload.ID, FlashReceivePayload.CODEC);
		PayloadTypeRegistry.playC2S().register(FlashSendPayload.ID, FlashSendPayload.CODEC);
		PayloadTypeRegistry.playC2S().register(DashPayload.ID, DashPayload.CODEC);
		PayloadTypeRegistry.playS2C().register(DashClientPayload.ID, DashClientPayload.CODEC);
		PayloadTypeRegistry.playC2S().register(LockInPayload.ID, LockInPayload.CODEC);
		PayloadTypeRegistry.playC2S().register(RakeJumpPayload.ID, RakeJumpPayload.CODEC);
		PayloadTypeRegistry.playS2C().register(LightRestartPayload.ID, LightRestartPayload.CODEC);
		PayloadTypeRegistry.playC2S().register(LightPlacePayload.ID, LightPlacePayload.CODEC);
		PayloadTypeRegistry.playS2C().register(RakeAttackReceivePayload.ID, RakeAttackReceivePayload.CODEC);
		PayloadTypeRegistry.playC2S().register(RakeAttackSendPayload.ID, RakeAttackSendPayload.CODEC);
		PayloadTypeRegistry.playC2S().register(SqueakPayload.ID, SqueakPayload.CODEC);

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
					ServerPlayerEntity player = context.server().getPlayerManager().getPlayer(payload.username());
					InSilenceEssentials p = (InSilenceEssentials) player;
					p.dash();
					if (Math.random() > 0.5) {
						player.getWorld().playSound(player, player.getBlockPos(), ModSounds.SPRINT_EVENT_1, SoundCategory.PLAYERS);
						player.playSoundToPlayer(ModSounds.SPRINT_EVENT_1, SoundCategory.AMBIENT, 0.3F , 1);
					} else {
						player.getWorld().playSound(player, player.getBlockPos(), ModSounds.SPRINT_EVENT_2, SoundCategory.PLAYERS);
						player.playSoundToPlayer(ModSounds.SPRINT_EVENT_2, SoundCategory.AMBIENT, 0.3F, 1);
					}
					for (ServerPlayerEntity sp : PlayerLookup.world(context.player().getServerWorld())) {
						ServerPlayNetworking.send(sp, new DashClientPayload(player.getId()));
					}
				}
			});
		});

		ServerPlayNetworking.registerGlobalReceiver(LockInPayload.ID, (payload, context) -> {
			context.server().execute(() -> {
				if (context.server().getPlayerManager().getPlayer(payload.username()) != null) {
					PlayerEntity player = context.server().getPlayerManager().getPlayer(payload.username());
					if (Math.random() > 0.5) {
						player.getWorld().playSound(player, player.getBlockPos(), ModSounds.EYE_EVENT_1, SoundCategory.PLAYERS);
						player.playSoundToPlayer(ModSounds.EYE_EVENT_1, SoundCategory.AMBIENT, 0.3F , 1);
					} else {
						player.getWorld().playSound(player, player.getBlockPos(), ModSounds.EYE_EVENT_2, SoundCategory.PLAYERS);
						player.playSoundToPlayer(ModSounds.EYE_EVENT_2, SoundCategory.AMBIENT, 0.3F, 1);
					}
				}
			});
		});

		ServerPlayNetworking.registerGlobalReceiver(FlashSendPayload.ID, (payload, context) -> {
			context.server().execute(() -> {
				PlayerEntity player = context.server().getPlayerManager().getPlayer(context.player().getNameForScoreboard());
				PlayerInventory inv = player.getInventory();
				ItemStack stack = null;
				for (int i = 0; i < 36; i++){
					stack = inv.getStack(i);
					if (stack.isOf(ModItems.FLASHLIGHT) && stack.get(ModItems.FLASH_STAGE) != null && stack.get(ModItems.FLASH_STAGE) > 1){
						break;
					}
				}
				if(stack.getItem() == ModItems.FLASHLIGHT){
					stack.set(ModItems.FLASH_STAGE, stack.get(ModItems.FLASH_STAGE) - 1);
					switch (stack.get(ModItems.FLASH_STAGE)) {
						case 1:
							stack.setDamage(88);
							break;
						case 2:
							stack.setDamage(40);
							break;
						case 3:
							stack.setDamage(0);
					}
					player.getWorld().playSound(null, player.getBlockPos(), ModSounds.FLASHBANG_EVENT, SoundCategory.PLAYERS, 0.5F, 1);
				}

				if (((InSilenceEssentials) player).isCaught()){
					((InSilenceEssentials) player).cancelAttack();
					((InSilenceEssentials) player.getWorld().getEntityById((((InSilenceEssentials) player).getAttackerId()))).cancelAttack();

					for (ServerPlayerEntity sp : PlayerLookup.world(context.player().getServerWorld())) {
						if (sp.distanceTo(player) < 10) {
							ServerPlayNetworking.send(sp, new FlashReceivePayload(((InSilenceEssentials) player).getAttackerId(), player.getId(), true));
							sp.playSoundToPlayer(ModSounds.EAR_RINGING_EVENT, SoundCategory.PLAYERS, 1, 1);
						} else {
							ServerPlayNetworking.send(sp, new FlashReceivePayload(((InSilenceEssentials) player).getAttackerId(), player.getId(), false));
						}
					}
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

		ServerPlayNetworking.registerGlobalReceiver(LightPlacePayload.ID, (payload, context) -> {
			context.server().execute(() -> {
				BlockPos pos = new BlockPos(payload.x(), payload.y(), payload.z());
				World world = context.player().getWorld();
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (world.getBlockState(pos) == Blocks.AIR.getDefaultState()) {
					world.setBlockState(pos, ModBlocks.FLASHLIGHT_LIGHT.getDefaultState());
					world.syncWorldEvent(null, 2001, pos, getRawIdFromState(world.getBlockState(pos)));
				} else if (blockEntity != null && blockEntity.getType() == ModBlockEntities.FLASHLIGHT_LIGHT_BLOCK_ENTITY) {
					for (ServerPlayerEntity player : PlayerLookup.world((ServerWorld) world)) {
						ServerPlayNetworking.send(player, new LightRestartPayload(pos.getX(), pos.getY(), pos.getZ()));
					}
				}
			});
		});

		ServerPlayNetworking.registerGlobalReceiver(RakeAttackSendPayload.ID, (payload, context) -> {
			context.server().execute(() -> {
				if (context.player().getEntityWorld().getEntityById(payload.attackerId()) != null) {
					PlayerEntity player = (PlayerEntity) context.player().getEntityWorld().getEntityById(payload.attackerId());
					InSilenceEssentials p = (InSilenceEssentials) player;
					p.triggerJumpscare();
					HitResult result = player.raycast(1.3, 0, false);
					if (context.player().getEntityWorld().getEntityById(payload.targetId()) != null) {
						PlayerEntity target = (PlayerEntity) context.player().getEntityWorld().getEntityById(payload.targetId());
						((InSilenceEssentials) target).triggerCaught(player.getYaw(), new Vec3d(result.getPos().x, player.getY(), result.getPos().z));
						((InSilenceEssentials) target).setAttackerId(payload.attackerId());
					}
					player.getWorld().playSound(player, player.getBlockPos(), ModSounds.CATCH_EVENT, SoundCategory.PLAYERS);
					player.playSoundToPlayer(ModSounds.CATCH_EVENT, SoundCategory.AMBIENT, 0.5F, 1);
					for (ServerPlayerEntity sp : PlayerLookup.world(context.player().getServerWorld())) {
						ServerPlayNetworking.send(sp, new RakeAttackReceivePayload(payload.attackerId(), payload.targetId()));
					}
				}
			});
		});

		ServerPlayNetworking.registerGlobalReceiver(SqueakPayload.ID, (payload, context) -> {
			context.server().execute(() -> {
				if (context.server().getPlayerManager().getPlayer(payload.username()) != null) {
					PlayerEntity player = context.server().getPlayerManager().getPlayer(payload.username());
					double rand = Math.random();
					if (rand > 0.33) {
						player.getWorld().playSound(player, player.getBlockPos(), ModSounds.RAT_SQUEAK_1_EVENT, SoundCategory.PLAYERS);
						player.playSoundToPlayer(ModSounds.RAT_SQUEAK_1_EVENT, SoundCategory.AMBIENT, 0.5F , 1);
					} else if (rand > 0.66){
						player.getWorld().playSound(player, player.getBlockPos(), ModSounds.RAT_SQUEAK_2_EVENT, SoundCategory.PLAYERS);
						player.playSoundToPlayer(ModSounds.RAT_SQUEAK_2_EVENT, SoundCategory.AMBIENT, 0.5F, 1);
					} else {
						player.getWorld().playSound(player, player.getBlockPos(), ModSounds.RAT_SQUEAK_3_EVENT, SoundCategory.PLAYERS);
						player.playSoundToPlayer(ModSounds.RAT_SQUEAK_3_EVENT, SoundCategory.AMBIENT, 0.5F, 1);
					}
					SoundEntity soundEntity = new SoundEntity(ModEntities.SOUNDENTITY, player.getWorld());
					soundEntity.setStrength(30);
					soundEntity.setPosition(player.getPos());
					player.getWorld().spawnEntity(soundEntity);
				}
			});
		});

		ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
			ServerPlayNetworking.send(handler.getPlayer(), new RakeListReceivePayload(RakeManager.getRakeManager().getRakeList()));
			ServerPlayNetworking.send(handler.getPlayer(), new MouseListReceivePayload(RakeManager.getRakeManager().getMouseList()));
		});
	}
}