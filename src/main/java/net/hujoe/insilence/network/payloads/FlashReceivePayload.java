package net.hujoe.insilence.network.payloads;

import net.hujoe.insilence.network.InsilenceNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record FlashReceivePayload(int id) implements CustomPayload {
    public static final Id<FlashReceivePayload> ID = new Id<>(InsilenceNetworking.FLASH_RECEIVE_PACKET_ID);
    public static final PacketCodec<RegistryByteBuf, FlashReceivePayload> CODEC = PacketCodec.tuple(PacketCodecs.INTEGER, FlashReceivePayload::id, FlashReceivePayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
