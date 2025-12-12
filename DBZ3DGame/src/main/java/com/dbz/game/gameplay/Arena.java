package com.dbz.game.gameplay;

import static org.lwjgl.opengl.GL11.*;

public class Arena {
    
    public void render() {
        glColor3f(0.2f, 0.6f, 0.2f);
        glBegin(GL_QUADS);
        glVertex3f(-50, 0, -50);
        glVertex3f(50, 0, -50);
        glVertex3f(50, 0, 50);
        glVertex3f(-50, 0, 50);
        glEnd();
        
        glColor3f(0.15f, 0.5f, 0.15f);
        glBegin(GL_LINES);
        for (int i = -50; i <= 50; i += 5) {
            glVertex3f(i, 0.01f, -50);
            glVertex3f(i, 0.01f, 50);
            glVertex3f(-50, 0.01f, i);
            glVertex3f(50, 0.01f, i);
        }
        glEnd();
    }
}
