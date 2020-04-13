package sisbase.mobbgone

import org.bukkit.Bukkit
import org.bukkit.Material
import java.util.*
import java.util.regex.Pattern
import java.util.stream.Collectors

class Config {
    private val _blocks: MutableSet<Material?> = HashSet()
    val blocks: Set<Material?>
        get() = _blocks

    fun initialize() {
        val BlockRegistry = getNames(Material::class.java)
        val fromConfig: List<String> = Bukkit.getPluginManager().getPlugin("MobBGone")!!
                .config.getStringList("spawnproof-blocks")
        val legacyMode: Boolean = Bukkit.getPluginManager().getPlugin("MobBGone")!!
                .config.getBoolean("register-legacy")
        for (entry in fromConfig) {
            var strings = checkName(entry, BlockRegistry)
            if (!legacyMode) {
                strings = strings.filter { !it.contains("LEGACY") }
            }
            for (response in strings) {
                println("REGISTERED : $response")
                _blocks.add(Material.getMaterial(response))
            }
        }
    }

    private fun checkName(entry: String, registry: Set<String>): Set<String> {
        val pattern = Regex.escape(entry.toUpperCase()).replace("*", "\\E.*\\Q").toRegex()
        return registry.filter { pattern.matches(it) }
    }
}
