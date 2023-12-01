package bot.test;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.modals.ModalMapping;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class Listener extends ListenerAdapter {

    @Override // When button is clicked
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {

        if (event.getButton().getId().equals("yes")) {
            event.reply("Понятно").queue();
        } else if (event.getButton().getId().equals("no")) {
            event.reply("Круто, я тоже").queue();
        }

        // Disable buttons after usage
        event.getMessage().editMessageEmbeds().setComponents().queue();
    }

    @Override // When form is sent
    public void onModalInteraction(ModalInteractionEvent event) {

        // Get name of the user that creates event
        String userName = event.getUser().getGlobalName();

        if (event.getModalId().equals("create-event")) {

            // Retrieving values of fields by ID's
            ModalMapping nameValue = event.getValue("event-name-field");
            ModalMapping dateValue = event.getValue("event-date");
            ModalMapping descriptionValue = event.getValue("event-description-field");
            assert nameValue != null;
            assert dateValue != null;
            assert descriptionValue != null;

            String name = nameValue.getAsString();
            String date = dateValue.getAsString();
            String description = descriptionValue.getAsString();

            EmbedBuilder builder = new EmbedBuilder();
            builder.setColor(Color.WHITE);
            builder.setTitle(userName + " создаёт событие!");
            builder.addField("Тема:", name, false);
            builder.addField("Дата начала:", date, false);
            builder.addField("Описание:", description, false);

            // Send event
            event.replyEmbeds(builder.build()).queue();
        }
    }
}
