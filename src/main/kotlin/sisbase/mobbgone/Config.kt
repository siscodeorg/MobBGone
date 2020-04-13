package sisbase.mobbgone

import org.bukkit.Material

class Config {
    private val _blocks: MutableSet<Material?> = mutableSetOf()
    var legacyMode: Boolean = false
    val spawnproofBlocks: Set<Material?>
        get() = _blocks

    fun initialize() {
        val blockRules: List<String> = MobBGone.bukkitConfig.getStringList("spawnproof-blocks")
        makeBlocklist(blockRules)

        legacyMode = MobBGone.bukkitConfig.getBoolean("register-legacy")
    }

    fun makeBlocklist(wildcards: List<String>) {
        val blockRegistry = getEnumNames<Material>().filter { legacyMode || !it.contains("LEGACY") }
        for (pattern in wildcards) {
            val blocks = matchWildcard(pattern, blockRegistry)
            blocks.ifEmpty {
                MobBGone.logger.warning("$pattern matches no known blocks")
            }
            for (block in blocks) {
                MobBGone.logger.info("Registered spawnproof block: $block")
                _blocks.add(Material.getMaterial(block))
            }
        }
    }

    private fun matchWildcard(entry: String, registry: Set<String>): Set<String> {
        val pattern = globToRegex(entry.toUpperCase())
        return registry.filter { pattern.matches(it) }
    }
}
