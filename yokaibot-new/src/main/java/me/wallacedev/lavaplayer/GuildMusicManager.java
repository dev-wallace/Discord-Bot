/**
 * Esta classe gerencia o player de áudio e o encaminhador de áudio para uma guilda específica.
 * 
 * Ela cria uma instância de AudioPlayer e TrackScheduler, e também inicializa o AudioForwarder.
 * O AudioPlayer é responsável pela reprodução de áudio, o TrackScheduler gerencia a fila de faixas
 * e o AudioForwarder lida com o envio de áudio para o Discord.
 */

 package me.wallacedev.lavaplayer;


import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import net.dv8tion.jda.api.entities.Guild;

public class GuildMusicManager {

    private final TrackScheduler trackScheduler;
    private final AudioForwarder audioForwarder;

    public GuildMusicManager(AudioPlayerManager manager, Guild guild) {
        AudioPlayer player = manager.createPlayer();
        trackScheduler = new TrackScheduler(player);
        player.addListener(trackScheduler);
        audioForwarder = new AudioForwarder(player, guild); 
    }

    public TrackScheduler getTrackScheduler() {
        return trackScheduler;
    }

    public AudioForwarder getAudioForwarder() {
        return audioForwarder;
    }
}




