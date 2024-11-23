package net.hujoe.insilence;

import net.hujoe.insilence.entity.custom.RakeEntity;
import net.minecraft.entity.player.PlayerEntity;

public interface InSilenceEssentials {
    void setSoundLevel(float lvl);
    int getLastVolume();
    void setLastVolume(int lvl);
    RakeEntity getRakeEntity();
    void setRakeEntity(RakeEntity r);
    boolean canDash();
    boolean canJump();
    boolean canLockIn();
    boolean isStunned();
    void lockIn();
    void dash();
}
