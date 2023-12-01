package bot.test;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

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
}
