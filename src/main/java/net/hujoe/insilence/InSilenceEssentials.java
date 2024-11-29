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
    boolean isDashing();
    void lockIn();
    void dash();
    void jumpCooldown();
    boolean isAttacking();
    void triggerJumpscare(int id);
}
