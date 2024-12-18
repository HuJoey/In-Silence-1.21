package net.hujoe.insilence.network;

import net.hujoe.insilence.Insilence;
import net.minecraft.util.Identifier;
public class InsilenceNetworking {
    public static final Identifier RAKE_UPDATE_PACKET_ID = Identifier.of(Insilence.MOD_ID, "rake_update");
    public static final Identifier RAKE_LIST_RECEIVE_PACKET_ID = Identifier.of(Insilence.MOD_ID, "rake_list_receive");
    public static final Identifier SIGNAL_SOUND_PACKET_ID = Identifier.of(Insilence.MOD_ID, "signal_sound");
    public static final Identifier VOLUME_UPDATE_PACKET_ID = Identifier.of(Insilence.MOD_ID, "volume_update");
    public static final Identifier FLASH_RECEIVE_PACKET_ID = Identifier.of(Insilence.MOD_ID, "flash_receive");
    public static final Identifier FLASH_SEND_PACKET_ID = Identifier.of(Insilence.MOD_ID, "flash_send");
    public static final Identifier DASH_PACKET_ID = Identifier.of(Insilence.MOD_ID, "dash");
    public static final Identifier DASH_CLIENT_PACKET_ID = Identifier.of(Insilence.MOD_ID, "dash_client");
    public static final Identifier LOCK_IN_PACKET_ID = Identifier.of(Insilence.MOD_ID, "lock_in");
    public static final Identifier RAKE_JUMP_PACKET_ID = Identifier.of(Insilence.MOD_ID, "rake_jump");
    public static final Identifier LIGHT_RESTART_PACKET_ID = Identifier.of(Insilence.MOD_ID, "light_restart");
    public static final Identifier LIGHT_PLACE_PACKET_ID = Identifier.of(Insilence.MOD_ID, "light_place");
    public static final Identifier RAKE_ATTACK_SEND_PACKET_ID = Identifier.of(Insilence.MOD_ID, "rake_attack_send");
    public static final Identifier RAKE_ATTACK_RECEIVE_PACKET_ID = Identifier.of(Insilence.MOD_ID, "rake_attack_receive");
    public static final Identifier MOUSE_UPDATE_PACKET_ID = Identifier.of(Insilence.MOD_ID, "mouse_update");
    public static final Identifier MOUSE_LIST_RECEIVE_PACKET_ID = Identifier.of(Insilence.MOD_ID, "mouse_list_receive");
    public static final Identifier SQUEAK_PACKET_ID = Identifier.of(Insilence.MOD_ID, "squeak");
}
