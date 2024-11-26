package net.hujoe.insilence.network.payloads;

import net.hujoe.insilence.network.InsilenceNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record DashClientPayload(int id) implements CustomPayload {
    public static final Id<DashClientPayload> ID = new Id<>(InsilenceNetworking.DASH_CLIENT_PACKET_ID);
    public static final PacketCodec<RegistryByteBuf, DashClientPayload> CODEC = PacketCodec.tuple(PacketCodecs.INTEGER, DashClientPayload::id, DashClientPayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
