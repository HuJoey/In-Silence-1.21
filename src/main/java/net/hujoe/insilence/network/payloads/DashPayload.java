package net.hujoe.insilence.network.payloads;

import net.hujoe.insilence.network.InsilenceNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record DashPayload(String username) implements CustomPayload {
    public static final Id<DashPayload> ID = new Id<>(InsilenceNetworking.DASH_PACKET_ID);
    public static final PacketCodec<RegistryByteBuf, DashPayload> CODEC = PacketCodec.tuple(PacketCodecs.STRING, DashPayload::username, DashPayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
