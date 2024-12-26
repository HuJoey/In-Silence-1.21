package net.hujoe.insilence.entity.client;

import net.hujoe.insilence.Insilence;
import net.hujoe.insilence.entity.custom.HuJoeEntity;
import net.hujoe.insilence.entity.custom.SobrXShuppetEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class SobrXShuppetEntityRenderer extends MobEntityRenderer<SobrXShuppetEntity, SobrXShuppetEntityModel> {
    public SobrXShuppetEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new SobrXShuppetEntityModel(ctx.getPart(SobrXShuppetEntityModel.SOBRXSHUPPET)), 0.0F);
    }

    @Override
    public Identifier getTexture(SobrXShuppetEntity entity) {
        return Identifier.of(Insilence.MOD_ID, "textures/entity/sobr_shuppet_statue.png");
    }
}
