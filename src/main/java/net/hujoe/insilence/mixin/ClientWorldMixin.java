package net.hujoe.insilence.mixin;

import net.hujoe.insilence.FlashActivator;
import net.hujoe.insilence.InsilenceClient;
import net.hujoe.insilence.client.BlindnessHandler;
import net.hujoe.insilence.sound.ModSounds;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.sound.SoundCategory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BooleanSupplier;

@Mixin(ClientWorld.class)
public class ClientWorldMixin {

    @Shadow @Final private MinecraftClient client;
    @Unique
    BlindnessHandler blindness = InsilenceClient.getBlindnessHandler();
    private int ticksSinceAmbiance = 0;

    @Inject(method = "tick", at = @At("TAIL"))
    public void tick(BooleanSupplier shouldKeepTicking, CallbackInfo ci){
        blindness.tick();

        FlashActivator renderer = (FlashActivator) client.gameRenderer;
        renderer.incrementTicks();
    }
}
