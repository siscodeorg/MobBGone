package siscode.mobbgone

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import siscode.mobbgone.commands.BlocksCommand
import siscode.mobbgone.handlers.InventoryHandler
import siscode.mobbgone.handlers.SpawnHandler

class MobBGone : JavaPlugin() {
    override fun onEnable() {
        saveDefaultConfig()
        reloadConfig()
        cfg.initialize()
        server.pluginManager.registerEvents(SpawnHandler(), this)
        server.pluginManager.registerEvents(InventoryHandler(),this)
        getCommand("blocks")?.setExecutor(BlocksCommand());
    }

    override fun onDisable() {}

    companion object {
        val cfg = Config()
        val bukkitConfig get() = Bukkit.getPluginManager().getPlugin("MobBGone")!!.config
        val logger get() = Bukkit.getPluginManager().getPlugin("MobBGone")!!.logger
    }
}
