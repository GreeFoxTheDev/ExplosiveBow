package greefox.explosiveBow;

import org.bukkit.plugin.java.JavaPlugin;

public final class ExplosiveBow extends JavaPlugin {
    public static ExplosiveBow instance;
    public static ExplosiveBow getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {

        reloadConfig();
        instance = this;
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new ExplosiveBowListener(this), this);
        ExplosiveBowItem.init();
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
