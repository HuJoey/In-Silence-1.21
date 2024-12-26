package net.hujoe.insilence.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.hujoe.insilence.Insilence;
import net.hujoe.insilence.entity.custom.*;
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

    public static final EntityType<LocationEntity> LOCATIONENTITY =
            Registry.register(Registries.ENTITY_TYPE, Identifier.of(Insilence.MOD_ID, "location"),
                    EntityType.Builder.create(LocationEntity::new, SpawnGroup.MISC).dimensions(0f, 0f).build("location"));

    public static final EntityType<RavenEntity> RAVEN =
            Registry.register(Registries.ENTITY_TYPE, Identifier.of(Insilence.MOD_ID, "raven"),
                    EntityType.Builder.create(RavenEntity::new, SpawnGroup.MISC).dimensions(1f, 1f).build("raven"));

    public static final EntityType<HuJoeEntity> HUJOE_STATUE =
            Registry.register(Registries.ENTITY_TYPE, Identifier.of(Insilence.MOD_ID, "hujoe_statue"),
                    EntityType.Builder.create(HuJoeEntity::new, SpawnGroup.MISC).dimensions(1.5f, 2f).build("hujoe_statue"));

    public static final EntityType<SobrXShuppetEntity> SOBR_SHUPPET_STATUE =
            Registry.register(Registries.ENTITY_TYPE, Identifier.of(Insilence.MOD_ID, "sobr_shuppet_statue"),
                    EntityType.Builder.create(SobrXShuppetEntity::new, SpawnGroup.MISC).dimensions(3f, 2f).build("sobr_shuppet_statue"));


    public static void registerModEntities() {
        Insilence.LOGGER.info("Registering ModEntities for " + Insilence.MOD_ID);
        FabricDefaultAttributeRegistry.register(RAKE, RakeEntity.createRakeAttributes());
        FabricDefaultAttributeRegistry.register(MOUSE, MouseEntity.createMouseAttributes());
        FabricDefaultAttributeRegistry.register(RAVEN, RavenEntity.createMobAttributes());
        FabricDefaultAttributeRegistry.register(HUJOE_STATUE, HuJoeEntity.createMobAttributes());
        FabricDefaultAttributeRegistry.register(SOBR_SHUPPET_STATUE, SobrXShuppetEntity.createMobAttributes());
    }
}
