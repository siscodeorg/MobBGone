package sisbase.mobbgone

import org.bukkit.plugin.java.JavaPlugin
import sisbase.mobbgone.handlers.SpawnHandler

class MobBGone : JavaPlugin() {
    override fun onEnable() {
        saveDefaultConfig()
        reloadConfig()
        cfg.initialize()
        server.pluginManager.registerEvents(SpawnHandler(), this)
    }

    override fun onDisable() {}

    companion object {
        val cfg = Config()
    }
}
