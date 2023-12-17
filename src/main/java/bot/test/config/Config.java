package bot.test.config;

import io.github.cdimascio.dotenv.Dotenv;

public class Config {

    // Load ".env" file
    private static final Dotenv dotenv = Dotenv.load();

    public static String getToken(String token) {
        return dotenv.get(token); // Get token from ".env"
    }
}
