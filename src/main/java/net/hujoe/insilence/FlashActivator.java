package net.hujoe.insilence;

import net.hujoe.insilence.entity.custom.RakeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;

public interface FlashActivator {
    void activateFlash();
    void incrementTicks();
}
