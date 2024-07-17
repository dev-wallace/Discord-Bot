package me.wallacedev.commands;

import java.util.List;

import me.wallacedev.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.utils.messages.MessageCreateData;
import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder;


public class Buttons implements ICommand {

    @Override
    public String getName() {
        return "button";
    }

    @Override
    public String getDescription() {
        return "Buttons";
    }

    @Override
    public List<OptionData> getOptions() {
        return null;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("Bate Bola Jogo Rapido !!");
        embedBuilder.setDescription("yokai amassa no elden ring?");

        Button yesButton = Button.success("yes-button", "Sim");
        Button noButton = Button.danger("no-button", "Não");

        MessageCreateData messageData = new MessageCreateBuilder()
                .setEmbeds(embedBuilder.build())
                .addActionRow(yesButton, noButton)
                .build();

        event.reply(messageData).queue();
    }
}
