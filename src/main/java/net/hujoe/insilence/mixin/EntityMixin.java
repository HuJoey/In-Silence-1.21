package net.hujoe.insilence.mixin;

import net.hujoe.insilence.client.ClientRakeManager;
import net.hujoe.insilence.server.RakeManager;
import net.hujoe.insilence.sound.ModSounds;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin {

    @Shadow public abstract void playSound(SoundEvent sound, float volume, float pitch);

    @Inject(method="playStepSound", at = @At("HEAD"), cancellable = true)
    public void stepSound(BlockPos pos, BlockState state, CallbackInfo ci){
        Entity entity = (Entity) (Object) this;
        if (entity instanceof PlayerEntity){
            boolean rake = false;
            if (entity.getWorld().isClient){
                if (ClientRakeManager.getRakeManager().isRake(entity.getNameForScoreboard())){
                    rake = true;
                }
            } else {
                if (RakeManager.getRakeManager().isRake(entity.getNameForScoreboard())){
                    rake = true;
                }
            }

            if (rake){
                if (entity.isSprinting()){
                    playStep(0.6F);
                } else {
                    playStep(0.35F);
                }
                ci.cancel();
            }
        }
    }

    @Unique
    public void playStep(float vol){
        double rand = Math.random() * 8;
        if (rand < 1){
            this.playSound(ModSounds.FOOTSTEPS_01_EVENT, vol, 1F);
        } else if (rand < 2){
            this.playSound(ModSounds.FOOTSTEPS_02_EVENT, vol, 1F);
        } else if (rand < 3){
            this.playSound(ModSounds.FOOTSTEPS_03_EVENT, vol, 1F);
        } else if (rand < 4){
            this.playSound(ModSounds.FOOTSTEPS_04_EVENT, vol, 1F);
        } else if (rand < 5){
            this.playSound(ModSounds.FOOTSTEPS_05_EVENT, vol, 1F);
        } else if (rand < 6){
            this.playSound(ModSounds.FOOTSTEPS_06_EVENT, vol, 1F);
        } else if (rand < 7){
            this.playSound(ModSounds.FOOTSTEPS_07_EVENT, vol, 1F);
        } else {
            this.playSound(ModSounds.FOOTSTEPS_08_EVENT, vol, 1F);
        }
    }
}
