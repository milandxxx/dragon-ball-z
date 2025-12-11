package engine.particles;

import java.util.*;
import static org.lwjgl.opengl.GL11.*;

public class ParticleSystem {

    private List<Particle> particles = new ArrayList<>();

    public void emit(float x, float y, float vx, float vy) {
        particles.add(new Particle(x, y, vx, vy, 1f));
    }

    public void update(float dt) {
        particles.removeIf(p -> (p.life -= dt) <= 0);
        for (Particle p : particles) {
            p.x += p.vx * dt;
            p.y += p.vy * dt;
        }
    }

    public void render() {
        glBegin(GL_QUADS);
        for (Particle p : particles) {
            glVertex2f(p.x-2, p.y-2);
            glVertex2f(p.x+2, p.y-2);
            glVertex2f(p.x+2, p.y+2);
            glVertex2f(p.x-2, p.y+2);
        }
        glEnd();
    }
}
