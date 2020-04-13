package sisbase.mobbgone

import org.bukkit.Material

class Config {
    private val _blocks: MutableSet<Material?> = HashSet()
    var legacyMode: Boolean = false
    val spawnproofBlocks: Set<Material?>
        get() = _blocks

    fun initialize() {
        val fromConfig: List<String> = MobBGone.bukkitConfig.getStringList("spawnproof-blocks")
        legacyMode = MobBGone.bukkitConfig.getBoolean("register-legacy")

        makeBlocklist(fromConfig)
    }

    fun makeBlocklist(wildcards: List<String>) {
        val blockRegistry = getEnumNames<Material>().filter { legacyMode || !it.contains("LEGACY") }
        for (entry in wildcards) {
            val strings = matchWildcard(entry, blockRegistry)
            strings.ifEmpty {
                MobBGone.logger.warning("$entry matches no known blocks")
            }
            for (response in strings) {
                MobBGone.logger.info("Registered spawnproof block: $response")
                _blocks.add(Material.getMaterial(response))
            }
        }
    }

    private fun matchWildcard(entry: String, registry: Set<String>): Set<String> {
        val pattern = globToRegex(entry.toUpperCase())
        return registry.filter { pattern.matches(it) }
    }
}
