package net.hujoe.insilence.entity.client;

import net.hujoe.insilence.Insilence;
import net.hujoe.insilence.entity.custom.RakeEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.joml.Quaternionf;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class RakeRenderer extends GeoEntityRenderer<RakeEntity> {

    public RakeRenderer(EntityRendererFactory.Context context){
        super(context, new RakeModel());
    }

    @Override
    public void render(RakeEntity rake, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i){
        super.render(rake, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTextureLocation(RakeEntity animatable) {
        return Identifier.of(Insilence.MOD_ID, "textures/entity/rake.png");
    }
}
