package net.hujoe.insilence.entity.client;

import net.hujoe.insilence.Insilence;
import net.hujoe.insilence.entity.custom.MouseEntity;
import net.hujoe.insilence.entity.custom.RakeEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class MouseRenderer extends GeoEntityRenderer<MouseEntity> {

    public MouseRenderer(EntityRendererFactory.Context context){
        super(context, new MouseModel());
    }

    @Override
    public void render(MouseEntity mouse, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i){
        super.render(mouse, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTextureLocation(MouseEntity animatable) {
        return Identifier.of(Insilence.MOD_ID, "textures/entity/mouse.png");
    }
}
