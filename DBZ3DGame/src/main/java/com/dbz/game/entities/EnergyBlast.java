package com.dbz.game.entities;

import static org.lwjgl.opengl.GL11.*;

public class EnergyBlast {
    private float x, y, z;
    private float vx;
    private float size = 0.5f;
    private Fighter owner;
    private float r, g, b;

    public EnergyBlast(float x, float y, float z, float direction, Fighter owner, float r, float g, float b) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.vx = direction * 15;
        this.owner = owner;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public void update(float dt) {
        x += vx * dt;
        size += dt * 0.5f;
        if (size > 1.5f) size = 1.5f;
    }

    public boolean collidesWith(Fighter fighter) {
        float dx = x - fighter.getX();
        float dy = y - fighter.getY();
        float dist = (float) Math.sqrt(dx * dx + dy * dy);
        return dist < 2;
    }

    public void render() {
        glPushMatrix();
        glTranslatef(x, y, z);
        
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE);
        
        glColor4f(r, g, b, 0.8f);
        renderSphere(size);
        
        glColor4f(1, 1, 1, 0.6f);
        renderSphere(size * 0.6f);
        
        glDisable(GL_BLEND);
        glPopMatrix();
    }

    private void renderSphere(float radius) {
        int slices = 16;
        int stacks = 16;
        for (int i = 0; i < stacks; i++) {
            double lat0 = Math.PI * (-0.5 + (double) i / stacks);
            double lat1 = Math.PI * (-0.5 + (double) (i + 1) / stacks);
            double z0 = Math.sin(lat0);
            double z1 = Math.sin(lat1);
            double r0 = Math.cos(lat0);
            double r1 = Math.cos(lat1);
            
            glBegin(GL_QUAD_STRIP);
            for (int j = 0; j <= slices; j++) {
                double lng = 2 * Math.PI * j / slices;
                double x1 = Math.cos(lng);
                double y1 = Math.sin(lng);
                glVertex3f((float)(x1 * r0 * radius), (float)(y1 * r0 * radius), (float)(z0 * radius));
                glVertex3f((float)(x1 * r1 * radius), (float)(y1 * r1 * radius), (float)(z1 * radius));
            }
            glEnd();
        }
    }

    public float getX() { return x; }
    public Fighter getOwner() { return owner; }
}
