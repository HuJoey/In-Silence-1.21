package net.hujoe.insilence.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.hujoe.insilence.InSilenceEssentials;
import net.hujoe.insilence.Insilence;
import net.hujoe.insilence.InsilenceClient;
import net.hujoe.insilence.client.ClientRakeManager;
import net.hujoe.insilence.network.payloads.RakeAttackSendPayload;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

    @Shadow public abstract void sendMessage(Text message, boolean overlay);

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

    @Inject(method = "tick", at = @At("TAIL"))
    public void tick(CallbackInfo ci){
        InSilenceEssentials player = (InSilenceEssentials) this;
        if (player.isCaught()){
            MutableText msg = Text.literal("Press [" + InsilenceClient.flashKeyBinding.getTranslationKey() + "] to flash!");
            Style msgStyle = msg.getStyle();
            Style style = msgStyle.withColor(Formatting.DARK_RED);
            msg.setStyle(style);
            sendMessage(msg, true);
        }
    }
}
