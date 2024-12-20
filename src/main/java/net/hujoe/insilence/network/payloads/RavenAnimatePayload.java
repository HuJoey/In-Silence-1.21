package net.hujoe.insilence.network.payloads;

import net.hujoe.insilence.network.InsilenceNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record RavenAnimatePayload(int ravenId) implements CustomPayload {
    public static final Id<RavenAnimatePayload> ID = new Id<>(InsilenceNetworking.RAVEN_ANIMATE_PACKET_ID);
    public static final PacketCodec<RegistryByteBuf, RavenAnimatePayload> CODEC = PacketCodec.tuple(PacketCodecs.INTEGER, RavenAnimatePayload::ravenId, RavenAnimatePayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
