package net.hujoe.insilence.block.custom;

import com.mojang.serialization.MapCodec;
import net.hujoe.insilence.Insilence;
import net.hujoe.insilence.item.ModItems;
import net.minecraft.block.*;
import net.minecraft.block.enums.BlockFace;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class BatteryBlock extends HorizontalFacingBlock {
    public static final VoxelShape X_SHAPE = Block.createCuboidShape(4.0, 0.0, 7.0, 12.0, 2.0, 9.0);
    public static final VoxelShape Z_SHAPE = Block.createCuboidShape(7.0, 0.0, 4.0, 9.0, 2.0, 12.0);
    public BatteryBlock(Settings settings) {
        super(settings);
        this.setDefaultState((this.stateManager.getDefaultState()).with(FACING, Direction.NORTH));
    }

    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction direction = state.get(FACING);
        if (direction == Direction.NORTH || direction == Direction.SOUTH){
            return X_SHAPE;
        } else {
            return Z_SHAPE;
        }
    }

    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        PlayerInventory inv = player.getInventory();
        if (inv.contains(TagKey.of(RegistryKeys.ITEM, Identifier.of(Insilence.MOD_ID, "flashlight")))){
            for (int i = 0; i < 36; i++){
                ItemStack stack = inv.getStack(i);
                if (stack.isOf(ModItems.FLASHLIGHT) && stack.get(ModItems.FLASH_STAGE) != null && stack.get(ModItems.FLASH_STAGE) < 3){
                    stack.set(ModItems.FLASH_STAGE, stack.get(ModItems.FLASH_STAGE) + 1);
                    if (!world.isClient) {
                        world.setBlockState(pos, Blocks.AIR.getDefaultState(), Block.NOTIFY_NEIGHBORS);
                    }
                    break;
                }
            }
        }
        return ActionResult.success(world.isClient);
    }

    @Override
    protected MapCodec<? extends HorizontalFacingBlock> getCodec() {
        return createCodec(BatteryBlock::new);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder){
        builder.add(FACING);
    }
}
