package sisbase.mobbgone;
import org.bukkit.Material;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Config {
    private List<Material> Blocks = new ArrayList<>();
    public void initialize()
    {
        List<String> BlockRegistry = Utils.getNames(Material.class);
        List<String> fromConfig = MobBGone.getPlugin(MobBGone.class)
                .getConfig().getStringList("spawnproof-blocks");
        for (String entry:fromConfig) {
            List<String> strings = checkName(entry, BlockRegistry);
            for(String response : strings)
            {
                System.out.println("REGISTERED : " + response);
                Blocks.add(Material.getMaterial(response));
            }
        }
    }
    public List<Material> getBlocks() {

        return Blocks;
    }
    private List<String> checkName(String entry, List<String> registry)
    {
        final String newEntry = entry.replace("*","").replace("\\*","");
        if(entry.contains("*")) return registry.stream().filter((d) -> d.contains(newEntry.toUpperCase())).collect(Collectors.toList());
        else
        {
            return  registry.stream().filter((d) -> d.equals(entry.toUpperCase())).collect(Collectors.toList());
        }
    }

}
