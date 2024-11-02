package net.hujoe.insilence.sound;

import net.hujoe.insilence.Insilence;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static Identifier SIGNAL = Identifier.of(Insilence.MOD_ID, "signal");
    public static SoundEvent SIGNAL_EVENT = SoundEvent.of(SIGNAL);

    public static void registerModSounds(){
        Insilence.LOGGER.info("Registering Mod Items for " + Insilence.MOD_ID);
        Registry.register(Registries.SOUND_EVENT, SIGNAL, SIGNAL_EVENT);
    }
}
