package me.wallacedev.commands.music;

import java.util.ArrayList;
import java.util.List;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;

import me.wallacedev.ICommand;
import me.wallacedev.lavaplayer.GuildMusicManager;
import me.wallacedev.lavaplayer.PlayerManager;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class Queue implements ICommand {

    @Override
    public String getName() {
        return "queue";
    }

    @Override
    public String getDescription() {
        return "Irá exibir a fila atual";
    }

    @Override
    public List<OptionData> getOptions() {
        return null;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        Member member = event.getMember();
        GuildVoiceState memberVoiceState = member.getVoiceState();

        if (!memberVoiceState.inAudioChannel()) {
            event.reply("Você precisa estar em um canal de voz").queue();
            return;
        }

        Member self = event.getGuild().getSelfMember();
        GuildVoiceState selfVoiceState = self.getVoiceState();

        if (!selfVoiceState.inAudioChannel()) {
            event.reply("Não estou em um canal de áudio").queue();
            return;
        }

        if (selfVoiceState.getChannel() != memberVoiceState.getChannel()) {
            event.reply("Você não está no mesmo canal que eu").queue();
            return;
        }

        GuildMusicManager guildMusicManager = PlayerManager.get().getGuildMusicManager(event.getGuild());
        List<AudioTrack> queue = new ArrayList<>(guildMusicManager.getTrackScheduler().getQueue());
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("Fila atual");

        if (queue.isEmpty()) {
            embedBuilder.setDescription("A fila está vazia.");
        } else {
            for (int i = 0; i < queue.size(); i++) {
                AudioTrackInfo info = queue.get(i).getInfo();
                embedBuilder.addField(i + 1 + ":", info.title, false);
            }
        }

        event.replyEmbeds(embedBuilder.build()).queue();
    }
}
