package net.hujoe.insilence.block.entity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FlashlightLightBlockEntity extends BlockEntity {
    public int lifespan = 2;

    public FlashlightLightBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FLASHLIGHT_LIGHT_BLOCK_ENTITY, pos, state);
    }

    public void tickLife(){
        lifespan--;
    }

    public void restartLife(){
        lifespan = 2;
    }

    public static void tick(World world, BlockPos pos, BlockState state, FlashlightLightBlockEntity blockEntity) {
        if(blockEntity.lifespan > 0){
            blockEntity.tickLife();
        } else {
            world.setBlockState(pos, Blocks.AIR.getDefaultState(), Block.NOTIFY_NEIGHBORS);
        }
    }
}
