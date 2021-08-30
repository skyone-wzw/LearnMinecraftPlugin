package skyone.auth;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class Auth extends JavaPlugin {
    public static JavaPlugin instance;

    @Override
    public void onLoad() {
        saveDefaultConfig();
    }

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new EventListener(), this);
        Objects.requireNonNull(Bukkit.getPluginCommand("login")).setExecutor(new CommandHandle());
        instance = this;
    }

    @Override
    public void onDisable() {
        saveConfig();
    }
}
