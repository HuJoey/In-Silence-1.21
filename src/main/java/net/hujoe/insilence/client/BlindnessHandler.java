package net.hujoe.insilence.client;

import com.google.gson.JsonSyntaxException;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.hujoe.insilence.InSilenceEssentials;
import net.hujoe.insilence.Insilence;
import net.hujoe.insilence.entity.custom.SoundEntity;
import net.hujoe.insilence.network.payloads.SignalSoundPayload;
import net.hujoe.insilence.sound.ModSounds;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.PostEffectProcessor;
import net.minecraft.resource.ResourceFactory;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

public class BlindnessHandler {
    private final MinecraftClient client;
    private PostEffectProcessor processor;
    private static final Identifier BLUR_PROCESSOR = Identifier.ofVanilla("shaders/post/blur.json");
    private boolean spawnedThisTick;
    private float blindnessLevel = 32F;
    private int ticksSinceSound = 100;
    private boolean lockInActive = false;
    private int lockInTimer = 0;

    public BlindnessHandler() {
        client = MinecraftClient.getInstance();
    }

    public void close(){
        processor.close();
    }

    public PostEffectProcessor getProcessor(){
        return processor;
    }

    public void loadProcessor(ResourceFactory resourceFactory){
        try {
            processor = new PostEffectProcessor(client.getTextureManager(), resourceFactory, client.getFramebuffer(), BLUR_PROCESSOR);
            processor.setupDimensions(client.getWindow().getFramebufferWidth(), client.getWindow().getFramebufferHeight());
        } catch (IOException var3) {
            Insilence.LOGGER.warn("Failed to load shader: {}", BLUR_PROCESSOR, var3);
        } catch (JsonSyntaxException var4) {
            Insilence.LOGGER.warn("Failed to parse shader: {}", BLUR_PROCESSOR, var4);
        }
    }

    public void renderBlur(float delta){
        if (this.processor != null) {
            this.processor.setUniforms("Radius", blindnessLevel);
            this.processor.render(delta);
        }
        this.client.getFramebuffer().beginWrite(false);
    }

    public void setDimensions(int width, int height){
        if (this.processor != null) {
            processor.setupDimensions(width, height);
        }
    }

    public void tick(){
        InSilenceEssentials player = (InSilenceEssentials) client.player;
        if (!player.isAttacking()) {
            if (!lockInActive) {
                if (spawnedThisTick) {
                    ticksSinceSound = 0;
                } else {
                    ticksSinceSound++;
                }
                if (ticksSinceSound > 100 && !client.world.isRaining()) {
                    blindnessLevel += 1F;
                    if (blindnessLevel > 32) {
                        blindnessLevel = 32;
                    }
                }
                spawnedThisTick = false;

                if (client.world.isRaining()){
                    if (blindnessLevel - 1 < 6) {
                        blindnessLevel = 6;
                    } else {
                        blindnessLevel -= 1;
                    }
                }
            } else {
                if (blindnessLevel > 2) {
                    blindnessLevel -= 2;
                } else if (blindnessLevel < 2) {
                    blindnessLevel = 2;
                }

                if (lockInTimer != 0) {
                    lockInTimer--;
                } else {
                    lockInActive = false;
                }
            }
        }
    }

    public void spawnSound(SoundEntity entity){
        if (client.player != null && ClientRakeManager.getRakeManager().isRake(client.player.getNameForScoreboard())) {
            var distance = client.player.distanceTo(entity);
            if (distance <= 40){
                float size = (int) (-0.2285F * distance + 18) - (5 - entity.getStrength() / 10);
                if (!lockInActive) {
                    if (blindnessLevel - (size / 2) < 6) {
                        blindnessLevel = 6;
                    } else {
                        blindnessLevel -= (size / 2);
                    }
                    this.spawnedThisTick = true;
                }
                var volume = 1 - (distance / 40);
                ClientPlayNetworking.send(new SignalSoundPayload(client.player.getNameForScoreboard(), volume));
            }
        }
    }

    public void lockIn(){
        lockInActive = true;
        lockInTimer = 300;
    }
}
