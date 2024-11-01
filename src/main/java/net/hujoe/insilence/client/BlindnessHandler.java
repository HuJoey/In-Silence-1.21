package net.hujoe.insilence.client;

import com.google.gson.JsonSyntaxException;
import com.mojang.blaze3d.systems.RenderSystem;
import net.hujoe.insilence.Insilence;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.PostEffectProcessor;
import net.minecraft.resource.ResourceFactory;
import net.minecraft.util.Identifier;

import java.io.IOException;

/** <a href="https://github.com/croissantnova/SanityDescentIntoMadness/blob/main/src/main/java/croissantnova/sanitydim/client/GuiHandler.java#L93">...</a>
 * ^ Reference for this class in the Insanity Mod
 * <p>
 * initSanityPostProcess() is where you will find information about rendering screen effects (keep in mind that its forge)
 * every time a sound spawns, check if it's in range, and if so then change blindness down a little
 * have a counter (check length in game) for how long until returning to regular (and does it return stage by stage or all at once)
 * check if it works by volume or by amount (so if a sound is really close, does it count the same as one far?)
 */

public class BlindnessHandler {
    private final MinecraftClient client;
    private PostEffectProcessor processor;
    private static final Identifier BLUR_PROCESSOR = Identifier.ofVanilla("shaders/post/blur.json");

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
            this.processor.setUniforms("Radius", 32);
            this.processor.render(delta);
        }
        this.client.getFramebuffer().beginWrite(false);
    }

    public void setDimensions(int width, int height){
        if (this.processor != null) {
            processor.setupDimensions(width, height);
        }
    }
}
