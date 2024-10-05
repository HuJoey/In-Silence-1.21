package net.hujoe.insilence.client;

import net.hujoe.insilence.Insilence;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayers {
    public static final EntityModelLayer RAKE =
            new EntityModelLayer(Identifier.of(Insilence.MOD_ID, "rake"), "main");
}
