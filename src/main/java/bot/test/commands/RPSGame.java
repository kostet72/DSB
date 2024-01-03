package bot.test.commands;

import bot.test.ICommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.util.List;

public class RPSGame implements ICommand {

    @Override // Name of the command
    public String getName() {
        return "суефа";
    }

    @Override // Description of the command
    public String getDescription() {
        return "Камень-Ножницы-Бумага";
    }

    @Override // Options for the command
    public List<OptionData> getOptions() {
        return null;
    }

    @Override // Execute command
    public void execute(SlashCommandInteractionEvent event) {

        // Initial message
        String letsPlay = "Хочешь сыграть со мной в Камень-Ножницы-Бумага? - Отлично. Я уже выбрала, чем буду биться, теперь твоя очередь";

        // Creating buttons with their ID's
        Button rockButton = Button.secondary("rock", "Камень");
        Button scissorsButton = Button.primary("scissors", "Ножницы");
        Button paperButton = Button.success("paper", "Бумага");

        // Creating the message and adding moves
        event.reply(letsPlay).addActionRow(rockButton, scissorsButton, paperButton).queue();
    }
}
