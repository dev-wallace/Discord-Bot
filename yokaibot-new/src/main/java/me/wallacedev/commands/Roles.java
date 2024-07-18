package me.wallacedev.commands;

import java.util.List;

import me.wallacedev.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder;
import net.dv8tion.jda.api.utils.messages.MessageCreateData;


public class Roles implements ICommand {

    @Override
    public String getName() {
       
        return "roles";
    }

    @Override
    public String getDescription() {
      return "Come kneel in front of me and beg for a position and i will provide you ";

      
    }

    @Override
    public List<OptionData> getOptions() {
        return null;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
     
         EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("Vai ter que implorar por este cargo amigao ");
        embedBuilder.setDescription("voce implora?");

        Button yesButton = Button.success("role-button-yes", "Sim");
        Button noButton = Button.danger("role-button-no", "Não");

        MessageCreateData teste = new MessageCreateBuilder()
                .setEmbeds(embedBuilder.build())
                .addActionRow(yesButton, noButton)
                .build();
    
        event.reply(teste).queue();
        Member member = event.getMember();
        Guild guild = event.getGuild();
        Role role = guild.getRoleById(1263520133738008657L);
        guild.addRoleToMember(member, role).queue();
     
    }

}
