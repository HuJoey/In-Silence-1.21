package net.hujoe.insilence.sound;

import net.hujoe.insilence.Insilence;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static Identifier SIGNAL = Identifier.of(Insilence.MOD_ID, "signal");
    public static Identifier SPRINT_1 = Identifier.of(Insilence.MOD_ID, "sprint1");
    public static Identifier SPRINT_2 = Identifier.of(Insilence.MOD_ID, "sprint2");
    public static Identifier EYE_1 = Identifier.of(Insilence.MOD_ID, "eye1");
    public static Identifier EYE_2 = Identifier.of(Insilence.MOD_ID, "eye2");
    public static Identifier FLASHBANG = Identifier.of(Insilence.MOD_ID, "flashbang");
    public static Identifier CATCH = Identifier.of(Insilence.MOD_ID, "catch");
    public static Identifier EAR_RINGING = Identifier.of(Insilence.MOD_ID, "earringing");
    public static SoundEvent SIGNAL_EVENT = SoundEvent.of(SIGNAL);
    public static SoundEvent SPRINT_EVENT_1 = SoundEvent.of(SPRINT_1);
    public static SoundEvent SPRINT_EVENT_2 = SoundEvent.of(SPRINT_2);
    public static SoundEvent EYE_EVENT_1 = SoundEvent.of(EYE_1);
    public static SoundEvent EYE_EVENT_2 = SoundEvent.of(EYE_2);
    public static SoundEvent FLASHBANG_EVENT = SoundEvent.of(FLASHBANG);
    public static SoundEvent CATCH_EVENT = SoundEvent.of(CATCH);
    public static SoundEvent EAR_RINGING_EVENT = SoundEvent.of(EAR_RINGING);

    public static void registerModSounds(){
        Insilence.LOGGER.info("Registering ModSounds for " + Insilence.MOD_ID);
        Registry.register(Registries.SOUND_EVENT, SIGNAL, SIGNAL_EVENT);
        Registry.register(Registries.SOUND_EVENT, SPRINT_1, SPRINT_EVENT_1);
        Registry.register(Registries.SOUND_EVENT, SPRINT_2, SPRINT_EVENT_2);
        Registry.register(Registries.SOUND_EVENT, EYE_1, EYE_EVENT_1);
        Registry.register(Registries.SOUND_EVENT, EYE_2, EYE_EVENT_2);
        Registry.register(Registries.SOUND_EVENT, FLASHBANG, FLASHBANG_EVENT);
        Registry.register(Registries.SOUND_EVENT, CATCH, CATCH_EVENT);
        Registry.register(Registries.SOUND_EVENT, EAR_RINGING, EAR_RINGING_EVENT);
    }
}
