package net.hujoe.insilence.network.payloads;

import net.hujoe.insilence.network.InsilenceNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record SignalSoundPayload(String username, float volume) implements CustomPayload {
    public static final CustomPayload.Id<SignalSoundPayload> ID = new CustomPayload.Id<>(InsilenceNetworking.SIGNAL_SOUND_PACKET_ID);
    public static final PacketCodec<RegistryByteBuf, SignalSoundPayload> CODEC = PacketCodec.tuple(PacketCodecs.STRING, SignalSoundPayload::username, PacketCodecs.FLOAT, SignalSoundPayload::volume, SignalSoundPayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
