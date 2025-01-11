package net.hujoe.insilence.mixin;

import net.hujoe.insilence.server.RakeManager;
import net.minecraft.block.entity.LockableContainerBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LockableContainerBlockEntity.class)
public class LockableContainerBlockEntityMixin {

    @Inject(method = "canPlayerUse", at = @At("HEAD"), cancellable = true)
    public void ignoreFreaks(PlayerEntity player, CallbackInfoReturnable<Boolean> cir){
        if (RakeManager.getRakeManager().isMouse(player.getNameForScoreboard()) || RakeManager.getRakeManager().isRake(player.getNameForScoreboard())){
            cir.setReturnValue(false);
        }
    }
}
