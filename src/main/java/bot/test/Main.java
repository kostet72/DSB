package bot.test;

import bot.test.commands.Help;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class Main {
    public static void main(String[] args) throws LoginException {

        JDA jda = JDABuilder.createDefault("MTE3OTQzNTg5NTk4MDU2MDQ3NA.GJGsuJ.akHmakyHx6jRY-hDTSlFfDMYXJyKIgx2V3EgJ8")
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .build();

        jda.addEventListener(new Help());
    }
}
