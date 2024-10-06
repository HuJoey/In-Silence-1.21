package net.hujoe.insilence.client;

import net.hujoe.insilence.Insilence;
import net.hujoe.insilence.entity.custom.RakeEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

public class RakeRenderer extends LivingEntityRenderer<RakeEntity, RakeModel<RakeEntity>> {
    private static final Identifier TEXTURE = Identifier.of(Insilence.MOD_ID, "textures/rake/rake.png");

    public RakeRenderer(EntityRendererFactory.Context context){
        super(context, new RakeModel<>(context.getPart(ModModelLayers.RAKE)), 0.6f);
    }

    @Override
    public void render(RakeEntity rake, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i){
        super.render(rake, f, g, matrixStack, vertexConsumerProvider, i);
    }

    public Identifier getTexture(RakeEntity entity) {
        return TEXTURE;
    }
}
