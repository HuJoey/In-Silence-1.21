package net.hujoe.insilence.voicechat;

import de.maxhenkel.voicechat.api.ServerPlayer;
import de.maxhenkel.voicechat.api.VoicechatApi;
import de.maxhenkel.voicechat.api.VoicechatPlugin;
import de.maxhenkel.voicechat.api.VoicechatServerApi;
import de.maxhenkel.voicechat.api.events.EventRegistration;
import de.maxhenkel.voicechat.api.events.MicrophonePacketEvent;
import de.maxhenkel.voicechat.api.events.VoicechatServerStartedEvent;
import de.maxhenkel.voicechat.api.opus.OpusDecoder;
import net.hujoe.insilence.InSilenceEssentials;
import org.jetbrains.annotations.Nullable;

// ALMOST ALL CODE HERE IS FROM THE henkelmax/voicechat-interaction repository (i couldnt understand much of this code myself)
public class InsilencePlugin implements VoicechatPlugin {
    public static VoicechatApi voicechatApi;
    @Nullable
    public static VoicechatServerApi voicechatServerApi;
    @Nullable
    private OpusDecoder decoder;

    public void initialize(VoicechatApi api) {
        voicechatApi = api;
    }

    public void registerEvents(EventRegistration registration) {
        registration.registerEvent(VoicechatServerStartedEvent.class, this::onServerStarted);
        registration.registerEvent(MicrophonePacketEvent.class, this::onMicPacket);
    }

    private void onServerStarted(VoicechatServerStartedEvent event) {
        voicechatServerApi = event.getVoicechat();
    }

    private void onMicPacket(MicrophonePacketEvent event) {
        ServerPlayer player = event.getSenderConnection().getPlayer();
		if (decoder == null) {
			decoder = event.getVoicechat().createDecoder();
		}

		decoder.resetState();
		short[] decoded = decoder.decode(event.getPacket().getOpusEncodedData());

        if (player != null){
            InSilenceEssentials p = (InSilenceEssentials) player.getEntity();
            p.setSoundLevel((float) calculateAudioLevel(decoded));
        }
    }

    public static double calculateAudioLevel(short[] samples) {
        double rms = 0D; // root mean square (RMS) amplitude

        for (int i = 0; i < samples.length; i++) {
            double sample = (double) samples[i] / (double) Short.MAX_VALUE;
            rms += sample * sample;
        }

        int sampleCount = samples.length / 2;

        rms = (sampleCount == 0) ? 0 : Math.sqrt(rms / sampleCount);

        double db;

        if (rms > 0D) {
            db = Math.min(Math.max(20D * Math.log10(rms), -127D), 0D);
        } else {
            db = -127D;
        }

        return db;
    }

    @Override
    public String getPluginId() {
        return "in-silence";
    }
}
