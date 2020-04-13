package siscode.mobbgone.handlers

import com.destroystokyo.paper.event.entity.PreCreatureSpawnEvent
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.CreatureSpawnEvent
import siscode.mobbgone.MobBGone

class SpawnHandler : Listener {
    @EventHandler
    fun Spawn(event: CreatureSpawnEvent) {
        if (MobBGone.cfg.spawnproofBlocks.isEmpty()) return
        if (event.spawnReason != CreatureSpawnEvent.SpawnReason.NATURAL) return
        if (event.entity.type == EntityType.VILLAGER) return
        val block = event.location.add(0.0, -1.0, 0.0).block
        if (MobBGone.cfg.spawnproofBlocks.contains(block.type)) {
            event.isCancelled = true
        }
    }

    @EventHandler
    fun PreSpawn(event: PreCreatureSpawnEvent) {
        if (MobBGone.cfg.spawnproofBlocks.isEmpty()) return
        if (event.reason != CreatureSpawnEvent.SpawnReason.NATURAL) return
        if (event.type == EntityType.VILLAGER) return
        val block = event.spawnLocation.add(0.0, -1.0, 0.0).block
        if (MobBGone.cfg.spawnproofBlocks.contains(block.type)) {
            event.isCancelled = true
        }
    }
}
