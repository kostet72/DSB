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
import java.util.Random;

public class Listener extends ListenerAdapter {

    @Override // When button is clicked
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {

        // All possible moves
        String[] moves = {"rock", "scissors", "paper"};

        // Make random move as a Bot
        String randomMove = moves[new Random().nextInt(moves.length)];

        // Receive player's move
        String button = event.getButton().getId();
        assert button != null;

        // Translation
        String botMove = switch (randomMove) {
            case "rock" -> "Камень";
            case "scissors" -> "Ножницы";
            case "paper" -> "Бумагу";
            default -> "error";
        };

        // Announce the move made by the Bot
        String move = "Бы-ла не бы-ла! Я выбираю " + botMove + "!\n";

        // Replies to the outcome of the game
        String draw = "Чтож, похоже у нас ничья. Давай сыграем ещё раз";
        String botLoses = "Ты победил. Ну ничего, в следующий раз я точно тебя одолею!";
        String botWins = "Ха-ха, так-то! Я победила!";

        // If the Bot and player chose the same move
        if (button.equals(randomMove)) {
            event.reply(move + draw).queue();
        }

        // Player chose rock
        else if (button.equals("rock")) {

            if (randomMove.equals("scissors")) {
                event.reply(move + botLoses).queue();
            } else if (randomMove.equals("paper")) {
                event.reply(move + botWins).queue();
            }
        }

        // Player chose scissors
        else if (button.equals("scissors")) {

            if (randomMove.equals("rock")) {
                event.reply(move + botWins).queue();
            } else if (randomMove.equals("paper")) {
                event.reply(move + botLoses).queue();
            }
        }

        // Player chose paper
        else if (button.equals("paper")) {

            if (randomMove.equals("rock")) {
                event.reply(move + botLoses).queue();
            } else if (randomMove.equals("scissors")) {
                event.reply(move + botWins).queue();
            }
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
            changeLogBuilder.addBlankField(false);
            changeLogBuilder.setFooter("Тыкай по синему тексту, чтобы перейти на GitHub проекта");
            changeLogBuilder.setUrl("https://github.com/kostet72/DSB");

            // Send change log
            event.replyEmbeds(changeLogBuilder.build()).queue();
        }
    }
}
