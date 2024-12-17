package net.hujoe.insilence.network.payloads;

import net.hujoe.insilence.network.InsilenceNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record MouseUpdatePayload(String username) implements CustomPayload {
    public static final Id<MouseUpdatePayload> ID = new Id<>(InsilenceNetworking.MOUSE_UPDATE_PACKET_ID);
    public static final PacketCodec<RegistryByteBuf, MouseUpdatePayload> CODEC = PacketCodec.tuple(PacketCodecs.STRING, MouseUpdatePayload::username, MouseUpdatePayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
