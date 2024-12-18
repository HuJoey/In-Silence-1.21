package net.hujoe.insilence.network.payloads;

import net.hujoe.insilence.network.InsilenceNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record SqueakPayload(String username) implements CustomPayload {
    public static final Id<SqueakPayload> ID = new Id<>(InsilenceNetworking.SQUEAK_PACKET_ID);
    public static final PacketCodec<RegistryByteBuf, SqueakPayload> CODEC = PacketCodec.tuple(PacketCodecs.STRING, SqueakPayload::username, SqueakPayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
