package net.hujoe.insilence.block.entity;

import net.hujoe.insilence.Insilence;
import net.hujoe.insilence.block.custom.TVBlock;
import net.hujoe.insilence.entity.ModEntities;
import net.hujoe.insilence.entity.custom.SoundEntity;
import net.hujoe.insilence.sound.ModSounds;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TVBlockEntity extends BlockEntity {
    public int ticksSincePing = 20;
    public int ticksSinceStage = 5;
    public int ticksSinceSound = 1;

    public TVBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.TV_BLOCK_ENTITY, pos, state);
    }

    public void incrementTicks(){
        ticksSincePing--;
        ticksSinceStage--;
        ticksSinceSound--;
    }

    public void resetPingTicks(){
        ticksSincePing = 20;
    }
    public void resetStageTicks(){
        ticksSinceStage = 5;
    }
    public void resetSoundTicks(){
        ticksSinceSound = 55;
    }
    public void zeroSoundTicks(){
        ticksSinceSound = 1;
    }

    public static void tick(World world, BlockPos pos, BlockState state, TVBlockEntity blockEntity) {
        if (state.get(TVBlock.ACTIVE)) {
            if (blockEntity.ticksSincePing > 0) {
                blockEntity.incrementTicks();
            } else {
                SoundEntity soundEntity = new SoundEntity(ModEntities.SOUNDENTITY, world);
                soundEntity.setStrength(50);
                soundEntity.setPosition(pos.toCenterPos());
                world.spawnEntity(soundEntity);
                blockEntity.resetPingTicks();
            }

            if (blockEntity.ticksSinceStage == 0){
                int stage = state.get(TVBlock.STAGE);
                if (stage == 4){
                    stage = 1;
                } else {
                    stage++;
                }
                world.setBlockState(pos, state.with(TVBlock.STAGE, stage));
                blockEntity.resetStageTicks();
            }

            if (blockEntity.ticksSinceSound == 0){
                if (!world.isClient) {
                    world.playSound(null, pos, ModSounds.TV_STATIC_EVENT, SoundCategory.BLOCKS);
                }
                blockEntity.resetSoundTicks();
            }
        } else {
            blockEntity.zeroSoundTicks();
        }
    }
}
