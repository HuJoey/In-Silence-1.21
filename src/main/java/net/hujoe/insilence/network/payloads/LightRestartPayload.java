package net.hujoe.insilence.network.payloads;

import net.hujoe.insilence.network.InsilenceNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record LightRestartPayload(int x, int y, int z) implements CustomPayload {
    public static final Id<LightRestartPayload> ID = new Id<>(InsilenceNetworking.LIGHT_RESTART_PACKET_ID);
    public static final PacketCodec<RegistryByteBuf, LightRestartPayload> CODEC = PacketCodec.tuple(PacketCodecs.INTEGER, LightRestartPayload::x, PacketCodecs.INTEGER, LightRestartPayload::y, PacketCodecs.INTEGER, LightRestartPayload::z, LightRestartPayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
