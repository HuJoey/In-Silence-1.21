package net.hujoe.insilence.mixin;

import net.hujoe.insilence.InSilenceEssentials;
import net.hujoe.insilence.Insilence;
import net.hujoe.insilence.client.ClientRakeManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

    @Inject(method = "attack", at = @At("HEAD"), cancellable = true)
    public void attack(Entity target, CallbackInfo ci){
        if (target.isAttackable() && ClientRakeManager.getRakeManager().isRake(((PlayerEntity) (Object)this).getNameForScoreboard())) {
            InSilenceEssentials rake = (InSilenceEssentials) this;
            if (!rake.isAttacking() && !rake.isStunned()) {
                if (target instanceof PlayerEntity) {
                    if (!ClientRakeManager.getRakeManager().isRake(target.getNameForScoreboard()) && !ClientRakeManager.getRakeManager().isMouse(target.getNameForScoreboard())) {
                        rake.triggerJumpscare(target.getId());
                        ci.cancel();
                    }
                }
            }
        }
    }
}
