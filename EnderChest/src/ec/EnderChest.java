package ec;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class EnderChest extends JavaPlugin {
    @Override
    public void onEnable() {
        Objects.requireNonNull(Bukkit.getPluginCommand("EnderChest")).setExecutor(new CommandHandle());
    }
}
