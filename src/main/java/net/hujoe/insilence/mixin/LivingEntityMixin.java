package net.hujoe.insilence.mixin;

import com.google.common.base.Objects;
import net.hujoe.insilence.CanSpeak;
import net.hujoe.insilence.Insilence;
import net.hujoe.insilence.client.ClientRakeManager;
import net.hujoe.insilence.entity.ModEntities;
import net.hujoe.insilence.entity.custom.SoundEntity;
import net.hujoe.insilence.server.RakeManager;
import net.minecraft.entity.*;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements CanSpeak {
    private int ticksSinceLastSound = 20;
    private Vec3d lastPos;
    private float soundLevel = -127;
    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Unique
    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
    }

    @Unique
    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
    }

    @Unique
    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
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
                    } else if (soundLevel > -127){
                        soundStrength += 10;
                    }
                    if (soundStrength > 0) {
                        SoundEntity soundEntity = new SoundEntity(ModEntities.SOUNDENTITY, world);
                        soundEntity.setStrength(soundStrength);
                        soundEntity.setPosition(this.getPos());
                        world.spawnEntity(soundEntity);
                    }
                } else {
                    ticksSinceLastSound--;
                }
                lastPos = this.getPos();
            }
        }
    }

    public void setSoundLevel(float lvl){
        soundLevel = lvl;
    }
}

