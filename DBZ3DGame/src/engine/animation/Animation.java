package engine.animation;

import java.util.*;

public class Animation {

    private List<Keyframe> keyframes = new ArrayList<>();
    private float duration;

    public Animation(float duration) {
        this.duration = duration;
    }

    public void addKeyframe(float time, float[] values) {
        keyframes.add(new Keyframe(time, values));
        keyframes.sort(Comparator.comparing(k -> k.time));
    }

    public float[] getValues(float t) {
        if (keyframes.isEmpty()) return new float[]{0,0,0};
        if (t <= keyframes.get(0).time) return keyframes.get(0).values;
        if (t >= keyframes.get(keyframes.size()-1).time)
            return keyframes.get(keyframes.size()-1).values;

        for (int i = 0; i < keyframes.size()-1; i++) {
            Keyframe a = keyframes.get(i);
            Keyframe b = keyframes.get(i+1);

            if (t >= a.time && t <= b.time) {
                float alpha = (t - a.time) / (b.time - a.time);
                float[] vals = new float[a.values.length];
                for (int k = 0; k < vals.length; k++)
                    vals[k] = a.values[k] + (b.values[k] - a.values[k]) * alpha;
                return vals;
            }
        }
        return keyframes.get(0).values;
    }

    public float getDuration() { return duration; }
}
