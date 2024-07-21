package me.wallacedev.commands;

import java.util.ArrayList;
import java.util.List;

import me.wallacedev.ICommand;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class Mute implements ICommand {
    @Override
    public String getName() {
        return "mute";
    }

    @Override
    public String getDescription() {
        return "Muta usuários";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> options = new ArrayList<>();
        options.add(new OptionData(OptionType.USER, "mutado", "Usuário a ser mutado", true));
        return options;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        Member member = event.getMember();
        Guild guild = event.getGuild();
        Role muteRole = guild.getRoleById(1263525463150563518L); 
        Role defaultRole = guild.getRoleById(1263520133738008657L); 
        Role requiredRole = guild.getRoleById(1263516469812006952L); 

        Member mutedMember = event.getOption("mutado").getAsMember();

        if (member.getRoles().contains(requiredRole)) {
            if (mutedMember.getRoles().contains(defaultRole)) {
                guild.removeRoleFromMember(mutedMember, defaultRole).queue();
            }
            guild.addRoleToMember(mutedMember, muteRole).queue();
            event.reply("Usuário mutado").queue();
        } else {
            event.reply("Você não tem permissão para mutar usuários").queue();
        }
    }
}
