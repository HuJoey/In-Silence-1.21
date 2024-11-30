package net.hujoe.insilence.mixin;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.hujoe.insilence.InSilenceEssentials;
import net.hujoe.insilence.client.ClientRakeManager;
import net.hujoe.insilence.network.payloads.RakeAttackSendPayload;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.hit.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class IJustNeedToSendAClientPacketMixin {
    @Inject(method="baseTick", at = @At("TAIL"))
    public void baseTick(CallbackInfo ci) {
        InSilenceEssentials playerES = (InSilenceEssentials) this;
        if (playerES.isDashing()) {
            PlayerEntity player = (PlayerEntity) (Object) this;
            HitResult result = ((LivingEntity) (Object) this).raycast(2.5, 0, false);
            PlayerEntity target = ((LivingEntity) (Object) this).getWorld().getClosestPlayer(result.getPos().x, result.getPos().y, result.getPos().z, 2, false);
            if (target != null && target.getId() != ((LivingEntity) (Object) this).getId()) {
                if (target.isAttackable() && ClientRakeManager.getRakeManager().isRake(((LivingEntity) (Object) this).getNameForScoreboard())) {
                    if (!playerES.isAttacking() && !playerES.isStunned()) {
                        if (target instanceof PlayerEntity) {
                            if (!ClientRakeManager.getRakeManager().isRake(target.getNameForScoreboard()) && !ClientRakeManager.getRakeManager().isMouse(target.getNameForScoreboard())) {
                                ClientPlayNetworking.send(new RakeAttackSendPayload(((LivingEntity) (Object) this).getId(), target.getId()));
                            }
                        }
                    }
                }
            }
        }
    }
}
