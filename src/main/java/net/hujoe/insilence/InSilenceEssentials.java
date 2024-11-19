package net.hujoe.insilence;

import net.hujoe.insilence.entity.custom.RakeEntity;

public interface InSilenceEssentials {
    void setSoundLevel(float lvl);
    int getLastVolume();
    void setLastVolume(int lvl);
    RakeEntity getRakeEntity();
    void setRakeEntity(RakeEntity r);
}
