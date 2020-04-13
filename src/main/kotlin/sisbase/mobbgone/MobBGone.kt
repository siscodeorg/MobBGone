package sisbase.mobbgone

import org.bukkit.Bukkit
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
        val bukkitConfig get() = Bukkit.getPluginManager().getPlugin("MobBGone")!!.config
        val logger get() = Bukkit.getPluginManager().getPlugin("MobBGone")!!.logger
    }
}
