package bot.test;

import bot.test.commands.*;
import bot.test.config.Config;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Main {
    public static void main(String[] args) {

        JDA jda = JDABuilder.createDefault(Config.getToken("TOKEN")) // Getting TOKEN from "example.env" file
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .setActivity(Activity.customStatus("Заглядывай в #dev"))
                .build();

        // Creating CommandManager and put commands there
        CommandManager manager = new CommandManager();
        manager.add(new Help());
        manager.add(new ShowUserInfo());
        manager.add(new CreateEvent());
        manager.add(new CreateChangeLog());
        manager.add(new RPSGame());
        jda.addEventListener(manager);
        jda.addEventListener(new Listener());
    }
}
