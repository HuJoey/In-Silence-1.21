package net.hujoe.insilence.mixin;

import net.hujoe.insilence.entity.ModEntities;
import net.hujoe.insilence.entity.custom.SoundEntity;
import net.minecraft.block.AbstractPressurePlateBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractPressurePlateBlock.class)
public class PressurePlateBlockMixin {

    @Inject(method = "onStateReplaced", at = @At("HEAD"))
    protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved, CallbackInfo ci) {
        if (!world.isClient){
            SoundEntity soundEntity = new SoundEntity(ModEntities.SOUNDENTITY, world);
            soundEntity.setStrength(20);
            soundEntity.setPosition(new Vec3d(pos.getX(), pos.getY(), pos.getZ()));
            world.spawnEntity(soundEntity);
        }
    }
}
