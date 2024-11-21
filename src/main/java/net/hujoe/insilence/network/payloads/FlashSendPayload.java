package net.hujoe.insilence.network.payloads;

import net.hujoe.insilence.network.InsilenceNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record FlashSendPayload(int id) implements CustomPayload {
    public static final Id<FlashSendPayload> ID = new Id<>(InsilenceNetworking.FLASH_SEND_PACKET_ID);
    public static final PacketCodec<RegistryByteBuf, FlashSendPayload> CODEC = PacketCodec.tuple(PacketCodecs.INTEGER, FlashSendPayload::id, FlashSendPayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
