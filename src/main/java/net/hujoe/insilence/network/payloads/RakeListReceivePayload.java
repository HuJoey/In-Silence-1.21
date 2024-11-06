package net.hujoe.insilence.network.payloads;

import io.netty.buffer.ByteBuf;
import net.hujoe.insilence.network.InsilenceNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

import java.util.List;

public record RakeListReceivePayload(List<String> list) implements CustomPayload {
    public static final Id<RakeListReceivePayload> ID = new Id<>(InsilenceNetworking.RAKE_LIST_RECEIVE_PACKET_ID);
    public static final PacketCodec<RegistryByteBuf, RakeListReceivePayload> CODEC = PacketCodec.tuple(PacketCodecs.STRING.collect(PacketCodecs.toList()), RakeListReceivePayload::list, RakeListReceivePayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
