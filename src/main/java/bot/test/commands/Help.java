package bot.test.commands;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class Help extends ListenerAdapter {

    @Override // When Bot is started
    public void onReady(@NotNull ReadyEvent event) {

        /* Server ID */ Guild guild = event.getJDA().getGuildById(1034094561649238078L);
        /* Send message on "/help" */ guild.upsertCommand("help", "Description and capabilities of the bot").queue();
    }

    @Override // Send info about bot by using "/help"
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {

        if (!event.getName().equals("help")) return;
        event.reply(help).queue();
    }

    // TODO: write the information
    String help = "no help, sir";
}
