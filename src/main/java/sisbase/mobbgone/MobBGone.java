package sisbase.mobbgone;

import org.bukkit.plugin.java.JavaPlugin;
import sisbase.mobbgone.handlers.SpawnHandler;

public final class MobBGone extends JavaPlugin {

    public static final Config cfg = new Config();
    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.reloadConfig();
        cfg.initialize();
        getServer().getPluginManager().registerEvents(new SpawnHandler(),this);
    }

    @Override
    public void onDisable() {
    }
}
