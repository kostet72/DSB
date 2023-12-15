package bot.test.commands;

import bot.test.ICommand;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;

import java.util.List;

public class CreateChangeLog implements ICommand {

    @Override // Name of the command
    public String getName() {
        return "список_изменений";
    }

    @Override // Description of the command
    public String getDescription() {
        return "Показать список изменений";
    }

    @Override // Options for the command
    public List<OptionData> getOptions() {
        return null;
    }

    @Override // Execute command
    public void execute(SlashCommandInteractionEvent event) {

        // Get Developer role
        Role developerRole = event.getGuild().getRoleById(1181201091933769738L);

        // Error message
        String notDeveloperError = "Хм.. ты не похож на моего создателя. Извини, но только разработчики этого бота могут использовать эту команду. " +
                "Ты можешь пополнить ряды наших разработчиков, написав моему создателю - Kostet https://discord.com/users/482330819323625482";

        // If user have Developer role
        if (event.getMember().getRoles().contains(developerRole)) {

            TextInput changeLogVersion = (
                    TextInput.create("change-log-version",
                                    "Версия",
                                    TextInputStyle.PARAGRAPH)
                            .setRequired(true)
                            .setRequiredRange(1, 30)
                            .setPlaceholder("Обозначьте изменения версией")
                            .build()
            );

            TextInput changeLogAuthor = (
                    TextInput.create("change-log-author",
                                    "Автор изменений",
                                    TextInputStyle.PARAGRAPH)
                            .setRequired(true)
                            .setRequiredRange(1, 30)
                            .setPlaceholder("Кто работал над этими изменениями?")
                            .build()
            );

            TextInput changeLogList = (
                    TextInput.create("change-log-list",
                                    "Список изменений",
                                    TextInputStyle.PARAGRAPH)
                            .setRequired(true)
                            .setRequiredRange(1, 4000)
                            .setPlaceholder("Что поменялось с прошлой версии?")
                            .build()
            );

            // Creating form to fill it by developer
            Modal createChangeLog = (
                    Modal.create("create-change-log",
                                    "Показать список изменений")
                            .addActionRow(changeLogVersion)
                            .addActionRow(changeLogAuthor)
                            .addActionRow(changeLogList)
                            .build()
            );

            // Send form to developer
            event.replyModal(createChangeLog).queue();

        } else { // Send error message if user don't have Developer role
            event.reply(notDeveloperError).queue();
        }
    }
}
