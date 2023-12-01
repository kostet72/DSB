package bot.test;

import bot.test.commands.CreateEvent;
import bot.test.commands.Test;
import bot.test.commands.Help;
import bot.test.commands.ShowUserInfo;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Main {
    public static void main(String[] args) {

        JDA jda = JDABuilder.createDefault("MTE3OTQzNTg5NTk4MDU2MDQ3NA.GGE_qn.RqBQDyfqr7UWJ1z7P4kZ-40fMHWwhDizQXOvME")
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .setActivity(Activity.watching("Hentai"))
                .build();

        // Creating CommandManager and put commands there
        CommandManager manager = new CommandManager();
        manager.add(new Help());
        manager.add(new ShowUserInfo());
        manager.add(new CreateEvent());
        manager.add(new Test());
        jda.addEventListener(manager);
        jda.addEventListener(new Listener());
    }
}
