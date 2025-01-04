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
    public static Identifier SPRINT_1_PLAYER = Identifier.of(Insilence.MOD_ID, "sprint1player");
    public static Identifier SPRINT_2_PLAYER = Identifier.of(Insilence.MOD_ID, "sprint2player");
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
    public static Identifier FOOTSTEPS_01 = Identifier.of(Insilence.MOD_ID, "footsteps01");
    public static Identifier FOOTSTEPS_02 = Identifier.of(Insilence.MOD_ID, "footsteps02");
    public static Identifier FOOTSTEPS_03 = Identifier.of(Insilence.MOD_ID, "footsteps03");
    public static Identifier FOOTSTEPS_04 = Identifier.of(Insilence.MOD_ID, "footsteps04");
    public static Identifier FOOTSTEPS_05 = Identifier.of(Insilence.MOD_ID, "footsteps05");
    public static Identifier FOOTSTEPS_06 = Identifier.of(Insilence.MOD_ID, "footsteps06");
    public static Identifier FOOTSTEPS_07 = Identifier.of(Insilence.MOD_ID, "footsteps07");
    public static Identifier FOOTSTEPS_08 = Identifier.of(Insilence.MOD_ID, "footsteps08");
    public static Identifier BREATHING_01 = Identifier.of(Insilence.MOD_ID, "breathing01");
    public static Identifier BREATHING_02 = Identifier.of(Insilence.MOD_ID, "breathing02");
    public static Identifier BREATHING_03 = Identifier.of(Insilence.MOD_ID, "breathing03");
    public static Identifier BREATHING_04 = Identifier.of(Insilence.MOD_ID, "breathing04");
    public static Identifier BREATHING_05 = Identifier.of(Insilence.MOD_ID, "breathing05");
    public static Identifier BREATHING_06 = Identifier.of(Insilence.MOD_ID, "breathing06");
    public static Identifier BLOODY_NIGHTMARE = Identifier.of(Insilence.MOD_ID, "bloodynightmare");
    public static Identifier RAT_SQUEAK_1 = Identifier.of(Insilence.MOD_ID, "ratsqueak1");
    public static Identifier RAT_SQUEAK_2 = Identifier.of(Insilence.MOD_ID, "ratsqueak2");
    public static Identifier RAT_SQUEAK_3 = Identifier.of(Insilence.MOD_ID, "ratsqueak3");
    public static Identifier TENSION_1 = Identifier.of(Insilence.MOD_ID, "tension1");
    public static Identifier TENSION_2 = Identifier.of(Insilence.MOD_ID, "tension2");
    public static Identifier TRUCK_ESCAPE = Identifier.of(Insilence.MOD_ID, "truckescape");
    public static Identifier TRUCK_REPAIR = Identifier.of(Insilence.MOD_ID, "truckrepair");
    public static Identifier USE_CAR_BATTERY = Identifier.of(Insilence.MOD_ID, "usecarbattery");
    public static Identifier USE_FUEL = Identifier.of(Insilence.MOD_ID, "usefuel");
    public static Identifier USE_KEY = Identifier.of(Insilence.MOD_ID, "usekey");
    public static Identifier USE_WHEEL = Identifier.of(Insilence.MOD_ID, "usewheel");
    public static Identifier YOU_ARE_CREATURE = Identifier.of(Insilence.MOD_ID, "youarecreature");
    public static Identifier YOU_ARE_HUMAN = Identifier.of(Insilence.MOD_ID, "youarehuman");
    public static Identifier THUNDER_SPIKE = Identifier.of(Insilence.MOD_ID, "thunderspike");
    public static Identifier AMBIANCE_WINDY = Identifier.of(Insilence.MOD_ID, "ambiancewindy");


    public static SoundEvent SIGNAL_EVENT = SoundEvent.of(SIGNAL);
    public static SoundEvent SPRINT_EVENT_1 = SoundEvent.of(SPRINT_1);
    public static SoundEvent SPRINT_EVENT_2 = SoundEvent.of(SPRINT_2);
    public static SoundEvent SPRINT_EVENT_1_PLAYER = SoundEvent.of(SPRINT_1_PLAYER);
    public static SoundEvent SPRINT_EVENT_2_PLAYER = SoundEvent.of(SPRINT_2_PLAYER);
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
    public static SoundEvent FOOTSTEPS_01_EVENT = SoundEvent.of(FOOTSTEPS_01);
    public static SoundEvent FOOTSTEPS_02_EVENT = SoundEvent.of(FOOTSTEPS_02);
    public static SoundEvent FOOTSTEPS_03_EVENT = SoundEvent.of(FOOTSTEPS_03);
    public static SoundEvent FOOTSTEPS_04_EVENT = SoundEvent.of(FOOTSTEPS_04);
    public static SoundEvent FOOTSTEPS_05_EVENT = SoundEvent.of(FOOTSTEPS_05);
    public static SoundEvent FOOTSTEPS_06_EVENT = SoundEvent.of(FOOTSTEPS_06);
    public static SoundEvent FOOTSTEPS_07_EVENT = SoundEvent.of(FOOTSTEPS_07);
    public static SoundEvent FOOTSTEPS_08_EVENT = SoundEvent.of(FOOTSTEPS_08);
    public static SoundEvent BREATHING_01_EVENT = SoundEvent.of(BREATHING_01);
    public static SoundEvent BREATHING_02_EVENT = SoundEvent.of(BREATHING_02);
    public static SoundEvent BREATHING_03_EVENT = SoundEvent.of(BREATHING_03);
    public static SoundEvent BREATHING_04_EVENT = SoundEvent.of(BREATHING_04);
    public static SoundEvent BREATHING_05_EVENT = SoundEvent.of(BREATHING_05);
    public static SoundEvent BREATHING_06_EVENT = SoundEvent.of(BREATHING_06);
    public static SoundEvent BLOODY_NIGHTMARE_EVENT = SoundEvent.of(BLOODY_NIGHTMARE);
    public static SoundEvent RAT_SQUEAK_1_EVENT = SoundEvent.of(RAT_SQUEAK_1);
    public static SoundEvent RAT_SQUEAK_2_EVENT = SoundEvent.of(RAT_SQUEAK_2);
    public static SoundEvent RAT_SQUEAK_3_EVENT = SoundEvent.of(RAT_SQUEAK_3);
    public static SoundEvent TENSION_1_EVENT = SoundEvent.of(TENSION_1);
    public static SoundEvent TENSION_2_EVENT = SoundEvent.of(TENSION_2);
    public static SoundEvent TRUCK_ESCAPE_EVENT = SoundEvent.of(TRUCK_ESCAPE);
    public static SoundEvent TRUCK_REPAIR_EVENT = SoundEvent.of(TRUCK_REPAIR);
    public static SoundEvent USE_CAR_BATTERY_EVENT = SoundEvent.of(USE_CAR_BATTERY);
    public static SoundEvent USE_FUEL_EVENT = SoundEvent.of(USE_FUEL);
    public static SoundEvent USE_KEY_EVENT = SoundEvent.of(USE_KEY);
    public static SoundEvent USE_WHEEL_EVENT = SoundEvent.of(USE_WHEEL);
    public static SoundEvent YOU_ARE_CREATURE_EVENT = SoundEvent.of(YOU_ARE_CREATURE);
    public static SoundEvent YOU_ARE_HUMAN_EVENT = SoundEvent.of(YOU_ARE_HUMAN);
    public static SoundEvent THUNDER_SPIKE_EVENT = SoundEvent.of(THUNDER_SPIKE);
    public static SoundEvent AMBIANCE_WINDY_EVENT = SoundEvent.of(AMBIANCE_WINDY);

    public static void registerModSounds(){
        Insilence.LOGGER.info("Registering ModSounds for " + Insilence.MOD_ID);
        Registry.register(Registries.SOUND_EVENT, SIGNAL, SIGNAL_EVENT);
        Registry.register(Registries.SOUND_EVENT, SPRINT_1, SPRINT_EVENT_1);
        Registry.register(Registries.SOUND_EVENT, SPRINT_2, SPRINT_EVENT_2);
        Registry.register(Registries.SOUND_EVENT, SPRINT_1_PLAYER, SPRINT_EVENT_1_PLAYER);
        Registry.register(Registries.SOUND_EVENT, SPRINT_2_PLAYER, SPRINT_EVENT_2_PLAYER);
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
        Registry.register(Registries.SOUND_EVENT, FOOTSTEPS_01, FOOTSTEPS_01_EVENT);
        Registry.register(Registries.SOUND_EVENT, FOOTSTEPS_02, FOOTSTEPS_02_EVENT);
        Registry.register(Registries.SOUND_EVENT, FOOTSTEPS_03, FOOTSTEPS_03_EVENT);
        Registry.register(Registries.SOUND_EVENT, FOOTSTEPS_04, FOOTSTEPS_04_EVENT);
        Registry.register(Registries.SOUND_EVENT, FOOTSTEPS_05, FOOTSTEPS_05_EVENT);
        Registry.register(Registries.SOUND_EVENT, FOOTSTEPS_06, FOOTSTEPS_06_EVENT);
        Registry.register(Registries.SOUND_EVENT, FOOTSTEPS_07, FOOTSTEPS_07_EVENT);
        Registry.register(Registries.SOUND_EVENT, FOOTSTEPS_08, FOOTSTEPS_08_EVENT);
        Registry.register(Registries.SOUND_EVENT, BREATHING_01, BREATHING_01_EVENT);
        Registry.register(Registries.SOUND_EVENT, BREATHING_02, BREATHING_02_EVENT);
        Registry.register(Registries.SOUND_EVENT, BREATHING_03, BREATHING_03_EVENT);
        Registry.register(Registries.SOUND_EVENT, BREATHING_04, BREATHING_04_EVENT);
        Registry.register(Registries.SOUND_EVENT, BREATHING_05, BREATHING_05_EVENT);
        Registry.register(Registries.SOUND_EVENT, BREATHING_06, BREATHING_06_EVENT);
        Registry.register(Registries.SOUND_EVENT, BLOODY_NIGHTMARE, BLOODY_NIGHTMARE_EVENT);
        Registry.register(Registries.SOUND_EVENT, RAT_SQUEAK_1, RAT_SQUEAK_1_EVENT);
        Registry.register(Registries.SOUND_EVENT, RAT_SQUEAK_2, RAT_SQUEAK_2_EVENT);
        Registry.register(Registries.SOUND_EVENT, RAT_SQUEAK_3, RAT_SQUEAK_3_EVENT);
        Registry.register(Registries.SOUND_EVENT, TENSION_1, TENSION_1_EVENT);
        Registry.register(Registries.SOUND_EVENT, TENSION_2, TENSION_2_EVENT);
        Registry.register(Registries.SOUND_EVENT, TRUCK_REPAIR, TRUCK_REPAIR_EVENT);
        Registry.register(Registries.SOUND_EVENT, TRUCK_ESCAPE, TRUCK_ESCAPE_EVENT);
        Registry.register(Registries.SOUND_EVENT, USE_CAR_BATTERY, USE_CAR_BATTERY_EVENT);
        Registry.register(Registries.SOUND_EVENT, USE_FUEL, USE_FUEL_EVENT);
        Registry.register(Registries.SOUND_EVENT, USE_KEY, USE_KEY_EVENT);
        Registry.register(Registries.SOUND_EVENT, USE_WHEEL, USE_WHEEL_EVENT);
        Registry.register(Registries.SOUND_EVENT, YOU_ARE_HUMAN, YOU_ARE_HUMAN_EVENT);
        Registry.register(Registries.SOUND_EVENT, YOU_ARE_CREATURE, YOU_ARE_CREATURE_EVENT);
        Registry.register(Registries.SOUND_EVENT, THUNDER_SPIKE, THUNDER_SPIKE_EVENT);
        Registry.register(Registries.SOUND_EVENT, AMBIANCE_WINDY, AMBIANCE_WINDY_EVENT);
    }
}
