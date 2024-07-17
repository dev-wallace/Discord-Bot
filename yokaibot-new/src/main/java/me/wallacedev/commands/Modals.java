package me.wallacedev.commands;

import me.wallacedev.ICommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;
import net.dv8tion.jda.api.interactions.components.ActionRow;

import java.util.List;

public class Modals implements ICommand {
    @Override
    public String getName() {
        return "modal";
    }

    @Override
    public String getDescription() {
        return "Will open a modal";
    }

    @Override
    public List<OptionData> getOptions() {
        return null;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        TextInput name = TextInput.create("name-field", "Nome", TextInputStyle.SHORT)
                .setRequired(true)
                .setMinLength(1)
                .setMaxLength(100)
                .build();

        TextInput age = TextInput.create("age-field", "Idade", TextInputStyle.SHORT)
                .setRequired(false)
                .build();

        TextInput description = TextInput.create("description-field", "Descrição", TextInputStyle.PARAGRAPH)
                .setRequired(true)
                .setMinLength(1)
                .setPlaceholder("Fale um pouco sobre você")
                .build();

        @SuppressWarnings("deprecation")
        Modal modal = Modal.create("person-modal", "Fale sobre você")
                .addActionRows(ActionRow.of(name), ActionRow.of(age), ActionRow.of(description))
                .build();

        event.replyModal(modal).queue();
    }
}
