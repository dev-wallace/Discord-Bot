package me.wallacedev.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.List;

import me.wallacedev.ICommand;

public class Sum implements ICommand {

    @Override
    public String getName() {
        return "soma";
    }

    @Override
    public String getDescription() {
        return "Preciso de dois números para fazer a soma.";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> data = new ArrayList<>();
        data.add(new OptionData(OptionType.INTEGER, "numero 1 ", "O primeiro número", true)
                        .setMinValue(1)
                        .setMaxValue(100));
        data.add(new OptionData(OptionType.INTEGER, "numero 2 ", "O segundo número", true)
                        .setMinValue(1)
                        .setMaxValue(100));
        return data;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) { 
        OptionMapping num1Option = event.getOption("number1");
        OptionMapping num2Option = event.getOption("number2");

        if (num1Option != null && num2Option != null) {
            int num1 = num1Option.getAsInt();
            int num2 = num2Option.getAsInt();
            int resultado = num1 + num2;

            event.reply("A soma de " + num1 + " e " + num2 + " é " + resultado).queue();
        } else {
            event.reply("Por favor, forneça dois números para somar.").setEphemeral(true).queue();
        }
    }
}