package bot.test.commands;

import bot.test.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.List;

public class ShowUserInfo implements ICommand {

    @Override // Name of the command
    public String getName() {
        return "профиль";
    }

    @Override // Description of the command
    public String getDescription() {
        return "Просмотреть информацию о себе";
    }

    @Override // Options for the command
    public List<OptionData> getOptions() {
        return null;
    }

    @Override // Execute command
    public void execute(SlashCommandInteractionEvent event) {

        User user = event.getUser(); // Get the user that uses command

        // Retrieving user data
        String userName = user.getAsMention(); // Name
        String avatarUrl = user.getAvatarUrl(); // Avatar
        String time = String.valueOf(user.getTimeCreated()); // Data of creating account

        // Building "message with info"
        EmbedBuilder builder = getEmbedBuilder(userName, avatarUrl, time);
        event.replyEmbeds(builder.build()).queue();
    }

    @NotNull // "Message with info" builder
    private static EmbedBuilder getEmbedBuilder(String userName, String avatarUrl, String time) {

        // Date formatting ↓ ↓ ↓
        String[] splitToGetData = time.split("[-T]");

        // Format month for good-looking :)
        String month = switch (splitToGetData[1]) {
            case "01" -> "января ";
            case "02" -> "февраля ";
            case "03" -> "марта ";
            case "04" -> "апреля ";
            case "05" -> "мая ";
            case "06" -> "июня ";
            case "07" -> "июля ";
            case "08" -> "августа ";
            case "09" -> "сентября ";
            case "10" -> "октября ";
            case "11" -> "ноября ";
            case "12" -> "декабря ";
            default -> "error ";
        };

        // Final result of date formatting
        String timeCreated = splitToGetData[2] + "-го " + month + splitToGetData[0] + "-го года";
        // Date formatting ↑ ↑ ↑

        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.WHITE);
        builder.setTitle("Информация о пользователе");
        builder.setImage(avatarUrl);

        builder.addField("Никнейм:", userName, true);
        builder.addField("Зарегестрирован с:", timeCreated, true);

        return builder;
    }
}
