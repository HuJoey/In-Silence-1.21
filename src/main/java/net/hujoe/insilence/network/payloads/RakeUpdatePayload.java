package net.hujoe.insilence.network.payloads;

import net.hujoe.insilence.network.InsilenceNetworking;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record RakeUpdatePayload(String username) implements CustomPayload {
    public static final CustomPayload.Id<RakeUpdatePayload> ID = new CustomPayload.Id<>(InsilenceNetworking.RAKE_UPDATE_PACKET_ID);
    public static final PacketCodec<RegistryByteBuf, RakeUpdatePayload> CODEC = PacketCodec.tuple(PacketCodecs.STRING, RakeUpdatePayload::username, RakeUpdatePayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
