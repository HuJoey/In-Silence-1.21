package net.hujoe.insilence.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.world.World;

public class RakeEntity extends MobEntity {

    public RakeEntity(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createRakeAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 50F).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1F).add(EntityAttributes.GENERIC_ARMOR, 1F).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5);
    }
}
