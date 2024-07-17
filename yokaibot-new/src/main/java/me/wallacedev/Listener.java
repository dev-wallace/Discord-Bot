package me.wallacedev;

import org.jetbrains.annotations.NotNull;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;

import net.dv8tion.jda.api.events.session.ReadyEvent;

import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class Listener extends ListenerAdapter {


    @Override
    public void onReady(@NotNull ReadyEvent event) {
        JDA jda = event.getJDA();
        Guild guild = jda.getGuildById(1262420280475648070L);
        
        if (guild != null) {
            guild.upsertCommand("soma", "Informe dois números para soma")
            .addOptions(new OptionData(OptionType.INTEGER, "num1", "the first number ", true )
            .setMinValue(1)
            .setMaxValue(100),
            

            new OptionData(OptionType.INTEGER, "num2", "the second number ", true)
            .setMinValue(1)
            .setMaxValue(100)

            ).queue();
        } else {
            System.out.println("Guild not found");
        }
    }
}

