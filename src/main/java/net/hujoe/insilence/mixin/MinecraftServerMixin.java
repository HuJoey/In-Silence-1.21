package net.hujoe.insilence.mixin;

import net.hujoe.insilence.HasRakeManager;
import net.hujoe.insilence.server.RakeManager;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin implements HasRakeManager {
    @Override
    public RakeManager getRakeManager() {
        return RakeManager.getRakeManager();
    }
}
