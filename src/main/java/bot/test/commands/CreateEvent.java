package bot.test.commands;

import bot.test.ICommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;

import java.util.Calendar;
import java.util.List;

public class CreateEvent implements ICommand {

    @Override // Name of the command
    public String getName() {
        return "событие";
    }

    @Override // Description of the command
    public String getDescription() {
        return "Создать событие";
    }

    @Override // Options for the command
    public List<OptionData> getOptions() {
        return null;
    }

    @Override // Execute command
    public void execute(SlashCommandInteractionEvent event) {

        // Get tomorrow date to insert it by default
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DATE, +1);
        String tomorrowDate = date.getTime().toString();

        // Date formatting ↓ ↓ ↓
        String[] splitToGetTomorrowDate = tomorrowDate.split(" ");

        // Format month for good-looking :)
        String month = switch (splitToGetTomorrowDate[1]) {
            case "Jan" -> "января";
            case "Feb" -> "февраля";
            case "Mar" -> "марта";
            case "Apr" -> "апреля";
            case "May" -> "мая";
            case "Jun" -> "июня";
            case "Jul" -> "июля";
            case "Aug" -> "августа";
            case "Sep" -> "сентября";
            case "Oct" -> "октября";
            case "Nov" -> "ноября";
            case "Dec" -> "декабря";
            default -> "error";
        };

        // Final result of date formatting
        String defaultDate = splitToGetTomorrowDate[2] + "-го " + month;
        // Date formatting ↑ ↑ ↑

        TextInput eventName = (
                TextInput.create("event-name-field",
                                "Тема",
                                TextInputStyle.PARAGRAPH)
                        .setRequired(true)
                        .setRequiredRange(1, 30)
                        .setPlaceholder("Дайте своему событию тему. Например: \"Совместный просмотр фильма\"")
                        .build()
        );

        TextInput eventDate = (
                TextInput.create("event-date",
                                "Дата начала",
                                TextInputStyle.SHORT)
                        .setRequired(false)
                        .setMaxLength(50)
                        .setValue(defaultDate)
                        .setPlaceholder("Необязательное поле")
                        .build()
        );

        TextInput eventDescription = (
                TextInput.create("event-description-field",
                                "Описание",
                                TextInputStyle.PARAGRAPH)
                        .setRequired(true)
                        .setMaxLength(500)
                        .setPlaceholder("Опишите суть/место/требования своего события")
                .build()
        );

        // Creating form to fill it by user
        Modal createEvent = (
                Modal.create("create-event",
                        "Создайте своё событие")
                        .addActionRow(eventName)
                        .addActionRow(eventDate)
                        .addActionRow(eventDescription)
                        .build()
        );

        // Send form to user
        event.replyModal(createEvent).queue();
    }
}
