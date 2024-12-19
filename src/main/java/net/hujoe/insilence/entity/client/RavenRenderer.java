package net.hujoe.insilence.entity.client;

import net.hujoe.insilence.Insilence;
import net.hujoe.insilence.entity.custom.MouseEntity;
import net.hujoe.insilence.entity.custom.RavenEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class RavenRenderer extends GeoEntityRenderer<RavenEntity> {

    public RavenRenderer(EntityRendererFactory.Context context){
        super(context, new RavenModel());
    }

    @Override
    public void render(RavenEntity raven, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i){
        super.render(raven, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTextureLocation(RavenEntity animatable) {
        return Identifier.of(Insilence.MOD_ID, "textures/entity/raven.png");
    }
}
