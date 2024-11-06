package net.hujoe.insilence.network;

import net.hujoe.insilence.Insilence;
import net.minecraft.util.Identifier;
public class InsilenceNetworking {
    public static final Identifier RAKE_UPDATE_PACKET_ID = Identifier.of(Insilence.MOD_ID, "rake_update");
    public static final Identifier RAKE_LIST_RECEIVE_PACKET_ID = Identifier.of(Insilence.MOD_ID, "rake_list_receive");
    public static final Identifier SIGNAL_SOUND_PACKET_ID = Identifier.of(Insilence.MOD_ID, "signal_sound");
}
