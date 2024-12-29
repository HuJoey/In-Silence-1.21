package net.hujoe.insilence.commands;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.hujoe.insilence.entity.ModEntities;
import net.hujoe.insilence.entity.custom.LocationEntity;
import net.hujoe.insilence.entity.custom.RakeEntity;
import net.hujoe.insilence.entity.custom.SoundEntity;
import net.hujoe.insilence.server.RakeManager;
import net.hujoe.insilence.sound.ModSounds;
import net.minecraft.client.network.ClientCommandSource;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;
import java.util.function.Predicate;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class ModCommands {
    public static void registerModCommands(){
        CommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess, environment) -> dispatcher.register(literal("rake")
                .requires(serverCommandSource -> serverCommandSource.hasPermissionLevel(2))
                .executes(commandContext -> {
                    ServerCommandSource source = commandContext.getSource();
                    Entity sender = source.getEntity();
                    if (sender != null) {
                        RakeManager.getRakeManager().toggleRakeUser(sender.getNameForScoreboard(), sender.getWorld());
                    }
                    commandContext.getSource().sendFeedback(() -> Text.literal("You Toggled Rake"), false);
                    return 1;
                }))));

        CommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess, environment) -> dispatcher.register(literal("mouse")
                .requires(serverCommandSource -> serverCommandSource.hasPermissionLevel(2))
                .executes(commandContext -> {
                    ServerCommandSource source = commandContext.getSource();
                    Entity sender = source.getEntity();
                    if (sender != null) {
                        RakeManager.getRakeManager().toggleMouseUser(sender.getNameForScoreboard(), sender.getWorld());
                    }
                    commandContext.getSource().sendFeedback(() -> Text.literal("You Toggled Mouse"), false);
                    return 1;
                }))));

        ClientCommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess) -> dispatcher.register(ClientCommandManager.literal("thumbnail")
                .requires(serverCommandSource -> serverCommandSource.hasPermissionLevel(2))
                .executes(commandContext -> {
                    FabricClientCommandSource source = commandContext.getSource();

                    BlockPos pos = source.getPlayer().getBlockPos();
                    World world = source.getWorld();
                    Vec3d vec3d = Vec3d.ofCenter(pos);
                    Predicate<RakeEntity> close = (rake) -> {
                        return rake.getPos().isInRange(vec3d, 5);
                    };
                    List<RakeEntity> closeEntities = world.getEntitiesByClass(RakeEntity.class, new Box(pos.getX() - 5, pos.getY() - 5, pos.getZ() - 5, pos.getX() + 5, pos.getY() + 5, pos.getZ() + 5), close.and(RakeEntity::isAlive).and(EntityPredicates.EXCEPT_SPECTATOR));
                    for (RakeEntity r : closeEntities){
                        r.getAnimatableInstanceCache().getManagerForId(r.getId()).tryTriggerAnimation("controller", "thumbnail");
                    }
                    return 1;
                }))));

        CommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess, environment) -> dispatcher.register(literal("playbirdsound")
                .requires(serverCommandSource -> serverCommandSource.hasPermissionLevel(2))
                .executes(commandContext -> {
                    ServerCommandSource source = commandContext.getSource();
                    Vec3d pos = source.getPosition();
                    World world = source.getWorld();

                    double rand = Math.random(); // limited range of 8.5 blocks in order to hear this sound
                    if (rand < 0.25) {
                        world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), ModSounds.CROW_01_EVENT, SoundCategory.AMBIENT, 0.25F, 1);
                    } else if (rand < 0.5){
                        world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), ModSounds.CROW_02_EVENT, SoundCategory.AMBIENT, 0.25F, 1);
                    } else if (rand < 0.75){
                        world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), ModSounds.OWL_HOOT_01_EVENT, SoundCategory.AMBIENT, 0.25F, 1);
                    } else {
                        world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), ModSounds.OWL_HOOT_02_EVENT, SoundCategory.AMBIENT, 0.25F, 1);

                    }

                    SoundEntity soundEntity = new SoundEntity(ModEntities.SOUNDENTITY, world);
                    soundEntity.setStrength(50);
                    soundEntity.setPosition(pos);
                    world.spawnEntity(soundEntity);
                    return 1;
                }))));

        CommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess, environment) -> dispatcher.register(literal("playhumansound")
                .requires(serverCommandSource -> serverCommandSource.hasPermissionLevel(2))
                .executes(commandContext -> {
                    ServerCommandSource source = commandContext.getSource();
                    Entity entity = source.getEntity();

                    if (entity instanceof PlayerEntity){
                        ((PlayerEntity) entity).playSoundToPlayer(ModSounds.YOU_ARE_HUMAN_EVENT, SoundCategory.AMBIENT, 0.7F, 1);
                    }
                    return 1;
                }))));

        CommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess, environment) -> dispatcher.register(literal("playcreaturesound")
                .requires(serverCommandSource -> serverCommandSource.hasPermissionLevel(2))
                .executes(commandContext -> {
                    ServerCommandSource source = commandContext.getSource();
                    Entity entity = source.getEntity();

                    if (entity instanceof PlayerEntity){
                        ((PlayerEntity) entity).playSoundToPlayer(ModSounds.YOU_ARE_CREATURE_EVENT, SoundCategory.AMBIENT, 0.6F, 1);
                    }
                    return 1;
                }))));

        CommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess, environment) -> dispatcher.register(literal("playtension")
                .requires(serverCommandSource -> serverCommandSource.hasPermissionLevel(2))
                .executes(commandContext -> {
                    ServerCommandSource source = commandContext.getSource();
                    Entity entity = source.getEntity();

                    if (entity instanceof PlayerEntity){
                        ((PlayerEntity) entity).playSoundToPlayer(ModSounds.TENSION_2_EVENT, SoundCategory.AMBIENT, 0.5F, 1);
                    }
                    return 1;
                }))));

        CommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess, environment) -> dispatcher.register(literal("playendsound")
                .requires(serverCommandSource -> serverCommandSource.hasPermissionLevel(2))
                .executes(commandContext -> {
                    ServerCommandSource source = commandContext.getSource();
                    Entity entity = source.getEntity();

                    if (entity instanceof PlayerEntity){
                        ((PlayerEntity) entity).playSoundToPlayer(ModSounds.BLOODY_NIGHTMARE_EVENT, SoundCategory.AMBIENT, 0.6F, 1);
                    }
                    return 1;
                }))));
    }
}
