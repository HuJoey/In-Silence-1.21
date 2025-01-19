package net.hujoe.insilence.mixin;

import net.hujoe.insilence.FlashActivator;
import net.hujoe.insilence.Insilence;
import net.hujoe.insilence.InsilenceClient;
import net.hujoe.insilence.block.ModBlocks;
import net.hujoe.insilence.block.custom.FlashlightLightBlock;
import net.hujoe.insilence.client.BlindnessHandler;
import net.hujoe.insilence.client.ClientRakeManager;
import net.hujoe.insilence.sound.ModSounds;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LightBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
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
    @Shadow @Final private ClientWorld.Properties clientWorldProperties;
    @Unique
    BlindnessHandler blindness = InsilenceClient.getBlindnessHandler();

    @Inject(method = "tick", at = @At("TAIL"))
    public void tick(BooleanSupplier shouldKeepTicking, CallbackInfo ci){
        blindness.tick();

        FlashActivator renderer = (FlashActivator) client.gameRenderer;
        renderer.incrementTicks();

        if (ClientRakeManager.getRakeManager().isRake(client.player.getNameForScoreboard())){
            placeRakeLight();
        } else if (ClientRakeManager.getRakeManager().isMouse(client.player.getNameForScoreboard())) {
            placeMouseLight();
        }
    }

    public void placeRakeLight(){
        BlockPos down = client.player.getBlockPos();
        BlockPos center = down.up();
        BlockPos up = down.up(2);

        ClientWorld clientWorld = (ClientWorld) (Object) this;
        BlockState block = ModBlocks.FLASHLIGHT_LIGHT.getDefaultState();
        BlockState light = block.with(FlashlightLightBlock.LEVEL_15, 6);

        if (clientWorld.getBlockState(center).isAir()){
            clientWorld.setBlockState(center, light);
        }
        if (clientWorld.getBlockState(up).isAir()){
            clientWorld.setBlockState(up, light);
        }
        if (clientWorld.getBlockState(down).isAir()){
            clientWorld.setBlockState(down, light);
        }
    }

    public void placeMouseLight(){
        BlockPos down = client.player.getBlockPos();

        ClientWorld clientWorld = (ClientWorld) (Object) this;
        BlockState block = ModBlocks.FLASHLIGHT_LIGHT.getDefaultState();
        BlockState light = block.with(FlashlightLightBlock.LEVEL_15, 6);

        if (clientWorld.getBlockState(down).isAir()){
            clientWorld.setBlockState(down, light);
        } else if (clientWorld.getBlockState(down.up()).isAir()) {
            clientWorld.setBlockState(down.up(), light);
        } else {
            light = block.with(FlashlightLightBlock.LEVEL_15, 5);
            if (clientWorld.getBlockState(down.west()).isAir()) {
                clientWorld.setBlockState(down.west(), light);
            }
            if (clientWorld.getBlockState(down.south()).isAir()) {
                clientWorld.setBlockState(down.south(), light);
            }
            if (clientWorld.getBlockState(down.north()).isAir()) {
                clientWorld.setBlockState(down.north(), light);
            }
            if (clientWorld.getBlockState(down.east()).isAir()) {
                clientWorld.setBlockState(down.east(), light);
            }
        }
    }
}
