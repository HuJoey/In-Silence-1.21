package net.hujoe.insilence.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityType;
import net.hujoe.insilence.Insilence;
import net.hujoe.insilence.block.ModBlocks;
import net.hujoe.insilence.block.custom.FlashlightLightBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<FlashlightLightBlockEntity> FLASHLIGHT_LIGHT_BLOCK_ENTITY = BlockEntityType.Builder.create(FlashlightLightBlockEntity::new, ModBlocks.FLASHLIGHT_LIGHT).build();
    public static final BlockEntityType<RadioBlockEntity> RADIO_BLOCK_ENTITY = BlockEntityType.Builder.create(RadioBlockEntity::new, ModBlocks.RADIO).build();
    public static final BlockEntityType<TVBlockEntity> TV_BLOCK_ENTITY = BlockEntityType.Builder.create(TVBlockEntity::new, ModBlocks.TV).build();

    public static void registerModBlockEntities() {
        Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Insilence.MOD_ID, "flashlight_block_entity"), FLASHLIGHT_LIGHT_BLOCK_ENTITY);
        Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Insilence.MOD_ID, "radio_block_entity"), RADIO_BLOCK_ENTITY);
        Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Insilence.MOD_ID, "tv_block_entity"), TV_BLOCK_ENTITY);
    }
}
