package me.wallacedev.commands.music;

import java.util.List;

import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;

import me.wallacedev.ICommand;
import me.wallacedev.lavaplayer.GuildMusicManager;
import me.wallacedev.lavaplayer.PlayerManager;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class NowPlaying implements ICommand{

    @Override
    public String getName() {
        return "nowplaying";
    }

    @Override
    public String getDescription() {
        return "Irá exibir a música que está sendo reproduzida no momento";
    }

    @Override
    public List<OptionData> getOptions() {
        return null;
    }

 @Override
    public void execute(SlashCommandInteractionEvent event) {
        Member member = event.getMember();
        GuildVoiceState memberVoiceState = member.getVoiceState();

        if(!memberVoiceState.inAudioChannel()) {
            event.reply("Você precisa estar em um canal de voz").queue();
            return;
        }

        Member self = event.getGuild().getSelfMember();
        GuildVoiceState selfVoiceState = self.getVoiceState();

        if(!selfVoiceState.inAudioChannel()) {
            event.reply("Não estou em um canal de áudio").queue();
            return;
        }

        if(selfVoiceState.getChannel() != memberVoiceState.getChannel()) {
            event.reply("Você não está no mesmo canal que eu").queue();
            return;
        }

        GuildMusicManager guildMusicManager = PlayerManager.get().getGuildMusicManager(event.getGuild());
        if(guildMusicManager.getTrackScheduler().getPlayer().getPlayingTrack() == null) {
            event.reply("não estou tocando nada").queue();
            return;
        }
        AudioTrackInfo info = guildMusicManager.getTrackScheduler().getPlayer().getPlayingTrack().getInfo();
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("tocando agora");
        embedBuilder.setDescription("**Name:** `" + info.title + "`");
        embedBuilder.appendDescription("\n**Author:** `" + info.author + "`");
        embedBuilder.appendDescription("\n**URL:** `" + info.uri + "`");
        event.replyEmbeds(embedBuilder.build()).queue();
    }

}
