package net.hujoe.insilence.network.payloads;

import net.hujoe.insilence.network.InsilenceNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record RakeAttackSendPayload(int attackerId, int targetId) implements CustomPayload {
    public static final Id<RakeAttackSendPayload> ID = new Id<>(InsilenceNetworking.RAKE_ATTACK_SEND_PACKET_ID);
    public static final PacketCodec<RegistryByteBuf, RakeAttackSendPayload> CODEC = PacketCodec.tuple(PacketCodecs.INTEGER, RakeAttackSendPayload::attackerId, PacketCodecs.INTEGER, RakeAttackSendPayload::targetId, RakeAttackSendPayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
