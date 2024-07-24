package me.wallacedev.commands.music;

import java.util.List;

import me.wallacedev.ICommand;
import me.wallacedev.lavaplayer.GuildMusicManager;
import me.wallacedev.lavaplayer.PlayerManager;
import me.wallacedev.lavaplayer.TrackScheduler;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class Stop implements ICommand {

    @Override
    public String getName() {
        return "stop";
    }

    @Override
    public String getDescription() {
        return "Irá parar o jogo do bot";
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
        TrackScheduler trackScheduler = guildMusicManager.getTrackScheduler();
        trackScheduler.getQueue().clear();
        trackScheduler.getPlayer().stopTrack();
        event.reply("Stopped").queue();


    }
      
}
