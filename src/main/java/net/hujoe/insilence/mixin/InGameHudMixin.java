package net.hujoe.insilence.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.hujoe.insilence.Insilence;
import net.hujoe.insilence.entity.custom.SoundEntity;
import net.hujoe.insilence.server.RakeManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.function.Predicate;

import static net.minecraft.data.DataProvider.LOGGER;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Unique
    private static final net.minecraft.util.Identifier RAKE_WHEEL = Identifier.of(Insilence.MOD_ID,"textures/gui/sprites/wheel.png");
    private static final net.minecraft.util.Identifier SOUND = Identifier.of(Insilence.MOD_ID,"textures/gui/point.png");

    @Inject(method="render", at = @At("HEAD"))
    public void render(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci){
            MinecraftClient minecraftClient = MinecraftClient.getInstance();
            ClientPlayerEntity clientPlayerEntity = minecraftClient.player;
        assert clientPlayerEntity != null;
        if (RakeManager.getRakeManager().isRake(clientPlayerEntity.getNameForScoreboard())) {
            // renders wheel hud
                int x = minecraftClient.getWindow().getScaledWidth() / 2;
                int y = minecraftClient.getWindow().getScaledHeight();
                RenderSystem.setShader(GameRenderer::getRenderTypeTextSeeThroughProgram);
                RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);
                RenderSystem.enableBlend(); // allows wheel to be transparent
                context.drawTexture(RAKE_WHEEL, x - 78, y - 110,0, 0, 156, 64, 156, 64); // draws the rake wheel texture
                RenderSystem.disableBlend(); // prevents transparency issue when hitting esc

            RenderSystem.enableBlend();
            context.drawTexture(SOUND, x - 6, y - 84,0, 0, 12, 12, 12, 12);
            RenderSystem.disableBlend();

            // renders all sound events
            BlockPos pos = clientPlayerEntity.getBlockPos();
            World world = clientPlayerEntity.getWorld();
            Vec3d vec3d = Vec3d.ofCenter(pos);
            Predicate<SoundEntity> close = (soundEntity) -> {
                return soundEntity.getPos().isInRange(vec3d, 100);
            };
            List<SoundEntity> closeEntities = world.getEntitiesByClass(SoundEntity.class, new Box(pos.getX() - 50, pos.getY() - 50, pos.getZ() - 50, pos.getX() + 50, pos.getY() + 50, pos.getZ() + 50), close.and(SoundEntity::isAlive).and(EntityPredicates.EXCEPT_SPECTATOR));
            for (SoundEntity temp : closeEntities) {
                var angle = calculateAngle(temp, clientPlayerEntity);
                LOGGER.info("FACING: " + Math.sin(angle));


                var xOffset = 78 * Math.sin(angle);
                var yOffset = 78 * Math.cos(angle) / 2;
                RenderSystem.enableBlend();
                context.drawTexture(SOUND, (int) (x - 78 + xOffset), (int) (y - 110 + yOffset),0, 0, 12, 12, 12, 12);
                RenderSystem.disableBlend();
            }
        }
    }

    @Unique
    private double calculateAngle(SoundEntity soundEntity, PlayerEntity playerEntity){
        double x1 = soundEntity.getX();
        double x2 = playerEntity.getX();
        double z1 = soundEntity.getZ();
        double z2 = playerEntity.getZ();
        LOGGER.info("ST1: " + x1 + ", " + x2 + ", " + z1 + ", " + z2);


        if (x1 - x2 == 0){
            x1 += 0.01;
        }
        double m1 = (z1 - z2) / (x1 - x2);
        LOGGER.info("ST2: " + m1);

        var facing = playerEntity.getRotationVector();
        var offset = new Vec3d(50 * facing.getX(), facing.getY(), 50 * facing.getZ());

        x1 = offset.getX() + x2;
        z1 = offset.getZ() + z2;
        LOGGER.info("ST3: " + x1 + ", " + z1);

        if (x1 - x2 == 0){
            x1 += 0.01;
        }
        double m2 = (z1 - z2) / (x1 - x2);
        LOGGER.info("ST4: " + m2);
        double angle = Math.abs((m2 - m1) / (1 + m1 * m2));
        return Math.atan(angle);
    }
}
