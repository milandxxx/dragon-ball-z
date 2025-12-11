package engine.animation;

public class Keyframe {
    public float time;
    public float[] values;

    public Keyframe(float time, float[] values) {
        this.time = time;
        this.values = values;
    }
}
