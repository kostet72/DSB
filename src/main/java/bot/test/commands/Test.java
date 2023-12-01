package bot.test.commands;

import bot.test.ICommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.util.List;

public class Test implements ICommand {

    @Override // Name of the command
    public String getName() {
        return "тест";
    }

    @Override // Description of the command
    public String getDescription() {
        return "Отвечай на вопросы";
    }

    @Override // Options for the command
    public List<OptionData> getOptions() {
        return null;
    }

    @Override // Execute command
    public void execute(SlashCommandInteractionEvent event) {

        // Creating buttons with their ID
        Button yesButton = Button.success("yes", "Да");
        Button noButton = Button.danger("no", "Нет");

        // Creating the message with question and answers
        event.reply("Ты живой?").addActionRow(yesButton, noButton).queue();
    }
}
