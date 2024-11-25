package net.hujoe.insilence.network.payloads;

import net.hujoe.insilence.network.InsilenceNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record FlashlightActivatePayload(boolean state) implements CustomPayload {
    public static final Id<FlashlightActivatePayload> ID = new Id<>(InsilenceNetworking.FLASHLIGHT_ACTIVATE_PACKET_ID);
    public static final PacketCodec<RegistryByteBuf, FlashlightActivatePayload> CODEC = PacketCodec.tuple(PacketCodecs.BOOL, FlashlightActivatePayload::state, FlashlightActivatePayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
