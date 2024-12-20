package net.hujoe.insilence.entity.client;

import net.hujoe.insilence.Insilence;
import net.hujoe.insilence.entity.custom.MouseEntity;
import net.hujoe.insilence.entity.custom.RavenEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.util.ClientUtil;

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

    @Nullable
    @Override
    public RenderLayer getRenderType(RavenEntity animatable, Identifier texture, @Nullable VertexConsumerProvider bufferSource, float partialTick) {
        return RenderLayer.getEntityCutoutNoCull(texture);
    }
}
