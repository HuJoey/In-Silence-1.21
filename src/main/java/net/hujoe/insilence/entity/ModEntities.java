package net.hujoe.insilence.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.hujoe.insilence.Insilence;
import net.hujoe.insilence.entity.custom.MouseEntity;
import net.hujoe.insilence.entity.custom.RakeEntity;
import net.hujoe.insilence.entity.custom.SoundEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<SoundEntity> SOUNDENTITY =
            Registry.register(Registries.ENTITY_TYPE, Identifier.of(Insilence.MOD_ID, "sound"),
                    EntityType.Builder.create(SoundEntity::new, SpawnGroup.MISC).dimensions(0f, 0f).build("sound"));

    public static final EntityType<RakeEntity> RAKE =
            Registry.register(Registries.ENTITY_TYPE, Identifier.of(Insilence.MOD_ID, "rake"),
                    EntityType.Builder.create(RakeEntity::new, SpawnGroup.MISC).dimensions(1f, 3f).build("rake"));

    public static final EntityType<MouseEntity> MOUSE =
            Registry.register(Registries.ENTITY_TYPE, Identifier.of(Insilence.MOD_ID, "mouse"),
                    EntityType.Builder.create(MouseEntity::new, SpawnGroup.MISC).dimensions(1f, 3f).build("mouse"));

    public static void registerModEntities() {
        Insilence.LOGGER.info("Registering ModEntities for " + Insilence.MOD_ID);
        FabricDefaultAttributeRegistry.register(RAKE, RakeEntity.createRakeAttributes());
        FabricDefaultAttributeRegistry.register(MOUSE, MouseEntity.createMouseAttributes());
    }
}
