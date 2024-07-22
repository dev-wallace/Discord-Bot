package me.wallacedev.lavaplayer;

import java.nio.ByteBuffer;

import org.jetbrains.annotations.Nullable;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.playback.MutableAudioFrame;

import net.dv8tion.jda.api.audio.AudioSendHandler;
import net.dv8tion.jda.api.entities.Guild;

public class AudioForwarder implements AudioSendHandler {
    
    private final AudioPlayer player;
    private final Guild guild;
    private final ByteBuffer buffer = ByteBuffer.allocate(1024);
    private final MutableAudioFrame frame = new MutableAudioFrame();
    private int time;
    
    public AudioForwarder(AudioPlayer player, Guild guild) {
        this.player = player;
        this.guild = guild;
        frame.setBuffer(buffer);
    }

    @Override
    public boolean canProvide() {
        boolean canProvide = player.provide(frame);
        if (!canProvide) {
            time += 20;
            if (time >= 300000) { // 5 minutes
                time = 0;
                guild.getAudioManager().closeAudioConnection();
            }
        } else {
            time = 0;
        }
        return canProvide;
    }

    @Nullable
    @Override
    public ByteBuffer provide20MsAudio() {
        buffer.flip();
        return buffer.duplicate();
    }

    @Override
    public boolean isOpus() {
        return true;
    }
}

