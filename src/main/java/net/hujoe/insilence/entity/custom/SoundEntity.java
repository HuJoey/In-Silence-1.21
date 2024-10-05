package net.hujoe.insilence.entity.custom;

import net.hujoe.insilence.entity.ModEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;
import java.util.function.Predicate;

public class SoundEntity extends Entity {
    private static final int duration = 40;
    private int strength;
    private int radius1;
    private int radius2;
    private int radius3;

    public SoundEntity(EntityType<?> type, World world) {
        super(type, world);
        strength = 50; //for testing purposes
        radius1 = strength / 2;
        radius2 = strength;
        radius3 = 2 * strength;
    }

    public void tick() {
        if (this.getWorld().isClient) {
            for(int i = 0; i < 2; ++i) {
                this.getWorld().addParticle(ParticleTypes.PORTAL, this.getParticleX(0.5), this.getRandomBodyY() - 0.25, this.getParticleZ(0.5), (this.random.nextDouble() - 0.5) * 2.0, -this.random.nextDouble(), (this.random.nextDouble() - 0.5) * 2.0);
            }
/*
            BlockPos pos = this.getBlockPos();
            World world = this.getWorld();
            Vec3d vec3d = Vec3d.ofCenter(pos);
            Predicate<LivingEntity> close = (player) -> {
                return player.getPos().isInRange(vec3d, radius1);
            };
            Predicate<LivingEntity> midway = (player) -> {
                return player.getPos().isInRange(vec3d, radius2);
            };
            Predicate<LivingEntity> far = (player) -> {
                return player.getPos().isInRange(vec3d, radius3);
            };
            List<LivingEntity> closeEntities = world.getEntitiesByClass(LivingEntity.class, new Box(pos), close.and(LivingEntity::isAlive).and(EntityPredicates.EXCEPT_SPECTATOR));
            List<LivingEntity> midwayEntities = world.getEntitiesByClass(LivingEntity.class, new Box(pos), midway.and(LivingEntity::isAlive).and(EntityPredicates.EXCEPT_SPECTATOR));
            List<LivingEntity> farEntities = world.getEntitiesByClass(LivingEntity.class, new Box(pos), far.and(LivingEntity::isAlive).and(EntityPredicates.EXCEPT_SPECTATOR));
            for (int i = 0; i < closeEntities.size(); i++){

            }
            
 */
        }
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {

    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }
}
