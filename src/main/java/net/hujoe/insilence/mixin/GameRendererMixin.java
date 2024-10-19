package net.hujoe.insilence.mixin;

import net.hujoe.insilence.server.RakeManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {

    @Shadow public abstract Camera getCamera();

    @Shadow public abstract MinecraftClient getClient();

    /*
    @Inject(method = "tick", at = @At("TAIL"))
    public void tick(CallbackInfo ci){
        if (this.getClient().player != null && RakeManager.getRakeManager().isRake(this.getClient().player.getNameForScoreboard())){
            Entity temp = this.getCamera().getFocusedEntity();


        }
    }
     */
}
