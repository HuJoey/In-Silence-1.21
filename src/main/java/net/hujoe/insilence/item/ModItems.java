package net.hujoe.insilence.item;

import net.hujoe.insilence.Insilence;
import net.hujoe.insilence.item.custom.CarPartItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item GASOLINE = registerItem("gasoline", new CarPartItem(new Item.Settings().maxCount(1)));
    public static final Item CAR_BATTERY = registerItem("car_battery", new CarPartItem(new Item.Settings().maxCount(1)));
    public static final Item KEY = registerItem("key", new CarPartItem(new Item.Settings().maxCount(1)));
    public static final Item TIRE = registerItem("tire", new CarPartItem(new Item.Settings().maxCount(1)));

    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(Insilence.MOD_ID, name), item);
    }

    public static void registerModItems(){
        Insilence.LOGGER.info("Registering Mod Items for " + Insilence.MOD_ID);
    }
}
