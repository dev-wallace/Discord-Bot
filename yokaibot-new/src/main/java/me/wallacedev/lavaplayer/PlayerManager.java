/**
 * Esta classe é responsável por gerenciar e fornecer instâncias de GuildMusicManager para diferentes guildas.
 * 
 * Usa um mapa para armazenar e recuperar gerenciadores de música baseados na ID da guilda.
 * O PlayerManager também registra fontes de áudio e cria novos gerenciadores de música quando necessário,
 * além de carregar e tocar faixas usando o AudioPlayerManager.
 */

 package me.wallacedev.lavaplayer;
import java.util.Map;
import java.util.HashMap;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.entities.Guild;

public class PlayerManager {
    private static PlayerManager INSTANCE;
    // Correção: Inicialização correta de `guildMusicManagers` como `HashMap`.
    private Map<Long, GuildMusicManager> guildMusicManagers = new HashMap<>();
    private AudioPlayerManager audioPlayerManager = new DefaultAudioPlayerManager();

    // Construtor que registra as fontes de áudio.
    private PlayerManager() {
        AudioSourceManagers.registerRemoteSources(audioPlayerManager);
        AudioSourceManagers.registerLocalSource(audioPlayerManager);
    }

    // Método estático para obter a instância do `PlayerManager`.
    public static PlayerManager get() {
        if (INSTANCE == null) {
            INSTANCE = new PlayerManager();
        }
        return INSTANCE;
    }

    // Método para obter o `GuildMusicManager` para um servidor específico.
    public GuildMusicManager getGuildMusicManager(Guild guild) {
        return guildMusicManagers.computeIfAbsent(guild.getIdLong(), (guildId) -> {
            GuildMusicManager musicManager = new GuildMusicManager(audioPlayerManager, guild);

            guild.getAudioManager().setSendingHandler(musicManager.getAudioForwarder());

            return musicManager;
        });
    }

    // Método para tocar uma música a partir de uma URL.
    public void play(Guild guild, String trackURL) {
        GuildMusicManager guildMusicManager = getGuildMusicManager(guild);
        audioPlayerManager.loadItemOrdered(guildMusicManager, trackURL, new AudioLoadResultHandler() {
            @Override
            public void trackLoaded(AudioTrack track) {
                guildMusicManager.getTrackScheduler().queue(track);
            }

            @Override
            public void playlistLoaded(AudioPlaylist playlist) {
                guildMusicManager.getTrackScheduler().queue(playlist.getTracks().get(0));
            }

            @Override
            public void noMatches() {
                // Tratamento para quando não há correspondências
            }

            @Override
            public void loadFailed(FriendlyException exception) {
                // Tratamento para quando o carregamento falha
            }
        });
    }
}


