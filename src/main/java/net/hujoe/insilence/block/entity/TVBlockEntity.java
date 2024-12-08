package net.hujoe.insilence.block.entity;

import net.hujoe.insilence.block.custom.TVBlock;
import net.hujoe.insilence.entity.ModEntities;
import net.hujoe.insilence.entity.custom.SoundEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TVBlockEntity extends BlockEntity {
    public int ticksSincePing = 20;
    public int ticksSinceStage = 5;

    public TVBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.TV_BLOCK_ENTITY, pos, state);
    }

    public void incrementTicks(){
        ticksSincePing--;
        ticksSinceStage--;
    }
    public void resetPingTicks(){
        ticksSincePing = 20;
    }
    public void resetStageTicks(){
        ticksSinceStage = 5;
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
        }
    }
}
