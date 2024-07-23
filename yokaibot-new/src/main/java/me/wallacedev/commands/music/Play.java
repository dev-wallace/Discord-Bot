package me.wallacedev.commands.music;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;



import me.wallacedev.ICommand;
import me.wallacedev.lavaplayer.GuildMusicManager;
import me.wallacedev.lavaplayer.PlayerManager;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class Play implements ICommand {

    @Override
    public String getName() {
        return "play";
    }

    @Override
    public String getDescription() {
        return "Vou tocar músicas";
    }

    @Override
    public List<OptionData> getOptions() {
       List<OptionData> options = new ArrayList<>();
       options.add(new OptionData(OptionType.STRING, "name", "Nome da música a ser tocada", true));
       return options;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        Member member = event.getMember();
        GuildVoiceState memberVoiceState = member.getVoiceState();

        // Verifica se o membro está em um canal de voz
        if (!memberVoiceState.inAudioChannel()) {
            event.reply("Você precisa estar em um canal de voz").queue();
            return;
        }

        Member self = event.getGuild().getSelfMember();
        GuildVoiceState selfVoiceState = self.getVoiceState();

        // Verifica se o bot está em um canal de voz e tenta se conectar, se necessário
        if (!selfVoiceState.inAudioChannel()) {
            event.getGuild().getAudioManager().openAudioConnection(memberVoiceState.getChannel());
        } else if (selfVoiceState.getChannel() != memberVoiceState.getChannel()) {
            event.reply("Você precisa estar no mesmo canal que eu").queue();
            return;
        }

        String name = event.getOption("name").getAsString();
        try {
            new URI(name); // Tenta criar uma URI a partir do nome fornecido
        } catch (URISyntaxException e) {
            name = "ytsearch:" + name; // Prefixo para busca no YouTube se não for uma URI válida
        }

        PlayerManager playerManager = PlayerManager.get();
        event.reply("Tocando").queue();
        playerManager.play(event.getGuild(), name);
    }

    
    @SuppressWarnings("unused")
    private GuildMusicManager getGuildMusicManager(Guild guild) {
        return PlayerManager.get().getGuildMusicManager(guild);
    }
}
