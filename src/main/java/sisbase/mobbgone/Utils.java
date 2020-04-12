package sisbase.mobbgone;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Utils {
    public static Set<String> getNames(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).collect(Collectors.toSet());
    }
}
