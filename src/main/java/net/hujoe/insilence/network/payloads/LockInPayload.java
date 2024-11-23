package net.hujoe.insilence.network.payloads;

import net.hujoe.insilence.network.InsilenceNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record LockInPayload(String username) implements CustomPayload {
    public static final Id<LockInPayload> ID = new Id<>(InsilenceNetworking.LOCK_IN_PACKET_ID);
    public static final PacketCodec<RegistryByteBuf, LockInPayload> CODEC = PacketCodec.tuple(PacketCodecs.STRING, LockInPayload::username, LockInPayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
