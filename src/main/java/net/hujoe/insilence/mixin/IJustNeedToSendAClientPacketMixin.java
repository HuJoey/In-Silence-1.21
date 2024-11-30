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
        if (playerES.wasAttackFromDash()){
            if (playerES.getCaughtTarget() != null){
                ClientPlayNetworking.send(new RakeAttackSendPayload(((PlayerEntity) (Object) this).getId(), playerES.getCaughtTarget().getId()));
            }
        }
    }
}
