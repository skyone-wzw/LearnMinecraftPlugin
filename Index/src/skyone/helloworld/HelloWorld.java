package skyone.helloworld;

import org.bukkit.plugin.java.JavaPlugin;

public class HelloWorld extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("Hello world!");
    }
}
