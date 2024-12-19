package net.hujoe.insilence.entity.custom;

import net.hujoe.insilence.InsilenceClient;
import net.hujoe.insilence.sound.ModSounds;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Arm;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

public class RavenEntity extends Entity implements GeoEntity {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final TrackedData<Boolean> SCARED;
    private int ticksTilDespawn = 290;

    public RavenEntity(EntityType<? extends Entity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState) {
        tAnimationState.getController().triggerableAnim("fly", RawAnimation.begin().then("fly", Animation.LoopType.PLAY_ONCE));
        return PlayState.CONTINUE;
    }

    public void tick() {
        if (!this.getWorld().isClient) {
            if (this.getScared()) {
                if (ticksTilDespawn == 0) {
                    this.discard();
                } else if (ticksTilDespawn == 278){
                    this.getWorld().playSound(null, this.getBlockPos(), ModSounds.CROW_WING_EVENT, SoundCategory.NEUTRAL);
                    ticksTilDespawn--;
                } else {
                    ticksTilDespawn--;
                }
            } else {
                if (this.getWorld().getClosestPlayer(this, 5) != null){
                    this.setScared(true);
                    cache.getManagerForId(this.getId()).tryTriggerAnimation("controller", "fly");

                    if (Math.random() > 0.5) {
                        this.getWorld().playSound(null, this.getBlockPos(), ModSounds.CROW_SCARE_01_EVENT, SoundCategory.NEUTRAL);
                    } else {
                        this.getWorld().playSound(null, this.getBlockPos(), ModSounds.CROW_SCARE_02_EVENT, SoundCategory.NEUTRAL);
                    }
                }
            }
        }
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        builder.add(SCARED, false);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {

    }

    public boolean getScared() {
        return this.dataTracker.get(SCARED);
    }

    public void setScared(boolean scared) {
        this.dataTracker.set(SCARED, scared);
    }

    static {
        SCARED = DataTracker.registerData(RavenEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    }

    @Override
    public void onSpawnPacket(EntitySpawnS2CPacket packet) {
        int i = packet.getEntityId();
        double d = packet.getX();
        double e = packet.getY();
        double f = packet.getZ();
        this.updateTrackedPosition(d, e, f);
        this.refreshPositionAfterTeleport(d, e, f);
        this.setPitch(packet.getPitch());

        double angle = Math.random() * 2 * Math.PI;
        angle = angle - Math.PI;

        this.setYaw((float) angle);
        this.setId(i);
        this.setUuid(packet.getUuid());
    }
}
