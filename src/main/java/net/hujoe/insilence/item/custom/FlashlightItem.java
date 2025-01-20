package net.hujoe.insilence.item.custom;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.hujoe.insilence.Insilence;
import net.hujoe.insilence.InsilenceClient;
import net.hujoe.insilence.block.ModBlocks;
import net.hujoe.insilence.block.entity.FlashlightLightBlockEntity;
import net.hujoe.insilence.block.entity.ModBlockEntities;
import net.hujoe.insilence.item.ModItems;
import net.hujoe.insilence.network.InsilenceNetworking;
import net.hujoe.insilence.network.payloads.*;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import static net.minecraft.block.Block.getRawIdFromState;

public class FlashlightItem extends Item {
    public FlashlightItem(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int i, boolean bl) {
        if (Boolean.TRUE.equals(stack.get(ModItems.FLASH_ACTIVE))) {
            if (world.isClient) {
                HitResult result = entity.raycast(4, 0, false);
                BlockPos pos = new BlockPos((int) result.getPos().getX(), (int) result.getPos().getY(), (int) result.getPos().getZ() - 1);
                if (entity.getHorizontalFacing() == Direction.EAST) {
                    pos = pos.add(-1, 0, 0);
                } else if (entity.getHorizontalFacing() == Direction.NORTH) {
                    pos = pos.add(0, 0, -1);
                }

                ClientPlayNetworking.send(new LightPlacePayload(pos.getX(), pos.getY(), pos.getZ()));
            }
        }
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        boolean state = stack.getOrDefault(ModItems.FLASH_ACTIVE, false);
        if (!world.isClient) {
            stack.set(ModItems.FLASH_ACTIVE, !state);
            user.playSoundToPlayer(SoundEvents.BLOCK_COMPARATOR_CLICK, SoundCategory.PLAYERS, 0.2F, 1);
        }
        user.getItemCooldownManager().set(this, 10);
        return TypedActionResult.pass(stack);
    }

    public void flash(World world, ItemStack stack, PlayerEntity user){
        if (stack.get(ModItems.FLASH_STAGE) != 1 && stack.contains(ModItems.FLASH_STAGE)){
            stack.set(ModItems.FLASH_STAGE, stack.get(ModItems.FLASH_STAGE) - 1);
            HitResult result = user.raycast(2, 0, false);
            world.addParticle(ParticleTypes.FLASH, true, result.getPos().getX(), result.getPos().getY(), result.getPos().getZ(), 0, 0, 0);
            updateDamage(stack);
            ClientPlayNetworking.send(new FlashSendPayload(user.getId()));
            user.getItemCooldownManager().set(this, 10);
        }
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return true;
    }

    public void updateDamage(ItemStack stack){
        switch (stack.get(ModItems.FLASH_STAGE)) {
            case 1:
                stack.setDamage(88);
                break;
            case 2:
                stack.setDamage(40);
                break;
            case 3:
                stack.setDamage(0);
        }
    }
}
