package net.hujoe.insilence.item.custom;

import net.hujoe.insilence.Insilence;
import net.hujoe.insilence.block.ModBlocks;
import net.hujoe.insilence.block.entity.FlashlightLightBlockEntity;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

public class FlashlightItem extends Item {
    private boolean active;
    private int stage;
    public FlashlightItem(Settings settings) {
        super(settings);
        active = false;
        stage = 3;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int i, boolean bl) {
        switch (stage){
            case 1:
                stack.setDamage(88);
                break;
            case 2:
                stack.setDamage(40);
                break;
            case 3:
                stack.setDamage(0);
        }

        if (active){
            HitResult result = entity.raycast(5, MinecraftClient.getInstance().getRenderTickCounter().getTickDelta(true), false);
            BlockPos pos = new BlockPos((int) result.getPos().getX(),(int) result.getPos().getY(),(int) result.getPos().getZ() -1);
            if (entity.getHorizontalFacing() == Direction.EAST){
                pos = pos.add(-1, 0 ,0);
                Insilence.LOGGER.info("happening");
            } else if (entity.getHorizontalFacing() == Direction.NORTH){
                pos = pos.add(0, 0, -1);
            }

            if (world.getBlockState(pos) == Blocks.AIR.getDefaultState()){
                world.setBlockState(pos, ModBlocks.FLASHLIGHT_LIGHT.getDefaultState());
            } else if (world.getBlockState(pos) == ModBlocks.FLASHLIGHT_LIGHT.getDefaultState()) {
                FlashlightLightBlockEntity blockEntity = (FlashlightLightBlockEntity) world.getBlockEntity(pos);
                if (blockEntity != null) {
                    blockEntity.restartLife();
                }
            }
        }
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (world.isClient) {
            active = !active;
        }
        user.getItemCooldownManager().set(this, 10);
        return TypedActionResult.pass(stack);
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return true;
    }
}
