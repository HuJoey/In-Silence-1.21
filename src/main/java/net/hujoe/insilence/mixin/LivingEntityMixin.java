package net.hujoe.insilence.mixin;

import com.google.common.base.Objects;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.hujoe.insilence.InSilenceEssentials;
import net.hujoe.insilence.Insilence;
import net.hujoe.insilence.client.ClientRakeManager;
import net.hujoe.insilence.entity.ModEntities;
import net.hujoe.insilence.entity.custom.MouseEntity;
import net.hujoe.insilence.entity.custom.RakeEntity;
import net.hujoe.insilence.entity.custom.SoundEntity;
import net.hujoe.insilence.network.payloads.RakeAttackSendPayload;
import net.hujoe.insilence.network.payloads.VolumeUpdatePayload;
import net.hujoe.insilence.server.RakeManager;
import net.hujoe.insilence.sound.ModSounds;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements InSilenceEssentials {
    @Shadow public abstract float getHeadYaw();

    @Shadow public abstract void setBodyYaw(float bodyYaw);

    @Shadow public abstract void setHeadYaw(float headYaw);

    @Shadow public abstract boolean damage(DamageSource source, float amount);

    private int ticksSinceLastSound = 20;
    private RakeEntity rakeEntity;
    private MouseEntity mouseEntity;
    private Vec3d lastPos;
    private float soundLevel = -127;
    private int lastVolume = 0;
    private int jumpCooldown;
    private int dashCooldown;
    private int lockInCooldown;
    private boolean dashActive = false;
    private int dashingTicks = 80;
    private float lastHeadYaw;
    private int attackTicks = 0;
    private Vec3d attackPos;
    private float attackYaw;
    private float attackPitch;
    private Vec3d caughtPos;
    private float caughtYaw;
    private boolean caught;
    private int bloodTicks = 10;
    private boolean attackFromDash;
    private PlayerEntity caughtTarget;
    private boolean isStunned = false;
    private int attackerId;
    private int ticksSinceLastSqueak = 0;

    public final RegistryKey<DamageType> RAKE_DAMAGE = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(Insilence.MOD_ID, "rake_damage"));
    private static final EntityAttributeModifier RAKE_WALK_SLOW = new EntityAttributeModifier(Identifier.of(Insilence.MOD_ID, "rake_walk"), -0.6, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);

    private static final EntityAttributeModifier RAKE_SPRINT = new EntityAttributeModifier(Identifier.of(Insilence.MOD_ID, "rake_sprint"), 2, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    private static final EntityAttributeModifier RAKE_JUMP = new EntityAttributeModifier(Identifier.of(Insilence.MOD_ID, "rake_jump"), 1, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    private static final EntityAttributeModifier RAKE_STEP = new EntityAttributeModifier(Identifier.of(Insilence.MOD_ID, "rake_step"), 1, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    private static final EntityAttributeModifier ANIMATION_GRAVITY = new EntityAttributeModifier(Identifier.of(Insilence.MOD_ID, "animation_gravity"), 0, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
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
            } else if (ClientRakeManager.getRakeManager().isMouse(player.getNameForScoreboard())){
                switch (pose){
                    case STANDING:
                        cir.setReturnValue(EntityDimensions.changing(0.5F, 0.5F)
                                .withEyeHeight(0.3F)
                                .withAttachments(EntityAttachments.builder().add(EntityAttachmentType.VEHICLE, new Vec3d(0.0, 0.6, 0.0))));
                        cir.cancel();
                        break;
                    case CROUCHING:
                        cir.setReturnValue(EntityDimensions.changing(0.5F, 0.2F)
                                .withEyeHeight(0.1F)
                                .withAttachments(EntityAttachments.builder().add(EntityAttachmentType.VEHICLE, new Vec3d(0.0, 0.6, 0.0))));
                        cir.cancel();
                        break;
                }
            }
        }
    }


    @Inject(method="baseTick", at = @At("TAIL"))
    public void baseTick(CallbackInfo ci) {
        ticksSinceLastSqueak++;
        if (jumpCooldown != 0){
            jumpCooldown--;
        }
        if (dashCooldown != 0){
            dashCooldown--;
        }
        if (lockInCooldown != 0){
            lockInCooldown--;
        }
        EntityAttributeInstance gravityAttributeInstance = ((LivingEntity) (Object) this).getAttributeInstance(EntityAttributes.GENERIC_GRAVITY);

        if (attackTicks > 0){
            if (!gravityAttributeInstance.hasModifier(Identifier.of(Insilence.MOD_ID, "animation_gravity"))) {
                gravityAttributeInstance.addTemporaryModifier(ANIMATION_GRAVITY);
            }
            if (!caught) {
                this.setPosition(attackPos);
                this.setBodyYaw(attackYaw);
                this.setYaw(attackYaw);
                attackPitch = getAttackingPitch();
                this.setPitch(attackPitch); // CAN BE CHANGED!!
                lockInCooldown++;
                jumpCooldown++;
                dashCooldown++;
            } else {
                if (attackTicks < 72 && attackTicks > 65){
                    caughtPos = new Vec3d(caughtPos.getX(), caughtPos.getY() + 0.3, caughtPos.getZ());
                }
                this.setPosition(caughtPos);
                this.setYaw(caughtYaw);
                this.setBodyYaw(caughtYaw);
                if (attackTicks < 89 && attackTicks > 32) {
                    if (bloodTicks == 0 && !getWorld().isClient) {
                        ((ServerWorld) getWorld()).spawnParticles(new BlockStateParticleEffect(ParticleTypes.BLOCK, Blocks.NETHER_WART_BLOCK.getDefaultState()), caughtPos.x, caughtPos.y + 1, caughtPos.z, 6, 0, 0, 0, 0);
                        bloodTicks = 10;
                    }
                    bloodTicks--;
                } else if (attackTicks == 89 && !getWorld().isClient){
                    ((ServerWorld) getWorld()).spawnParticles(new BlockStateParticleEffect(ParticleTypes.BLOCK, Blocks.NETHER_WART_BLOCK.getDefaultState()), caughtPos.x, caughtPos.y + 1, caughtPos.z, 15, 0, 0, 0, 0);
                    bloodTicks = 10;
                } else if (attackTicks == 32 && !getWorld().isClient){
                    ((ServerWorld) getWorld()).spawnParticles(new BlockStateParticleEffect(ParticleTypes.BLOCK, Blocks.NETHER_WART_BLOCK.getDefaultState()), caughtPos.x, caughtPos.y + 1, caughtPos.z, 50, 0, 0, 0, 0);
                    murder();
                }
            }
            attackTicks--;
        } else if (attackTicks < 0){
            attackTicks = 0;
        } else {
            if (gravityAttributeInstance.hasModifier(Identifier.of(Insilence.MOD_ID, "animation_gravity"))) {
                gravityAttributeInstance.removeModifier(ANIMATION_GRAVITY);
            }
        }

        if (!RakeManager.getRakeManager().isRake(this.getNameForScoreboard())){
            World world = this.getWorld();
            if (!world.isClient() && this.isAlive()) {
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
                dashingTicks = 80;
                dashActive = false;
            }
            HitResult result = this.raycast(2, 0, false);
            PlayerEntity target = getWorld().getClosestPlayer(result.getPos().x,result.getPos().y,result.getPos().z, 2.5, false);
            if (target != null && target.getId() != this.getId()) {
                if (target.isAttackable() && ClientRakeManager.getRakeManager().isRake(this.getNameForScoreboard())) {
                    if (!isAttacking() && !isStunned()) {
                        if (target instanceof PlayerEntity) {
                            if (!ClientRakeManager.getRakeManager().isRake(target.getNameForScoreboard()) && !ClientRakeManager.getRakeManager().isMouse(target.getNameForScoreboard())) {
                                if (getWorld().isClient) {
                                    this.triggerJumpscare();
                                    attackFromDash = true;
                                    caughtTarget = target;
                                }
                            }
                        }
                    }
                }
            }
            dashingTicks--;
        }
    }

    @Inject(method="jump", at = @At("HEAD"), cancellable = true)
    public void jump(CallbackInfo ci){
        if (ClientRakeManager.getRakeManager().isRake(this.getNameForScoreboard())) {
            if (!canJump()) {
                ci.cancel();
            } else {
                jumpCooldown();
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
    public MouseEntity getMouseEntity(){return mouseEntity;}
    public void setMouseEntity(MouseEntity m){mouseEntity = m;}
    public boolean canDash(){return dashCooldown == 0;}
    public boolean canJump(){return jumpCooldown == 0;}
    public boolean canLockIn(){return lockInCooldown == 0;}
    public void lockIn(){
        lockInCooldown = 400;
    }

    public void dash(){
        dashCooldown = 600;
        lastHeadYaw = this.getHeadYaw();
        dashActive = true;
    }

    private void setDash(){
        float f = -MathHelper.sin(this.getYaw() * 0.017453292F) * MathHelper.cos(this.getPitch() * 0.017453292F);
        float g = -MathHelper.sin((this.getPitch()) * 0.017453292F);
        float h = MathHelper.cos(this.getYaw() * 0.017453292F) * MathHelper.cos(this.getPitch() * 0.017453292F);
        Vec3d vec3d = (new Vec3d(f, g, h)).normalize().multiply( 1);
        Vec3d realVec = new Vec3d(vec3d.x, -1, vec3d.z);
        this.setVelocity(realVec);
    }

    public boolean isDashing(){
        return dashActive;
    }

    public void jumpCooldown(){
        this.jumpCooldown = 60;
    }

    public boolean isAttacking(){
        return attackTicks > 0;
    }

    public void triggerJumpscare(int targetId){
        triggerJumpscare();
        World world = this.getWorld();
    }

    public void triggerJumpscare(){
        attackTicks = 142;
        attackYaw = this.getYaw();
        attackPitch = 0;
        attackPos = this.getPos();
        dashingTicks = 80;
        dashActive = false;
        caught = false;
    }

    public boolean isStunned(){
        return isStunned;
    }

    // this is probably the worst way to animate a camera - find a better way!!!!
    public float getAttackingPitch(){
        float currentPitch = attackPitch;
        if (attackTicks > 136){
            currentPitch += 8;
        } else if (attackTicks > 103){
            // do nothing
        } else if (attackTicks > 94){
            currentPitch -= 3;
        } else if (attackTicks > 88){
            currentPitch += 8;
        } else if (attackTicks > 60){
            currentPitch -= 3;
        } else if (attackTicks > 37){
            // have a dance party
        } else if (attackTicks > 32){
            currentPitch -= 20;
        } else if (attackTicks > 27){
            currentPitch += 20;
        }
        return currentPitch;
    }

    public void triggerCaught(float yaw, Vec3d pos) {
        caughtYaw = yaw + 180;
        attackTicks = 142;
        this.setPitch(0);
        caughtPos = pos;
        caught = true;
    }

    public boolean isCaught(){
        return caught;
    }

    public void murder(){
        attackTicks = 0;
        DamageSource damageSource = new DamageSource(getWorld().getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(RAKE_DAMAGE));
        this.damage(damageSource, 100);
    }

    public boolean wasAttackFromDash(){
        boolean result = false;
        if (attackFromDash){
            attackFromDash = false;
            result = true;
        }
        return result;
    }

    public PlayerEntity getCaughtTarget(){
        return caughtTarget;
    }

    public void setStunned(boolean stunned){
        isStunned = stunned;
    }

    public int getAttackerId(){
        return attackerId;
    }
    public void setAttackerId(int id){
        attackerId = id;
    }

    public void cancelAttack(){
        attackTicks = 0;
    }

    public boolean trySqueak(){
        if (ticksSinceLastSqueak >= 200){
            ticksSinceLastSqueak = 0;
            return true; // success
        } else {
            return false; // failed
        }
    }
}

