package me.wallacedev;

import org.jetbrains.annotations.NotNull;

import net.dv8tion.jda.api.EmbedBuilder;

import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;


import net.dv8tion.jda.api.hooks.ListenerAdapter;

import net.dv8tion.jda.api.interactions.modals.ModalMapping;

public class Listener extends ListenerAdapter {



        @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        if(event.getButton().getId().equals("yes-button")) {
            event.reply("esse mlk sabe o que fala ").queue();
        } else if(event.getButton().getId().equals("no-button")) {
            event.reply("Ahh slk fala a verdade ai ").queue();
        }
        event.getMessage().delete().queue();
    }
    @Override
    public void onModalInteraction(@NotNull ModalInteractionEvent event) {
        if(event.getModalId().equals("person-modal")) {
            ModalMapping nameValue = event.getValue("name-field");
            ModalMapping ageValue = event.getValue("age-field");
            ModalMapping descriptionValue = event.getValue("description-field");

            String name = nameValue.getAsString();
            String description = descriptionValue.getAsString();

            String age;
            if(ageValue.getAsString().isEmpty()) {
                age = "N/A";
            } else {
                age = ageValue.getAsString();
            }

            EmbedBuilder builder = new EmbedBuilder();
            builder.setTitle(name);
            builder.setDescription("Um pouco sobre " + name);
            builder.addField("Name", name, false);
            builder.addField("Age", age, false);
            builder.addField("Description", description, false);
            event.replyEmbeds(builder.build()).queue();
        }
    }
}

