package bot.test.commands;

import bot.test.ICommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.List;

public class Help implements ICommand {

    // TODO: write the information
    /* Answer to the command */ String help = "Извини :( Эта информация ещё пополняется";

    @Override // Name of the command
    public String getName() {
        return "помощь";
    }

    @Override // Description of the command
    public String getDescription() {
        return "Описание и возможности бота";
    }

    @Override // Options for the command
    public List<OptionData> getOptions() {
        return null;
    }

    @Override // Execute command
    public void execute(SlashCommandInteractionEvent event) {
        event.reply(help).queue();
    }
}
