package net.hujoe.insilence.entity.custom;

import net.hujoe.insilence.InsilenceClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.world.World;

public class LocationEntity extends Entity {
    private static final TrackedData<Integer> LOCATION_TYPE;
    public LocationEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    /*
            "Type": 1 is CAR
            "Type": 2 is BARN
            "Type": 3 is HOUSE
     */

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        builder.add(LOCATION_TYPE, 0);
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
        this.setLocationType(nbt.getInt("Type"));
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
        nbt.putInt("Type", this.getLocationType());
    }

    public int getLocationType() {
        return (Integer)this.dataTracker.get(LOCATION_TYPE);
    }

    public void setLocationType(int type) {
        this.dataTracker.set(LOCATION_TYPE, type);
    }


    static {
        LOCATION_TYPE = DataTracker.registerData(LocationEntity.class, TrackedDataHandlerRegistry.INTEGER);
    }
}
