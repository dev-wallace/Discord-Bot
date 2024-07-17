package me.wallacedev.commands;

import java.util.List;


import me.wallacedev.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class Embed implements ICommand {

    @Override
    public String getName() {
      
        return "embed";
    }

    @Override
    public String getDescription() {
        
        return "Will send an embed";
    }

    @Override
    public List<OptionData> getOptions() {
        return null;
    }
    @Override
    public void execute(SlashCommandInteractionEvent event) {
        
      
          
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Test Embed");
        builder.setDescription("An example embed");
        builder.addField("Field 1", "Value", false);
        builder.addField("Field 2", "Value", false);
        builder.addField("Field 3", "Value", false);
        builder.setFooter("Example Footer");
        
        builder.appendDescription(" This has been added");
        
        event.replyEmbeds(builder.build()).queue();
    
    }
    

}
