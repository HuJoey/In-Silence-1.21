package net.hujoe.insilence.block.custom;

import com.mojang.serialization.MapCodec;
import net.hujoe.insilence.block.entity.ModBlockEntities;
import net.hujoe.insilence.block.entity.TVBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class TVBlock extends BlockWithEntity {
    public static final DirectionProperty FACING;
    public static final BooleanProperty LIT;
    public static final BooleanProperty ACTIVE;
    public static final IntProperty STAGE;
    public final VoxelShape X_SHAPE = Block.createCuboidShape(2.0, 0.0, 6.0, 14.0, 8.0, 10.0);
    public final VoxelShape Z_SHAPE = Block.createCuboidShape(6.0, 0.0, 2.0, 10.0, 8.0, 14.0);
    public TVBlock(Settings settings) {
        super(settings);
        this.setDefaultState((BlockState) ((BlockState) ((BlockState) ((BlockState) ((BlockState)this.stateManager.getDefaultState()).with(FACING, Direction.NORTH)).with(ACTIVE, false)).with(STAGE, 1)).with(LIT, false));
    }

    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction direction = state.get(FACING);
        if (direction == Direction.NORTH || direction == Direction.SOUTH){
            return X_SHAPE;
        } else {
            return Z_SHAPE;
        }
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state){
        return BlockRenderType.MODEL;
    }

    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        boolean active = state.get(ACTIVE);
        world.setBlockState(pos, ((BlockState) (state.with(ACTIVE, !active)).with(LIT, !active)));

        return ActionResult.success(world.isClient);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new TVBlockEntity(pos, state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, ModBlockEntities.TV_BLOCK_ENTITY, TVBlockEntity::tick);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return createCodec(TVBlock::new);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder){
        builder.add(FACING);
        builder.add(ACTIVE);
        builder.add(STAGE);
        builder.add(LIT);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return super.getPlacementState(ctx).with(Properties.HORIZONTAL_FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    static {
        FACING = Properties.HORIZONTAL_FACING;
        LIT = Properties.LIT;
        ACTIVE = BooleanProperty.of("active");
        STAGE = IntProperty.of("stage", 1, 4);
    }
}
