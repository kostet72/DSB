package bot.test;

import bot.test.commands.Test;
import bot.test.commands.Help;
import bot.test.commands.ShowUserInfo;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class Main {
    public static void main(String[] args) throws LoginException {

        JDA jda = JDABuilder.createDefault("MTE3OTQzNTg5NTk4MDU2MDQ3NA.Gg_UYM._inl59zH9TCWX8IWJdk4XxgdZ_oPjtPjY3WIYk")
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .build();

        // Creating CommandManager and put commands there
        CommandManager manager = new CommandManager();
        manager.add(new Help());
        manager.add(new ShowUserInfo());
        manager.add(new Test());
        jda.addEventListener(manager);
        jda.addEventListener(new Listener());
    }
}
