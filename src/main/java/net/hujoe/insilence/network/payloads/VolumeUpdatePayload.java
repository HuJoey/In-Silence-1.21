package net.hujoe.insilence.network.payloads;

import net.hujoe.insilence.network.InsilenceNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record VolumeUpdatePayload(int volume) implements CustomPayload {
    public static final Id<VolumeUpdatePayload> ID = new Id<>(InsilenceNetworking.VOLUME_UPDATE_PACKET_ID);
    public static final PacketCodec<RegistryByteBuf, VolumeUpdatePayload> CODEC = PacketCodec.tuple(PacketCodecs.INTEGER, VolumeUpdatePayload::volume, VolumeUpdatePayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
