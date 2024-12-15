package net.hujoe.insilence.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.hujoe.insilence.InSilenceEssentials;
import net.hujoe.insilence.Insilence;
import net.hujoe.insilence.InsilenceClient;
import net.hujoe.insilence.client.ClientRakeManager;
import net.hujoe.insilence.network.payloads.RakeAttackSendPayload;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.ClearTitleS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.hujoe.insilence.sound.ModSounds;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {
    private int ticksSinceLastBreath = 0;
    private int lastBreathSound = 6;

    @Inject(method = "tick", at = @At("HEAD"))
    public void tick(CallbackInfo ci){
        PlayerEntity player = (PlayerEntity) (Object) this;
        if(player.getWorld().isClient && ClientRakeManager.getRakeManager().isRake(player.getNameForScoreboard())) {
            InSilenceEssentials rake = (InSilenceEssentials) player;

            if (!rake.isAttacking() && !rake.isDashing() && !rake.isStunned()) {
                if (ticksSinceLastBreath == 0) {
                    playBreathingSound();
                    ticksSinceLastBreath = 100;
                } else {
                    ticksSinceLastBreath--;
                }
            }
        }
    }

    @Inject(method = "attack", at = @At("HEAD"), cancellable = true)
    public void attack(Entity target, CallbackInfo ci){
        if (target.isAttackable() && ClientRakeManager.getRakeManager().isRake(((PlayerEntity) (Object)this).getNameForScoreboard())) {
            InSilenceEssentials rake = (InSilenceEssentials) this;
            if (!rake.isAttacking() && !rake.isStunned()) {
                if (target instanceof PlayerEntity) {
                    if (!ClientRakeManager.getRakeManager().isRake(target.getNameForScoreboard()) && !ClientRakeManager.getRakeManager().isMouse(target.getNameForScoreboard())) {
                        if (target.getWorld().isClient) {
                            rake.triggerJumpscare(target.getId());
                            ClientPlayNetworking.send(new RakeAttackSendPayload(((PlayerEntity) (Object)this).getId(), target.getId()));
                        }
                        ci.cancel();
                    }
                }
            }
        }
    }

    @Unique
    private void playBreathingSound(){
        PlayerEntity player = (PlayerEntity) (Object) this;
        lastBreathSound++;
        if (lastBreathSound == 7){
            lastBreathSound = 1;
        }

        switch (lastBreathSound){
            case 1:
                player.playSoundToPlayer(ModSounds.BREATHING_01_EVENT, SoundCategory.PLAYERS, 0.5F, 1);
                break;
            case 2:
                player.playSoundToPlayer(ModSounds.BREATHING_02_EVENT, SoundCategory.PLAYERS, 0.5F, 1);
                break;
            case 3:
                player.playSoundToPlayer(ModSounds.BREATHING_03_EVENT, SoundCategory.PLAYERS, 0.5F, 1);
                break;
            case 4:
                player.playSoundToPlayer(ModSounds.BREATHING_04_EVENT, SoundCategory.PLAYERS, 0.5F, 1);
                break;
            case 5:
                player.playSoundToPlayer(ModSounds.BREATHING_05_EVENT, SoundCategory.PLAYERS, 0.5F, 1);
                break;
            case 6:
                player.playSoundToPlayer(ModSounds.BREATHING_06_EVENT, SoundCategory.PLAYERS, 0.5F, 1);
                break;
        }
    }
}
