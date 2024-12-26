package net.hujoe.insilence.entity.client;

import net.hujoe.insilence.Insilence;
import net.hujoe.insilence.entity.custom.HuJoeEntity;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

public class HuJoeEntityRenderer extends MobEntityRenderer<HuJoeEntity, HuJoeEntityModel> {
    public HuJoeEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new HuJoeEntityModel(ctx.getPart(HuJoeEntityModel.HUJOE)), 0.0F);
    }

    @Override
    public Identifier getTexture(HuJoeEntity entity) {
        return Identifier.of(Insilence.MOD_ID, "textures/entity/hujoe_statue.png");
    }
}
