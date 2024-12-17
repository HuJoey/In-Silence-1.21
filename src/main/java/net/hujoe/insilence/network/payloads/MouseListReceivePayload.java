package net.hujoe.insilence.network.payloads;

import net.hujoe.insilence.network.InsilenceNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

import java.util.List;

public record MouseListReceivePayload(List<String> list) implements CustomPayload {
    public static final Id<MouseListReceivePayload> ID = new Id<>(InsilenceNetworking.MOUSE_LIST_RECEIVE_PACKET_ID);
    public static final PacketCodec<RegistryByteBuf, MouseListReceivePayload> CODEC = PacketCodec.tuple(PacketCodecs.STRING.collect(PacketCodecs.toList()), MouseListReceivePayload::list, MouseListReceivePayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
