package net.hujoe.insilence.mixin;

import com.google.common.base.Objects;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.hujoe.insilence.InSilenceEssentials;
import net.hujoe.insilence.Insilence;
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
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements InSilenceEssentials {
    private int ticksSinceLastSound = 20;
    private RakeEntity rakeEntity;
    private Vec3d lastPos;
    private float soundLevel = -127;
    private int lastVolume = 0;
    private static final EntityAttributeModifier RAKE_WALK_SLOW = new EntityAttributeModifier(Identifier.of(Insilence.MOD_ID, "rake_walk"), -0.6, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    private static final EntityAttributeModifier RAKE_SPRINT = new EntityAttributeModifier(Identifier.of(Insilence.MOD_ID, "rake_sprint"), 2.67, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
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
        EntityAttributeInstance entityAttributeInstance = ((LivingEntity) (Object) this).getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
        if(ClientRakeManager.getRakeManager().isRake(this.getNameForScoreboard())){
            if (!entityAttributeInstance.hasModifier(Identifier.of(Insilence.MOD_ID, "rake_walk"))) {
                entityAttributeInstance.addTemporaryModifier(RAKE_WALK_SLOW);
            }
            if (entityAttributeInstance.hasModifier(Identifier.of(Insilence.MOD_ID, "rake_sprint"))) {
                if (!this.isSprinting()) {
                    entityAttributeInstance.removeModifier(RAKE_SPRINT);
                }
            } else {
                if (this.isSprinting()) {
                    entityAttributeInstance.addTemporaryModifier(RAKE_SPRINT);
                }
            }
        } else if (entityAttributeInstance.hasModifier(Identifier.of(Insilence.MOD_ID, "rake_walk"))) {
            entityAttributeInstance.removeModifier(RAKE_WALK_SLOW);
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
}

