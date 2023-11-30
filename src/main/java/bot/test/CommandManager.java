package bot.test;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CommandManager extends ListenerAdapter {

    private final List<ICommand> commands = new ArrayList<>();

    @Override // When Bot is started
    public void onReady(@NotNull ReadyEvent event) {

        for (Guild guild : event.getJDA().getGuilds()) {

            for (ICommand command : commands) {

                // If there are no Options in command - ignore getOptions()
                if (command.getOptions() == null) {
                    guild.upsertCommand(command.getName(), command.getDescription()).queue();
                } else {
                    guild.upsertCommand(command.getName(), command.getDescription()).addOptions(command.getOptions()).queue();
                }
            }
        }
    }

    @Override // Executes a command by getting list of the commands
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {

        for (ICommand command : commands) {

            // checking for the required command
            if (command.getName().equals(event.getName())) {
                command.execute(event);
                return;
            }
        }
    }

    // Adding command to Bot commands
    public void add(ICommand command) {
        commands.add(command);
    }
}
