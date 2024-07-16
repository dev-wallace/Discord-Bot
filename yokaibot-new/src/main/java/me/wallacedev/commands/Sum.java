package me.wallacedev.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import org.jetbrains.annotations.NotNull;;
public class Sum extends ListenerAdapter {
    
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (!event.getName().equals("soma")) return;

        OptionMapping num1Option = event.getOption("num1");
        OptionMapping num2Option = event.getOption("num2");

        if (num1Option != null && num2Option != null) {
            int num1 = num1Option.getAsInt();
            int num2 = num2Option.getAsInt();
            int sum = num1 + num2;

            event.reply("A soma de " + num1 + " e " + num2 + " é " + sum).queue();
        } else {
            event.reply("Por favor, forneça dois números para somar.").setEphemeral(true).queue();
        }
    }
}