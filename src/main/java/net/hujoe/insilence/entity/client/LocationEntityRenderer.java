package net.hujoe.insilence.entity.client;

import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

public class LocationEntityRenderer extends EntityRenderer {
    public LocationEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Override
    public boolean shouldRender(Entity entity, Frustum frustum, double x, double y, double z) {
        return false;
    }

    @Override
    public Identifier getTexture(Entity entity) {
        return null;
    }
}
