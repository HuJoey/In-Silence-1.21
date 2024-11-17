package net.hujoe.insilence.item.custom;

import net.hujoe.insilence.Insilence;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
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

        if (!world.isClient && active){
            Insilence.LOGGER.info("AKLJBHSFASLKDFH");
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
