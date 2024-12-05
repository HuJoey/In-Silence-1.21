package net.hujoe.insilence.block;

import com.mojang.serialization.MapCodec;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.hujoe.insilence.Insilence;
import net.hujoe.insilence.block.custom.BatteryBlock;
import net.hujoe.insilence.block.custom.DecoyBlock;
import net.hujoe.insilence.block.custom.FlashlightLightBlock;
import net.hujoe.insilence.block.custom.TallWheatBlock;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;


public class ModBlocks {
    public static final Block TALL_WHEAT = new TallWheatBlock(AbstractBlock.Settings.create()
            .nonOpaque()
            .replaceable()
            .noCollision()
            .breakInstantly()
            .sounds(BlockSoundGroup.GRASS)
            .offset(AbstractBlock.OffsetType.XZ)
            .burnable()
            .pistonBehavior(PistonBehavior.DESTROY));
    public static final Block FLASHLIGHT_LIGHT = new FlashlightLightBlock(AbstractBlock.Settings.create()
            .replaceable()
            .strength(-1.0F, 3600000F)
            .dropsNothing()
            .noCollision()
            .sounds(BlockSoundGroup.INTENTIONALLY_EMPTY)
            .luminance(FlashlightLightBlock.STATE_TO_LUMINANCE));
    public static final Block BATTERY = new BatteryBlock(AbstractBlock.Settings.create()
            .sounds(BlockSoundGroup.INTENTIONALLY_EMPTY));
    public static final Block DECOY = new DecoyBlock(AbstractBlock.Settings.create()
            .strength(0.5F)
            .sounds(BlockSoundGroup.GRASS)
            .nonOpaque()
    );
    public static void registerModBlocks(){
        Registry.register(Registries.BLOCK, Identifier.of(Insilence.MOD_ID, "flashlight_light"), FLASHLIGHT_LIGHT);
        Registry.register(Registries.BLOCK, Identifier.of(Insilence.MOD_ID, "tall_wheat"), TALL_WHEAT);
        Registry.register(Registries.BLOCK, Identifier.of(Insilence.MOD_ID, "battery"), BATTERY);
        Registry.register(Registries.BLOCK, Identifier.of(Insilence.MOD_ID, "decoy"), DECOY);
        Registry.register(Registries.ITEM, Identifier.of(Insilence.MOD_ID, "tall_wheat"),  new BlockItem(TALL_WHEAT, new Item.Settings()));
        Registry.register(Registries.ITEM, Identifier.of(Insilence.MOD_ID, "decoy"),  new BlockItem(DECOY, new Item.Settings().maxCount(1)));
        Insilence.LOGGER.info("Registering ModBlocks for " + Insilence.MOD_ID);
    }
}
