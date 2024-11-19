package net.hujoe.insilence.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.hujoe.insilence.InSilenceEssentials;
import net.hujoe.insilence.Insilence;
import net.hujoe.insilence.client.ClientRakeManager;
import net.hujoe.insilence.entity.custom.SoundEntity;
import net.hujoe.insilence.server.RakeManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;
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
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
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
    @Shadow @Final private MinecraftClient client;
    @Unique
    private static final Identifier RAKE_WHEEL = Identifier.of(Insilence.MOD_ID,"textures/gui/sprites/wheel.png");
    @Unique
    private static final Identifier SOUND = Identifier.of(Insilence.MOD_ID,"textures/gui/point.png");
    private static final Identifier BAR_EMPTY = Identifier.of(Insilence.MOD_ID, "textures/gui/volume_empty.png");
    private static final Identifier BAR_FILLED_1 = Identifier.of(Insilence.MOD_ID, "textures/gui/volume_filled1.png");

    private static final Identifier BAR_FILLED_2 = Identifier.of(Insilence.MOD_ID, "textures/gui/volume_filled2.png");

    private static final Identifier BAR_FILLED_3 = Identifier.of(Insilence.MOD_ID, "textures/gui/volume_filled3.png");

    private static final Identifier BAR_FILLED_4 = Identifier.of(Insilence.MOD_ID, "textures/gui/volume_filled4.png");

    private static final Identifier BAR_FILLED_5 = Identifier.of(Insilence.MOD_ID, "textures/gui/volume_filled5.png");

    private static final Identifier BAR_FILLED_RAIN = Identifier.of(Insilence.MOD_ID, "textures/gui/volume_filledred.png");


    @Inject(method="render", at = @At("HEAD"))
    public void render(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        ClientPlayerEntity clientPlayerEntity = minecraftClient.player;
        assert clientPlayerEntity != null;
        int x = minecraftClient.getWindow().getScaledWidth() / 2;
        int y = minecraftClient.getWindow().getScaledHeight();
        if (ClientRakeManager.getRakeManager().isRake(clientPlayerEntity.getNameForScoreboard())) {

            if (!clientPlayerEntity.isSpectator() && !client.options.hudHidden) {
                // renders wheel hud
                RenderSystem.setShader(GameRenderer::getRenderTypeTextSeeThroughProgram);
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 0.1F);
                RenderSystem.enableBlend(); // allows wheel to be transparent
                if (minecraftClient.world.isRaining()) {
                    RenderSystem.setShaderColor(1.0F, 0F, 0F, 1.0F);
                } else {
                    RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 0.5F);
                }
                context.drawTexture(RAKE_WHEEL, x - 78, y - 78, 0, 0, 156, 64, 156, 64); // draws the rake wheel texture
                RenderSystem.disableBlend(); // prevents transparency issue when hitting esc

                if (minecraftClient.world.isRaining()){
                RenderSystem.enableBlend();
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                context.drawTexture(SOUND, x - 12, y - 58,0, 0, 24, 24, 24, 24);
                RenderSystem.disableBlend();
                } else {

                // renders all sound events
                BlockPos pos = clientPlayerEntity.getBlockPos();
                World world = clientPlayerEntity.getWorld();
                Vec3d vec3d = Vec3d.ofCenter(pos);
                Predicate<SoundEntity> close = (soundEntity) -> {
                    return soundEntity.getPos().isInRange(vec3d, 100);
                };
                List<SoundEntity> closeEntities = world.getEntitiesByClass(SoundEntity.class, new Box(pos.getX() - 50, pos.getY() - 50, pos.getZ() - 50, pos.getX() + 50, pos.getY() + 50, pos.getZ() + 50), close.and(SoundEntity::isAlive).and(EntityPredicates.EXCEPT_SPECTATOR));
                var facing = clientPlayerEntity.getRotationVector();
                var offset = new Vec3d(50 * facing.getX(), facing.getY(), 50 * facing.getZ());

                double x1 = offset.getX() + clientPlayerEntity.getX();
                double z1 = offset.getZ() + clientPlayerEntity.getZ();

                if (x1 - clientPlayerEntity.getX() == 0) {
                    x1 += 0.01;
                }
                double m2 = (z1 - clientPlayerEntity.getZ()) / (x1 - clientPlayerEntity.getX());
                double facingAngle = Math.atan(m2);
                if (facingAngle < 0) {
                    facingAngle *= -1;
                    facingAngle = Math.PI - facingAngle;
                }
                if (z1 < clientPlayerEntity.getZ()) {
                    facingAngle = Math.PI + facingAngle;
                }
                for (SoundEntity temp : closeEntities) {
                    x1 = temp.getX();
                    z1 = temp.getZ();
                    int size = (int) (-0.2285F * clientPlayerEntity.distanceTo(temp) + 18) - (5 - temp.getStrength() / 10);

                    if (x1 - clientPlayerEntity.getX() == 0) {
                        x1 += 0.01;
                    }
                    double m1 = (z1 - clientPlayerEntity.getZ()) / (x1 - clientPlayerEntity.getX());
                    double soundAngle = Math.atan(m1);
                    if (soundAngle < 0) {
                        soundAngle *= -1;
                        soundAngle = Math.PI - soundAngle;
                    }
                    if (z1 < clientPlayerEntity.getZ()) {
                        soundAngle = Math.PI + soundAngle;
                    }
                    soundAngle -= facingAngle;

                    double xOffset = 78 * Math.sin(soundAngle);
                    double yOffset = -78 * Math.cos(soundAngle) / 2.55;

                    RenderSystem.enableBlend();
                    float alpha;
                    if (temp.getLife() <= 30) {
                        float life = temp.getLife();
                        life = life / 30;
                        alpha = life - 0.1F;
                    } else {
                        alpha = 1;
                    }
                    if (size > 3) {
                        RenderSystem.setShaderColor(1F, 1F, 1F, alpha);
                        context.drawTexture(SOUND, (int) (x - 1 + xOffset), (int) (y - 46 + yOffset), 0, 0, size, size, size, size);
                        RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
                        RenderSystem.disableBlend();
                    }
                }
                }
            }
        } else {
            if (!clientPlayerEntity.isSpectator() && !client.options.hudHidden) {
                RenderSystem.setShader(GameRenderer::getRenderTypeTextSeeThroughProgram);
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                RenderSystem.enableBlend();
                if (minecraftClient.world.isRaining()) {
                    context.drawTexture(BAR_FILLED_RAIN, x + 97, y - 21, 0, 0, 4, 20, 4, 20);
                } else {
                    switch (((InSilenceEssentials) clientPlayerEntity).getLastVolume()){
                        case 0:
                            context.drawTexture(BAR_EMPTY, x - 97, y - 21, 0, 0, 4, 20, 4, 20);
                            break;
                        case 10:
                            context.drawTexture(BAR_FILLED_1, x - 97, y - 21, 0, 0, 4, 20, 4, 20);
                            break;
                        case 20:
                            context.drawTexture(BAR_FILLED_2, x - 97, y - 21, 0, 0, 4, 20, 4, 20);
                            break;
                        case 30:
                            context.drawTexture(BAR_FILLED_3, x - 97, y - 21, 0, 0, 4, 20, 4, 20);
                            break;
                        case 40:
                            context.drawTexture(BAR_FILLED_4, x - 97, y - 21, 0, 0, 4, 20, 4, 20);
                            break;
                        case 50:
                            context.drawTexture(BAR_FILLED_5, x - 97, y - 21, 0, 0, 4, 20, 4, 20);
                            break;
                    }
                }
                RenderSystem.disableBlend(); // prevents transparency issue when hitting esc
            }
        }
    }

    @Inject(method="renderHotbar", at = @At("HEAD"), cancellable = true)
    private void renderHotbar(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        ClientPlayerEntity clientPlayerEntity = minecraftClient.player;
        assert clientPlayerEntity != null;
        if (ClientRakeManager.getRakeManager().isRake(clientPlayerEntity.getNameForScoreboard())) {
            ci.cancel();
        }
    }

    @Inject(method="renderMainHud", at = @At("HEAD"), cancellable = true)
    private void renderMainHud(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        ClientPlayerEntity clientPlayerEntity = minecraftClient.player;
        assert clientPlayerEntity != null;
        if (ClientRakeManager.getRakeManager().isRake(clientPlayerEntity.getNameForScoreboard())) {
            ci.cancel();
        }
    }

    @Inject(method="renderExperienceLevel", at = @At("HEAD"), cancellable = true)
    private void renderExperienceLevel(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        ClientPlayerEntity clientPlayerEntity = minecraftClient.player;
        assert clientPlayerEntity != null;
        if (ClientRakeManager.getRakeManager().isRake(clientPlayerEntity.getNameForScoreboard())) {
            ci.cancel();
        }
    }
}
