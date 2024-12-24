package net.hujoe.insilence.entity.custom;

import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.hujoe.insilence.Insilence;
import net.hujoe.insilence.InsilenceClient;
import net.hujoe.insilence.entity.ModEntities;
import net.hujoe.insilence.network.payloads.MouseUpdatePayload;
import net.hujoe.insilence.network.payloads.RavenAnimatePayload;
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
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Arm;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

public class RavenEntity extends MobEntity implements GeoEntity {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final TrackedData<Boolean> SCARED;
    private int ticksTilDespawn = 58;
    private boolean doneStupidSpin = false;

    public RavenEntity(EntityType<? extends MobEntity> entityType, World world) {
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
            if (!doneStupidSpin){
                doneStupidSpin = true;
                double angle = Math.random() * 360;
                angle = angle - 180;
                this.setYaw((float) angle);
            }

            if (this.getScared()) {
                if (ticksTilDespawn == 0) {
                    this.discard();
                } else if (ticksTilDespawn == 48){
                    this.getWorld().playSound(null, this.getBlockPos(), ModSounds.CROW_WING_EVENT, SoundCategory.NEUTRAL);
                    ticksTilDespawn--;
                } else {
                    ticksTilDespawn--;
                }
            } else {
                if (this.getWorld().getClosestPlayer(this, 7) != null){
                    this.setScared(true);
                    for (ServerPlayerEntity player : PlayerLookup.world((ServerWorld) this.getWorld())) {
                        ServerPlayNetworking.send(player, new RavenAnimatePayload(this.getId()));
                    }

                    if (Math.random() > 0.5) {
                        this.getWorld().playSound(null, this.getBlockPos(), ModSounds.CROW_SCARE_01_EVENT, SoundCategory.NEUTRAL);
                    } else {
                        this.getWorld().playSound(null, this.getBlockPos(), ModSounds.CROW_SCARE_02_EVENT, SoundCategory.NEUTRAL);
                    }

                    SoundEntity soundEntity = new SoundEntity(ModEntities.SOUNDENTITY, this.getWorld());
                    soundEntity.setStrength(30);
                    soundEntity.setPosition(this.getPos());
                    this.getWorld().spawnEntity(soundEntity);
                }
            }
        }
    }

    @Override
    public Arm getMainArm() {
        return null;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(SCARED, false);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    public boolean shouldRenderName() {
        return false;
    }

    @Override
    public Iterable<ItemStack> getArmorItems() {
        return null;
    }

    @Override
    public ItemStack getEquippedStack(EquipmentSlot slot) {
        return ItemStack.EMPTY;
    }

    @Override
    public void equipStack(EquipmentSlot slot, ItemStack stack) {

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
}
