package siscode.mobbgone

import org.bukkit.Material

class Config {
    private val _blocks: MutableSet<Material?> = mutableSetOf()
    private val _blocksPerEntry : MutableMap<String,Set<Material?>> = mutableMapOf()
    var legacyMode: Boolean = false
        private set
    val spawnproofBlocks: Set<Material?>
        get() = _blocks
    val blocksPerEntry : Map<String,Set<Material?>>
        get() = _blocksPerEntry


    fun initialize() {
        val blockRules: List<String> = MobBGone.bukkitConfig.getStringList("spawnproof-blocks")
        makeBlocklist(blockRules)
        legacyMode = MobBGone.bukkitConfig.getBoolean("register-legacy")
    }

    private fun makeBlocklist(wildcards: List<String>) {
        val blockRegistry = getEnumNames<Material>().filter { legacyMode || !it.contains("LEGACY") }
        for (pattern in wildcards) {
            val data : MutableSet<Material?> = mutableSetOf()
            val blocks = matchWildcard(pattern, blockRegistry)
            blocks.ifEmpty {
                MobBGone.logger.warning("$pattern matches no known blocks")
            }
            for (block in blocks) {
                MobBGone.logger.info("Registered spawnproof block: $block")
                _blocks.add(Material.getMaterial(block))
                data.add(Material.getMaterial(block))
            }
            _blocksPerEntry[pattern] = data
        }
    }

    private fun matchWildcard(entry: String, registry: Set<String>): Set<String> {
        val pattern = globToRegex(entry.toUpperCase())
        return registry.filter { pattern.matches(it) }
    }
}
