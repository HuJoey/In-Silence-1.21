package net.hujoe.insilence.block.custom;

import com.mojang.serialization.MapCodec;
import net.hujoe.insilence.block.entity.FlashlightLightBlockEntity;
import net.hujoe.insilence.block.entity.ModBlockEntities;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.function.ToIntFunction;

public class FlashlightLightBlock extends BlockWithEntity implements BlockEntityProvider {
    public static final IntProperty LEVEL_15;
    public static final ToIntFunction<BlockState> STATE_TO_LUMINANCE;
    public FlashlightLightBlock(Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)((BlockState)this.stateManager.getDefaultState()).with(LEVEL_15, 15)));
    }

    @Override
    protected MapCodec<? extends FlashlightLightBlock> getCodec() {
        return createCodec(FlashlightLightBlock::new);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new FlashlightLightBlockEntity(pos, state);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state){
        return BlockRenderType.INVISIBLE;
    }

    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LEVEL_15);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, ModBlockEntities.FLASHLIGHT_LIGHT_BLOCK_ENTITY, FlashlightLightBlockEntity::tick);
    }

    static {
        LEVEL_15 = Properties.LEVEL_15;
        STATE_TO_LUMINANCE = (state) -> {
            return (Integer)state.get(LEVEL_15);
        };
    }
}
