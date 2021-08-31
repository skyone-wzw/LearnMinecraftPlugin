package skyone.dashboard;

import org.bukkit.plugin.java.JavaPlugin;

public class DashBoard extends JavaPlugin {
    public static JavaPlugin instance;

    @Override
    public void onLoad() {
    }

    @Override
    public void onEnable() {
        instance = this;
    }
}
