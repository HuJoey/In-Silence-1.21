package net.hujoe.insilence.block.entity;

import net.hujoe.insilence.Insilence;
import net.hujoe.insilence.block.custom.RadioBlock;
import net.hujoe.insilence.entity.ModEntities;
import net.hujoe.insilence.entity.custom.SoundEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class RadioBlockEntity extends BlockEntity {
    public int ticksSincePing = 20;

    public RadioBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.RADIO_BLOCK_ENTITY, pos, state);
    }

    public void incrementTicks(){
        ticksSincePing--;
    }
    public void resetTicks(){
        ticksSincePing = 20;
    }

    public static void tick(World world, BlockPos pos, BlockState state, RadioBlockEntity blockEntity) {
        if (state.get(RadioBlock.ACTIVE)) {
            if (blockEntity.ticksSincePing > 0) {
                blockEntity.incrementTicks();

            } else {
                SoundEntity soundEntity = new SoundEntity(ModEntities.SOUNDENTITY, world);
                soundEntity.setStrength(30);
                soundEntity.setPosition(pos.toCenterPos());
                world.spawnEntity(soundEntity);
                blockEntity.resetTicks();
            }
        }
    }
}
