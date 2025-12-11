package engine.audio;

import javax.sound.sampled.*;
import java.io.File;
import java.util.*;

public class SoundManager {

    private Map<String, Clip> sounds = new HashMap<>();

    public void loadSound(String name, String path) {
        try {
            File file = new File(path);
            AudioInputStream ais = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            sounds.put(name, clip);
        } catch (Exception e) {
            System.err.println("Error cargando sonido: " + e.getMessage());
        }
    }

    public void playSound(String name) {
        Clip c = sounds.get(name);
        if (c == null) return;
        c.stop();
        c.setFramePosition(0);
        c.start();
    }
}
