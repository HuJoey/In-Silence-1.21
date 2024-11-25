package net.hujoe.insilence.item;

import com.mojang.serialization.Codec;
import net.hujoe.insilence.Insilence;
import net.hujoe.insilence.item.custom.CarPartItem;
import net.hujoe.insilence.item.custom.FlashlightItem;
import net.minecraft.component.ComponentType;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final ComponentType<Integer> FLASH_STAGE = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(Insilence.MOD_ID, "flash_stage"),
            ComponentType.<Integer>builder().codec(Codec.INT).build()
    );
    public static final ComponentType<Boolean> FLASH_ACTIVE = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(Insilence.MOD_ID, "flash_active"),
            ComponentType.<Boolean>builder().codec(Codec.BOOL).build()
    );
    public static final Item GASOLINE = registerItem("gasoline", new CarPartItem(new Item.Settings().maxCount(1)));
    public static final Item CAR_BATTERY = registerItem("car_battery", new CarPartItem(new Item.Settings().maxCount(1)));
    public static final Item KEY = registerItem("key", new CarPartItem(new Item.Settings().maxCount(1)));
    public static final Item TIRE = registerItem("tire", new CarPartItem(new Item.Settings().maxCount(1)));
    public static final Item FLASHLIGHT = registerItem("flashlight", new FlashlightItem(new Item.Settings().maxCount(1).maxDamage(100).component(FLASH_STAGE, 3).component(FLASH_ACTIVE, false)));

    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(Insilence.MOD_ID, name), item);
    }

    public static void registerModItems(){
        Insilence.LOGGER.info("Registering Mod Items for " + Insilence.MOD_ID);
    }
}
