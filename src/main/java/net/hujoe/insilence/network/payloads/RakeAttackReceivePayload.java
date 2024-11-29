package net.hujoe.insilence.network.payloads;

import net.hujoe.insilence.network.InsilenceNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record RakeAttackReceivePayload(int id) implements CustomPayload {
    public static final Id<RakeAttackReceivePayload> ID = new Id<>(InsilenceNetworking.RAKE_ATTACK_RECEIVE_PACKET_ID);
    public static final PacketCodec<RegistryByteBuf, RakeAttackReceivePayload> CODEC = PacketCodec.tuple(PacketCodecs.INTEGER, RakeAttackReceivePayload::id, RakeAttackReceivePayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
