package net.hujoe.insilence.mixin;

import net.hujoe.insilence.InsilenceClient;
import net.hujoe.insilence.client.BlindnessHandler;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BooleanSupplier;

@Mixin(ClientWorld.class)
public class ClientWorldMixin {

    @Unique
    BlindnessHandler blindness = InsilenceClient.getBlindnessHandler();

    @Inject(method = "tick", at = @At("TAIL"))
    public void tick(BooleanSupplier shouldKeepTicking, CallbackInfo ci){
        blindness.tick();
    }
}
