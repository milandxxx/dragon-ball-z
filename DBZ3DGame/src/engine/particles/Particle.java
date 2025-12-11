package engine.particles;

public class Particle {

    public float x, y;
    public float vx, vy;
    public float life;
    public float maxLife;

    public Particle(float x, float y, float vx, float vy, float life) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.life = life;
        this.maxLife = life;
    }
}
