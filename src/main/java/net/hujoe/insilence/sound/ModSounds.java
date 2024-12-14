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
    public static Identifier USE_BATTERY = Identifier.of(Insilence.MOD_ID, "usebattery");
    public static Identifier CROW_01 = Identifier.of(Insilence.MOD_ID, "crow1");
    public static Identifier CROW_02 = Identifier.of(Insilence.MOD_ID, "crow2");
    public static Identifier CROW_SCARE_01 = Identifier.of(Insilence.MOD_ID, "crowscare1");
    public static Identifier CROW_SCARE_02 = Identifier.of(Insilence.MOD_ID, "crowscare2");
    public static Identifier CROW_WING = Identifier.of(Insilence.MOD_ID, "crowwing");
    public static Identifier OWL_HOOT_01 = Identifier.of(Insilence.MOD_ID, "owlhoot1");
    public static Identifier OWL_HOOT_02 = Identifier.of(Insilence.MOD_ID, "owlhoot2");
    public static Identifier TV_STATIC = Identifier.of(Insilence.MOD_ID, "tvstatic");
    public static Identifier RADIO_STATIC = Identifier.of(Insilence.MOD_ID, "radiostatic");
    public static SoundEvent SIGNAL_EVENT = SoundEvent.of(SIGNAL);
    public static SoundEvent SPRINT_EVENT_1 = SoundEvent.of(SPRINT_1);
    public static SoundEvent SPRINT_EVENT_2 = SoundEvent.of(SPRINT_2);
    public static SoundEvent EYE_EVENT_1 = SoundEvent.of(EYE_1);
    public static SoundEvent EYE_EVENT_2 = SoundEvent.of(EYE_2);
    public static SoundEvent FLASHBANG_EVENT = SoundEvent.of(FLASHBANG);
    public static SoundEvent CATCH_EVENT = SoundEvent.of(CATCH);
    public static SoundEvent EAR_RINGING_EVENT = SoundEvent.of(EAR_RINGING);
    public static SoundEvent USE_BATTERY_EVENT = SoundEvent.of(USE_BATTERY);
    public static SoundEvent CROW_01_EVENT = SoundEvent.of(CROW_01);
    public static SoundEvent CROW_02_EVENT = SoundEvent.of(CROW_02);
    public static SoundEvent CROW_SCARE_01_EVENT = SoundEvent.of(CROW_SCARE_01);
    public static SoundEvent CROW_SCARE_02_EVENT = SoundEvent.of(CROW_SCARE_02);
    public static SoundEvent CROW_WING_EVENT = SoundEvent.of(CROW_WING);
    public static SoundEvent OWL_HOOT_01_EVENT = SoundEvent.of(OWL_HOOT_01);
    public static SoundEvent OWL_HOOT_02_EVENT = SoundEvent.of(OWL_HOOT_02);
    public static SoundEvent TV_STATIC_EVENT = SoundEvent.of(TV_STATIC);
    public static SoundEvent RADIO_STATIC_EVENT = SoundEvent.of(RADIO_STATIC);

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
        Registry.register(Registries.SOUND_EVENT, USE_BATTERY, USE_BATTERY_EVENT);
        Registry.register(Registries.SOUND_EVENT, CROW_01, CROW_01_EVENT);
        Registry.register(Registries.SOUND_EVENT, CROW_02, CROW_02_EVENT);
        Registry.register(Registries.SOUND_EVENT, CROW_SCARE_01, CROW_SCARE_01_EVENT);
        Registry.register(Registries.SOUND_EVENT, CROW_SCARE_02, CROW_SCARE_02_EVENT);
        Registry.register(Registries.SOUND_EVENT, CROW_WING, CROW_WING_EVENT);
        Registry.register(Registries.SOUND_EVENT, OWL_HOOT_01, OWL_HOOT_01_EVENT);
        Registry.register(Registries.SOUND_EVENT, OWL_HOOT_02, OWL_HOOT_02_EVENT);
        Registry.register(Registries.SOUND_EVENT, TV_STATIC, TV_STATIC_EVENT);
        Registry.register(Registries.SOUND_EVENT, RADIO_STATIC, RADIO_STATIC_EVENT);
    }
}
