package sisbase.mobbgone.handlers;
import com.destroystokyo.paper.event.entity.PreCreatureSpawnEvent;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import sisbase.mobbgone.MobBGone;

public class SpawnHandler implements Listener {
    @EventHandler
    public void Spawn(CreatureSpawnEvent event)
    {
        if(MobBGone.cfg.getBlocks().size() == 0) return;
        if(!event.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.NATURAL)) return;
        if(event.getEntity().getType().equals(EntityType.VILLAGER)) return;
        Block block = event.getLocation().add(0, -1, 0).getBlock();
        if(MobBGone.cfg.getBlocks().contains(block.getType()))
        {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void PreSpawn(PreCreatureSpawnEvent event)
    {
        if(MobBGone.cfg.getBlocks().size() == 0) return;
        if(!event.getReason().equals(CreatureSpawnEvent.SpawnReason.NATURAL)) return;
        if(event.getType().equals(EntityType.VILLAGER)) return;
        Block block = event.getSpawnLocation().add(0, -1, 0).getBlock();
        if(MobBGone.cfg.getBlocks().contains(block.getType()))
        {
            event.setCancelled(true);
        }
    }
}
