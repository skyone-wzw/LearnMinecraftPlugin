package skyone.auth;

import org.bukkit.configuration.file.FileConfiguration;

public final class ConfigReader {
    public static FileConfiguration config = Auth.instance.getConfig();

    public static boolean isRegistered(String playerName) {
        return config.contains(playerName);
    }

    public static boolean verifyPassword(String playerName, String password) {
        return password.equals(config.getString(playerName.toLowerCase()));
    }

    public static void register(String playerName, String password) {
        config.set(playerName.toLowerCase(), password);
        Auth.instance.saveConfig();
    }
}
