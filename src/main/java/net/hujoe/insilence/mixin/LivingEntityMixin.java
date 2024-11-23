package net.hujoe.insilence.mixin;

import com.google.common.base.Objects;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.hujoe.insilence.InSilenceEssentials;
import net.hujoe.insilence.Insilence;
import net.hujoe.insilence.InsilenceClient;
import net.hujoe.insilence.client.BlindnessHandler;
import net.hujoe.insilence.client.ClientRakeManager;
import net.hujoe.insilence.entity.ModEntities;
import net.hujoe.insilence.entity.custom.RakeEntity;
import net.hujoe.insilence.entity.custom.SoundEntity;
import net.hujoe.insilence.network.payloads.RakeUpdatePayload;
import net.hujoe.insilence.network.payloads.VolumeUpdatePayload;
import net.hujoe.insilence.server.RakeManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements InSilenceEssentials {
    @Shadow public abstract float getHeadYaw();

    @Shadow public abstract void setHealth(float health);

    private int ticksSinceLastSound = 20;
    private RakeEntity rakeEntity;
    private Vec3d lastPos;
    private float soundLevel = -127;
    private int lastVolume = 0;
    private int jumpCooldown;
    private int dashCooldown;
    private int lockInCooldown;
    private boolean dashActive = false;
    private int dashingTicks = 100;
    private float lastHeadYaw;
    private static final EntityAttributeModifier RAKE_WALK_SLOW = new EntityAttributeModifier(Identifier.of(Insilence.MOD_ID, "rake_walk"), -0.6, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    private static final EntityAttributeModifier RAKE_SPRINT = new EntityAttributeModifier(Identifier.of(Insilence.MOD_ID, "rake_sprint"), 2.67, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    private static final EntityAttributeModifier RAKE_JUMP = new EntityAttributeModifier(Identifier.of(Insilence.MOD_ID, "rake_jump"), 1, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    private static final EntityAttributeModifier RAKE_STEP = new EntityAttributeModifier(Identifier.of(Insilence.MOD_ID, "rake_step"), 1, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "getDimensions", at = @At("HEAD"), cancellable = true)
    private void getDimensions(EntityPose pose, CallbackInfoReturnable<EntityDimensions> cir) {
        if ((Object) this instanceof PlayerEntity player) {
            if (ClientRakeManager.getRakeManager().isRake(player.getNameForScoreboard())){
                switch (pose){
                    case STANDING:
                        cir.setReturnValue(EntityDimensions.changing(0.9F, 2.7F)
                                .withEyeHeight(2.5F)
                                .withAttachments(EntityAttachments.builder().add(EntityAttachmentType.VEHICLE, new Vec3d(0.0, 0.6, 0.0))));
                        cir.cancel();
                        break;
                    case CROUCHING:
                        cir.setReturnValue(EntityDimensions.changing(0.9F, 2.4F)
                                .withEyeHeight(2.15F)
                                .withAttachments(EntityAttachments.builder().add(EntityAttachmentType.VEHICLE, new Vec3d(0.0, 0.6, 0.0))));
                        cir.cancel();
                        break;
                }
            }
        }
    }


    @Inject(method="baseTick", at = @At("TAIL"))
    public void baseTick(CallbackInfo ci) {
        if (jumpCooldown != 0){
            jumpCooldown--;
        }
        if (dashCooldown != 0){
            dashCooldown--;
        }
        if (lockInCooldown != 0){
            lockInCooldown--;
        }

        if (!RakeManager.getRakeManager().isRake(this.getNameForScoreboard())){
            World world = this.getWorld();
            if (!world.isClient()) {
                if (ticksSinceLastSound == 0) {
                    ticksSinceLastSound = 20;
                    int soundStrength = 0;
                    if (this.isSprinting()) {
                        soundStrength += 30;
                    } else if (!Objects.equal(this.lastPos, this.getPos())) {
                        if (!this.isSneaking()) {
                            soundStrength += 10;
                        }
                    }
                    if (soundLevel > -30){
                        soundStrength += 20;
                    } else if (soundLevel > -60){
                        soundStrength += 10;
                    }
                    if (soundStrength > 0) {
                        SoundEntity soundEntity = new SoundEntity(ModEntities.SOUNDENTITY, world);
                        soundEntity.setStrength(soundStrength);
                        soundEntity.setPosition(this.getPos());
                        world.spawnEntity(soundEntity);
                    }
                    if(this.getType() == EntityType.PLAYER){
                        if (soundStrength != this.lastVolume) {
                            ServerPlayNetworking.send(((ServerPlayerEntity) (Object) this), new VolumeUpdatePayload(soundStrength));
                        }
                    }
                    this.lastVolume = soundStrength;
                } else {
                    ticksSinceLastSound--;
                }
                lastPos = this.getPos();
            }
        }

        EntityAttributeInstance entityWalkAttributeInstance = ((LivingEntity) (Object) this).getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
        EntityAttributeInstance entityJumpAttributeInstance = ((LivingEntity) (Object) this).getAttributeInstance(EntityAttributes.GENERIC_JUMP_STRENGTH);
        EntityAttributeInstance entityStepAttributeInstance = ((LivingEntity) (Object) this).getAttributeInstance(EntityAttributes.GENERIC_STEP_HEIGHT);
        if(ClientRakeManager.getRakeManager().isRake(this.getNameForScoreboard())){
            if (!entityJumpAttributeInstance.hasModifier(Identifier.of(Insilence.MOD_ID, "rake_jump"))) {
                entityJumpAttributeInstance.addTemporaryModifier(RAKE_JUMP);
            }
            if (!entityStepAttributeInstance.hasModifier(Identifier.of(Insilence.MOD_ID, "rake_step"))) {
                entityStepAttributeInstance.addTemporaryModifier(RAKE_STEP);
            }
            if (!entityWalkAttributeInstance.hasModifier(Identifier.of(Insilence.MOD_ID, "rake_walk"))) {
                entityWalkAttributeInstance.addTemporaryModifier(RAKE_WALK_SLOW);
            }
            if (entityWalkAttributeInstance.hasModifier(Identifier.of(Insilence.MOD_ID, "rake_sprint"))) {
                if (!this.isSprinting()) {
                    entityWalkAttributeInstance.removeModifier(RAKE_SPRINT);
                }
            } else {
                if (this.isSprinting()) {
                    entityWalkAttributeInstance.addTemporaryModifier(RAKE_SPRINT);
                }
            }
        } else {
            if (entityWalkAttributeInstance.hasModifier(Identifier.of(Insilence.MOD_ID, "rake_walk"))) {
                entityWalkAttributeInstance.removeModifier(RAKE_WALK_SLOW);
            }
            if (entityWalkAttributeInstance.hasModifier(Identifier.of(Insilence.MOD_ID, "rake_sprint"))) {
                entityWalkAttributeInstance.removeModifier(RAKE_SPRINT);
            }
            if (entityJumpAttributeInstance.hasModifier(Identifier.of(Insilence.MOD_ID, "rake_jump"))) {
                entityJumpAttributeInstance.removeModifier(RAKE_JUMP);
            }
            if (entityStepAttributeInstance.hasModifier(Identifier.of(Insilence.MOD_ID, "rake_step"))) {
                entityStepAttributeInstance.removeModifier(RAKE_STEP);
            }
        }

        if (dashActive){
            Insilence.LOGGER.info(this.getHeadYaw() + " " + this.getPitch());
            Insilence.LOGGER.info("" + lastHeadYaw);
            if (dashingTicks != 0){
                this.setSwimming(true);
                this.setPitch(0);
                if (this.getYaw() > lastHeadYaw + 1){
                    this.setYaw(lastHeadYaw + 1F);
                    this.setHeadYaw(lastHeadYaw + 1F);
                } else if (this.getYaw() < lastHeadYaw - 1){
                    this.setYaw(lastHeadYaw - 1F);
                    this.setHeadYaw(lastHeadYaw - 1F);
                }
                lastHeadYaw = this.getYaw();
                setDash();
                jumpCooldown++;
            } else {
                dashingTicks = 100;
                dashActive = false;
            }
            HitResult result = raycast(2, MinecraftClient.getInstance().getRenderTickCounter().getTickDelta(true), false);
            if (result.getType() == HitResult.Type.ENTITY){
                EntityHitResult entityHitResult = (EntityHitResult) result;
                Entity entity = entityHitResult.getEntity();
                if (entity instanceof PlayerEntity){
                    // ATTACK
                }
            }
            dashingTicks--;
        }
    }

    @Inject(method="jump", at = @At("HEAD"), cancellable = true)
    public void jump(CallbackInfo ci){
        if (RakeManager.getRakeManager().isRake(this.getNameForScoreboard())) {
            if (!canJump()) {
                ci.cancel();
            } else {
                jumpCooldown = 60;
            }
        }
    }

    public void setSoundLevel(float lvl){
        soundLevel = lvl;
    }
    public int getLastVolume(){
        return lastVolume;
    }

    public void setLastVolume(int lvl){
        lastVolume = lvl;
    }

    public RakeEntity getRakeEntity(){return rakeEntity;}
    public void setRakeEntity(RakeEntity r){rakeEntity = r;}

    public boolean canDash(){return dashCooldown == 0;}
    public boolean canJump(){return jumpCooldown == 0;}
    public boolean canLockIn(){return lockInCooldown == 0;}
    public void lockIn(){
        lockInCooldown = 400;
        // should play scream
    }

    public void dash(){
        dashCooldown = 600;
        lastHeadYaw = this.getHeadYaw();
        dashActive = true;
    }

    public void setDash(){
        float f = -MathHelper.sin(this.getYaw() * 0.017453292F) * MathHelper.cos(this.getPitch() * 0.017453292F);
        float g = -MathHelper.sin((this.getPitch()) * 0.017453292F);
        float h = MathHelper.cos(this.getYaw() * 0.017453292F) * MathHelper.cos(this.getPitch() * 0.017453292F);
        Vec3d vec3d = (new Vec3d(f, g, h)).normalize().multiply( 1);
        Vec3d realVec = new Vec3d(vec3d.x, -1, vec3d.z);
        this.setVelocity(realVec);
    }
}

