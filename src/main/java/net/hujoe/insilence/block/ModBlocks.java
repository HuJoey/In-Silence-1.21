package net.hujoe.insilence.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.hujoe.insilence.Insilence;
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
            .luminance(FlashlightLightBlock.STATE_TO_LUMINANCE));
    public static void registerModBlocks(){
        Registry.register(Registries.BLOCK, Identifier.of(Insilence.MOD_ID, "flashlight_light"), FLASHLIGHT_LIGHT);
        Registry.register(Registries.BLOCK, Identifier.of(Insilence.MOD_ID, "tall_wheat"), TALL_WHEAT);
        Registry.register(Registries.ITEM, Identifier.of(Insilence.MOD_ID, "tall_wheat"),  new BlockItem(TALL_WHEAT, new Item.Settings()));
        Insilence.LOGGER.info("Registering ModBlocks for " + Insilence.MOD_ID);
    }
}
