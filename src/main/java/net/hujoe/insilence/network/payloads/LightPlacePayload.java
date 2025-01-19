package net.hujoe.insilence.network.payloads;

import net.hujoe.insilence.network.InsilenceNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record LightPlacePayload(int x, int y, int z) implements CustomPayload {
    public static final Id<LightPlacePayload> ID = new Id<>(InsilenceNetworking.LIGHT_PLACE_PACKET_ID);
    public static final PacketCodec<RegistryByteBuf, LightPlacePayload> CODEC = PacketCodec.tuple(PacketCodecs.INTEGER, LightPlacePayload::x, PacketCodecs.INTEGER, LightPlacePayload::y, PacketCodecs.INTEGER, LightPlacePayload::z, LightPlacePayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
