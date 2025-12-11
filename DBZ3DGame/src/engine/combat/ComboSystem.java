package engine.combat;

import java.util.*;

public class ComboSystem {

    private Map<String, List<String>> combos = new HashMap<>();

    public ComboSystem() {
        combos.put("KAMEHAMEHA", Arrays.asList("DOWN", "RIGHT", "PUNCH"));
        combos.put("FINAL_FLASH", Arrays.asList("LEFT", "LEFT", "PUNCH"));
    }
}
