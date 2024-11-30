package net.hujoe.insilence.mixin;

import net.hujoe.insilence.InsilenceClient;
import net.hujoe.insilence.client.BlindnessHandler;
import net.hujoe.insilence.server.RakeManager;
import net.hujoe.insilence.sound.ModSounds;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

@Mixin(ServerWorld.class)
public abstract class ServerWorldMixin {

    @Inject(method = "tick", at = @At("TAIL"))
    public void tick(BooleanSupplier shouldKeepTicking, CallbackInfo ci){
        double rand = Math.random();
        if (rand < 0.3) {
            if (((ServerWorld) (Object) this).isRaining()) {
                List<ServerPlayerEntity> online = ((ServerWorld) (Object) this).getPlayers();
                ArrayList<String> users = RakeManager.getRakeManager().getList();
                for (ServerPlayerEntity player : online) {
                    if (users.contains(player.getNameForScoreboard())) {
                        player.playSoundToPlayer(ModSounds.SIGNAL_EVENT, SoundCategory.AMBIENT, (float) (rand * 3), 1);
                    }
                }
            }
        }
    }
}
