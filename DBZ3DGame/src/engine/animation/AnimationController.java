package engine.animation;

public class AnimationController {

    private Animation animation;
    private float time = 0;
    private boolean playing = false;

    public void play(Animation anim) {
        this.animation = anim;
        this.time = 0;
        this.playing = true;
    }

    public void update(float dt) {
        if (!playing || animation == null) return;

        time += dt;

        if (time > animation.getDuration()) {
            time = animation.getDuration();
            playing = false;
        }
    }

    public float[] getValues() {
        return animation != null ? animation.getValues(time) : new float[]{0,0,0};
    }
}
