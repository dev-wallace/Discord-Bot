package me.wallacedev.lavaplayer;


import java.util.concurrent.BlockingQueue;

import java.util.concurrent.LinkedBlockingQueue;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;

public class TrackScheduler extends AudioEventAdapter{

    
    public AudioPlayer getPlayer() {
        return player;
    }


    public BlockingQueue<AudioTrack> getQueue() {
        return queue;
    }
    public void setRepeat(boolean repeat) {
        isRepeat = repeat;
    }
    public boolean isRepeat() {
        return isRepeat;
    }

    private final AudioPlayer player;
    private final BlockingQueue<AudioTrack> queue = new LinkedBlockingQueue<>();
    private boolean isRepeat = false;

    public TrackScheduler(AudioPlayer player) {
        this.player = player;
    }
    
    
    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
        if(isRepeat) {
            player.startTrack(track.makeClone(), false);
        } else {
            player.startTrack(queue.poll(), false);
        }
    }
     
    public void queue(AudioTrack track) {
        if(!player.startTrack(track, true)) {
            queue.offer(track);
        }
    }

}
