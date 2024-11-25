package net.hujoe.insilence.item.custom;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.hujoe.insilence.block.ModBlocks;
import net.hujoe.insilence.block.entity.FlashlightLightBlockEntity;
import net.hujoe.insilence.item.ModItems;
import net.hujoe.insilence.network.InsilenceNetworking;
import net.hujoe.insilence.network.payloads.FlashlightActivatePayload;
import net.hujoe.insilence.network.payloads.LightRestartPayload;
import net.hujoe.insilence.network.payloads.RakeUpdatePayload;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class FlashlightItem extends Item {
    public FlashlightItem(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int i, boolean bl) {
        if (!world.isClient){
        switch (stack.get(ModItems.FLASH_STAGE)){
            case 1:
                stack.setDamage(88);
                break;
            case 2:
                stack.setDamage(40);
                break;
            case 3:
                stack.setDamage(0);
        }

        if (stack.get(ModItems.FLASH_ACTIVE)) {
            HitResult result = entity.raycast(4, MinecraftClient.getInstance().getRenderTickCounter().getTickDelta(true), false);
            BlockPos pos = new BlockPos((int) result.getPos().getX(), (int) result.getPos().getY(), (int) result.getPos().getZ() - 1);
            if (entity.getHorizontalFacing() == Direction.EAST) {
                pos = pos.add(-1, 0, 0);
            } else if (entity.getHorizontalFacing() == Direction.NORTH) {
                pos = pos.add(0, 0, -1);
            }

            if (world.getBlockState(pos) == Blocks.AIR.getDefaultState()) {
                world.setBlockState(pos, ModBlocks.FLASHLIGHT_LIGHT.getDefaultState());
            } else if (world.getBlockEntity(pos) instanceof FlashlightLightBlockEntity blockEntity) {
                blockEntity.restartLife();
                for (ServerPlayerEntity player : PlayerLookup.world((ServerWorld) world)) {
                    ServerPlayNetworking.send(player, new LightRestartPayload(pos.getX(), pos.getY(), pos.getZ()));

                }
            }
        }
        }
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (world.isClient) {
            stack.set(ModItems.FLASH_ACTIVE, !stack.get(ModItems.FLASH_ACTIVE));
            ClientPlayNetworking.send(new FlashlightActivatePayload(stack.get(ModItems.FLASH_ACTIVE)));
        }
        user.getItemCooldownManager().set(this, 10);
        return TypedActionResult.pass(stack);
    }

    public void flash(World world, ItemStack stack, PlayerEntity user){
        if (stack.get(ModItems.FLASH_STAGE) != 1 && stack.contains(ModItems.FLASH_STAGE)){
            stack.set(ModItems.FLASH_STAGE, stack.get(ModItems.FLASH_STAGE) - 1);
            world.addParticle(ParticleTypes.FLASH, true, user.getX(), user.getY() + 1, user.getZ(), 0, 0, 0);
        }
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return true;
    }
}
