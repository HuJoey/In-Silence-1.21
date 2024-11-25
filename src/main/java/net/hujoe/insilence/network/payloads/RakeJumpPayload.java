package net.hujoe.insilence.network.payloads;

import net.hujoe.insilence.network.InsilenceNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record RakeJumpPayload() implements CustomPayload {
    public static final Id<RakeJumpPayload> ID = new Id<>(InsilenceNetworking.RAKE_JUMP_PACKET_ID);
    public static final RakeJumpPayload INSTANCE = new RakeJumpPayload();
    public static final PacketCodec<RegistryByteBuf, RakeJumpPayload> CODEC = PacketCodec.unit(RakeJumpPayload.INSTANCE);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
