package sisbase.mobbgone;
import org.bukkit.Material;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Config {
    private Set<Material> Blocks = new HashSet<>();
    public void initialize()
    {
        Set<String> BlockRegistry = Utils.getNames(Material.class);
        List<String> fromConfig = MobBGone.getPlugin(MobBGone.class)
                .getConfig().getStringList("spawnproof-blocks");
        for (String entry:fromConfig) {
            Set<String> strings = checkName(entry, BlockRegistry);
            for(String response : strings)
            {
                System.out.println("REGISTERED : " + response);
                Blocks.add(Material.getMaterial(response));
            }
        }
    }
    public Set<Material> getBlocks() {
        return Blocks;
    }
    private Set<String> checkName(String entry, Set<String> registry)
    {
        final String newEntry = entry.replace("*","").replace("\\*","");
        if(entry.contains("*")) return registry.stream().filter((d) -> d.contains(newEntry.toUpperCase())).collect(Collectors.toSet());
        else
        {
            return  registry.stream().filter((d) -> d.equals(entry.toUpperCase())).collect(Collectors.toSet());
        }
    }

}
