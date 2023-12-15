package bot.test;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
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

    @Override // When someone sends message
    public void onMessageReceived(MessageReceivedEvent event) {

        MessageChannel channel = event.getChannel();
        String helloForUser = "Да-да, я тут. Приветик, чем могу помочь? Можешь написать команду \"/помощь\" чтобы узнать обо всех моих вохможностях";

        // If bot is pinged
        if (event.getMessage().getMentions().getUsers().get(0).getIdLong() == 1179435895980560474L)
            channel.sendMessage(helloForUser).queue();
    }

    @Override // When form is filled
    public void onModalInteraction(ModalInteractionEvent event) {

        // Get name of the user that creates event
        String userName = event.getUser().getGlobalName();

        // EVENT
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

            EmbedBuilder eventBuilder = new EmbedBuilder();
            eventBuilder.setColor(Color.WHITE);
            eventBuilder.setTitle(userName + " создаёт событие!");
            eventBuilder.addField("Тема:", name, false);
            eventBuilder.addField("Дата начала:", date, false);
            eventBuilder.addField("Описание:", description, false);

            // Send event
            event.replyEmbeds(eventBuilder.build()).queue();
        }

        // CHANGE LOG
        if (event.getModalId().equals("create-change-log")) {

            // Retrieving values of fields by ID's
            ModalMapping versionValue = event.getValue("change-log-version");
            ModalMapping authorValue = event.getValue("change-log-author");
            ModalMapping changesValue = event.getValue("change-log-list");
            assert versionValue != null;
            assert authorValue != null;
            assert changesValue != null;

            String version = versionValue.getAsString();
            String author = authorValue.getAsString();
            String changes = changesValue.getAsString();

            EmbedBuilder changeLogBuilder = new EmbedBuilder();
            changeLogBuilder.setColor(Color.decode("#D93CCE"));
            changeLogBuilder.setTitle("Список изменений нашего бота");
            changeLogBuilder.addField("Версия:", version, true);
            changeLogBuilder.addField("Автор:", author, true);
            changeLogBuilder.addField("Список изменений:", changes, false);
            changeLogBuilder.setFooter("Тыкай по синему тексту, чтобы перейти на гитХаб нашего проекта");
            changeLogBuilder.setUrl("https://github.com/kostet72/DSB");

            // Send change log
            event.replyEmbeds(changeLogBuilder.build()).queue();

        }
    }
}
